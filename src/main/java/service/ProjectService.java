package service;

import model.Project;
import model.User;
import model.WorkOn;
import model.WorkPercentage;
import repository.ProjectRepository;
import repository.WorkOnRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProjectService {
    private ProjectRepository projectRepository = new ProjectRepository();
    private WorkOnRepository workOnRepository = new WorkOnRepository();

    private WorkPercentage wp;


    public void getAllProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Project> plist = projectRepository.getAllProject();

        if(plist!=null && plist.size()>0){
            req.setAttribute("projectslist",plist);
        }else{
            req.setAttribute("msg","Not found any Project.");
        }
        req.getRequestDispatcher("/project-table.jsp").forward(req,resp);
    }

    public boolean addProject(String name, String createDate, String endDate){
        return projectRepository.addProject(new Project(name, createDate, endDate));
    }

    public Project findProjectByID(int ID){
        return projectRepository.getProjectByID(ID);
    }
    public boolean removeProject(int ID){
        return projectRepository.removeProject(ID);
    }

    public boolean updateProject(String ID, String name, String createDate, String endDate){
        return projectRepository.updateProject(new Project(ID, name, createDate, endDate));
    }

    public void searchProject(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String txtSearch = (String)req.getParameter("txt");
        ArrayList<Project> list = projectRepository.searchProjectByName(txtSearch);
        PrintWriter pw = resp.getWriter();
        for(Project p: list ){
            pw.println(" <tr>\n" +
                    "                                            <td>"+ p.getID() +"</td>\n" +
                    "                                            <td>"+ p.getName() +"</td>\n" +
                    "                                            <td>"+ p.getCreateDate() +"</td>\n" +
                    "                                            <td>"+ p.getEndDate() +"</td>\n" +
                    "                                            <td style=\"display:flex;\">\n" +
                    "                                                <form action=\"updateProject\" method=\"POST\" style=\"margin-left: 5px;\">\n" +
                    "                                                    <input type=\"hidden\" name=\"updateProjectID\" value=\"${p.ID}\">\n" +
                    "                                                    <button type=\"submit\" class=\"btn btn-sm btn-primary\">Sửa</button>\n" +
                    "                                                </form>\n" +
                    "                                                <form action=\"removeProject\" method=\"POST\" style=\"margin-left: 5px;\">\n" +
                    "                                                    <input type=\"hidden\" name=\"removeProjectID\" value=\"${p.ID}\">\n" +
                    "                                                    <button type=\"submit\" class=\"btn btn-sm btn-danger\">Xóa</button>\n" +
                    "                                                </form>\n" +
                    "                                                <form action=\"projectWorkList\" method=\"POST\" style=\"margin-left: 5px;\">\n" +
                    "                                                    <input type=\"hidden\" name=\"projectID\" value=\"${p.ID}\">\n" +
                    "                                                    <button type=\"submit\" class=\"btn btn-sm btn-info\">Xem</button>\n" +
                    "                                                </form>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>");
        }
        pw.close();
    }

    public void getAllWorkInProject(int ID, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            ArrayList<User> ulist = workOnRepository.getAllUserByProjectID(ID);
            ArrayList<WorkOn> wlist = workOnRepository.getAllUserWorkByProjectID(ID);

            if(wlist.size()>0 && wlist!=null){
                // get percentage project work status
                wp = new WorkPercentage(wlist);
                ArrayList<Integer> perList = wp.getPerList();

                // distribute type of work status to user list of work
                for(User u : ulist){
                    for(WorkOn w : wlist){
                        if(u.getID().equals(w.getEmployeeID())){
                            if(w.getStatusID().equals("1")){
                                u.getNotDoneList().add(w);
                            }else if(w.getStatusID().equals("2")){
                                u.getIsDoingList().add(w);
                            }else{
                                u.getDoneList().add(w);
                            }
                        }
                    }
                }

                // request Scope attribute
                req.setAttribute("perList",perList);
                req.setAttribute("usersList", ulist);
                req.setAttribute("worksList", wlist);
            }else {
                req.setAttribute("msg", "Not found any task employee(s).");
            }

        req.getRequestDispatcher("/project-detail.jsp").forward(req,resp);
    }
}
