package service;

import model.*;
import repository.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WorkService {
    private WorkOnRepository workOnRepository = new WorkOnRepository();
    private ProjectRepository projectRepository = new ProjectRepository();
    private TaskRepository taskRepository = new TaskRepository();
    private UserRepository userRepository = new UserRepository();
    private StatusRepository statusRepository = new StatusRepository();

    public boolean addWork(String taskID, String projectID, String employeeID, String createDate, String endDate, String statusID){
        return workOnRepository.addWorkOn(new WorkOn(taskID, projectID, employeeID, createDate, endDate, statusID));
    }

    public ArrayList<User> getAllUser(){
        return userRepository.getAllUser();
    }

    public ArrayList<Project> getAllProject(){
        return projectRepository.getAllProject();
    }

    public ArrayList<Task> getAllTask(){
        return taskRepository.getAllTask();
    }

    public ArrayList<Status> getAllStatus(){
        return statusRepository.getAllStatus();
    }
    public ArrayList<WorkOn> getAllWork(){
        return workOnRepository.getAllUserWork();
    }

    public WorkOn getWorkByID(int ID){
        return workOnRepository.getWorkByJoin(ID);
    }
    public void getAllWorkAddDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Project> plist = projectRepository.getAllProject();

        ArrayList<Task> tlist = taskRepository.getAllTask();

        ArrayList<User> ulist = userRepository.getAllUser();

        ArrayList<Status> stlist = statusRepository.getAllStatus();


        // set 3 list to Request attribute Scope and sendRedirect to /listWork
        req.setAttribute("projectsList",plist);
        req.setAttribute("tasksList",tlist);
        req.setAttribute("usersList",ulist);
        req.setAttribute("statusList",stlist);
        req.getRequestDispatcher("/work-add.jsp").forward(req, resp);
    }

    public boolean removeWork(int ID) {
        return workOnRepository.removeWork(ID);
    }


    public boolean updateWork(String ID, String taskID, String projectID, String createDate, String endDate, String statusID) {
        return workOnRepository.updateWorkOn(new WorkOn(ID, taskID, projectID, null, createDate, endDate, statusID));
    }

    public void searchWork(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String txtSearch = (String)req.getParameter("txt");
        ArrayList<WorkOn> list = workOnRepository.searchWorkByName(txtSearch);
        PrintWriter pw = resp.getWriter();
        for(WorkOn w : list){
            pw.println(" <tr>\n" +
                    "                                        <td>"+ w.getWorkOnID() +"</td>\n" +
                    "                                        <td>"+ w.getTaskID() +"</td>\n" +
                    "                                        <td>"+ w.getProjectID() +"</td>\n" +
                    "                                        <td>"+ w.getEmployeeID() +"</td>\n" +
                    "                                        <td>"+ w.getCreateDate() +"</td>\n" +
                    "                                        <td>"+ w.getEndDate() +"</td>\n" +
                    "                                        <td>"+ w.getStatusID() +"</td>\n" +
                    "                                        <td style=\"display: flex;\">\n" +
                    "                                            <form action=\"work\" method=\"GET\" style=\"margin-left: 5px;\">\n" +
                    "                                                <input type=\"hidden\" name=\"updateID\" value=\"${w.workOnID}\">\n" +
                    "                                                <button type=\"submit\" class=\"btn btn-sm btn-primary\">Sửa</button>\n" +
                    "                                            </form>\n" +
                    "                                            <form action=\"work\" method=\"GET\" style=\"margin-left: 5px;\">\n" +
                    "                                                <input type=\"hidden\" name=\"detailUserID\" value=\"${w.workOnID}\">\n" +
                    "                                                <button type=\"submit\" class=\"btn btn-sm btn-info\">Xem</button>\n" +
                    "                                            </form>\n" +
                    "                                            <form action=\"work\" method=\"GET\" style=\"margin-left: 5px;\">\n" +
                    "                                                <input type=\"hidden\" name=\"removeID\" value=\"${w.workOnID}\">\n" +
                    "                                                <button type=\"submit\" class=\"btn btn-sm btn-danger\">Xóa</button>\n" +
                    "                                            </form>\n" +
                    "                                        </td>\n" +
                    "                                    </tr>");
        }
        pw.close();
    }
}
