package controller;

import service.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/project","/project/add","/project/work","/project/remove","/project/update","/project/search"})
public class ProjectController extends HttpServlet {

    private ProjectService projectService = new ProjectService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch(path){
            case "/project":
                this.getAllProject(req,resp);
                break;
            case "/project/add":
                this.addProject(req,resp);
                break;
            case "/project/work":
                this.getAllWorkInProject(req, resp);
                break;
            case "/project/remove":
                this.removeProject(req,resp);
                break;
            case "/project/update":
                this.updateProject(req,resp);
                break;
            case "/project/search":
                this.searchProject(req,resp);
                break;
        }
    }

    private void getAllWorkInProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String ID = (String)req.getParameter("id");
        projectService.getAllWorkInProject(Integer.parseInt(ID), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch(path){
            case "/project":
                this.getAllProject(req,resp);
                break;
            case "/project/add":
                this.addProject(req,resp);
                break;
            case "/project/remove":
                break;
            case "/project/update":
                this.updateProject(req,resp);
                break;
        }
    }

    public void getAllProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        this.projectService.getAllProject(req,resp);
    }

    public void addProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String method = req.getMethod();

        if(method.equalsIgnoreCase("GET")){
            req.getRequestDispatcher("/project-add.jsp").forward(req,resp);
        }else{
            String name = (String)req.getParameter("name");
            String createDate = (String)req.getParameter("createDate");
            String endDate = (String)req.getParameter("endDate");

            if(name.isEmpty() || createDate.isEmpty() || endDate.isEmpty()){
                req.setAttribute("msg","Please don't give blank fields.");
            }else{
                boolean addFail = projectService.addProject(name, createDate, endDate);

                if(!addFail){
                    resp.sendRedirect(req.getContextPath() + "/project");
                }else{
                    System.out.println("Add Project Fail.");
                }
            }
        }
    }
    public void removeProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String ID = (String)req.getParameter("id");
        boolean removeFail = projectService.removeProject(Integer.parseInt(ID));

        if(removeFail){
            req.setAttribute("msg","Remove Fail.");
        }
        req.getRequestDispatcher("/project-table.jsp").forward(req,resp);
    }

    public void updateProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String ID = (String)req.getParameter("id");
        String method = req.getMethod();

        if(method.equalsIgnoreCase("GET")){
            req.setAttribute("project", projectService.findProjectByID(Integer.parseInt(ID)));
            req.getRequestDispatcher("/project-update.jsp").forward(req, resp);
        }else{
            String name = (String)req.getParameter("name");
            String createDate = (String)req.getParameter("createDate");
            String endDate = (String)req.getParameter("endDate");

            boolean updateFail = projectService.updateProject(ID, name, createDate, endDate);

            if(!updateFail){
                resp.sendRedirect(req.getContextPath() + "/project");
            }else{
                System.out.println("Update Project Fail.");
            }
        }
    }

    public void searchProject(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        this.projectService.searchProject(req, resp);
    }
}
