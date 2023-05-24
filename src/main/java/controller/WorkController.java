package controller;

import model.WorkOn;
import service.WorkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/work","/work/add","/work/detail","/work/remove","/work/update","/work/search"})
public class WorkController extends HttpServlet {

    private WorkService workService = new WorkService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch(path){
            case "/work":
                req.setAttribute("workOnList", this.workService.getAllWork());
                req.getRequestDispatcher("work-table.jsp").forward(req, resp);
                break;
            case "/work/add":
                this.addWork(req, resp);
                break;
            case "/work/detail":
                this.workDetail(req, resp);
                break;
            case "/work/remove":
                this.removeWork(req, resp);
                break;
            case "/work/update":
                this.updateWork(req, resp);
                break;
            case "/work/search":
                this.searchWork(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch(path){
            case "/work":
                break;
            case "/work/add":
                this.addWork(req, resp);
                break;
            case "/work/detail":
                break;
            case "/work/remove":
                break;
            case "/work/update":
                this.updateWork(req, resp);
                break;
            case "/work/search":
                break;
        }
    }

    public void addWork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String method = req.getMethod();

        if(method.equalsIgnoreCase("GET")){
            workService.getAllWorkAddDetail(req, resp);

        }else{
            String taskID = (String)req.getParameter("taskID");
            String projectID = (String)req.getParameter("projectID");
            String employeeID = (String)req.getParameter("employeeID");
            String createDate = (String)req.getParameter("createDate");
            String endDate = (String)req.getParameter("endDate");
            String statusID = (String)req.getParameter("statusID");

            if(createDate.isEmpty() || endDate.isEmpty()){
                req.setAttribute("msg","Please don't give blank fields.");
                req.getRequestDispatcher("/work-add.jsp").forward(req,resp);
            }else {
                boolean addFail = this.workService.addWork(taskID, projectID, employeeID, createDate, endDate, statusID);

                if(!addFail){
                    resp.sendRedirect(req.getContextPath() + "/work");
                }else{
                    System.out.println("Add Work Fail. Check Repository");
                }
            }
        }
    }

    public void removeWork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String ID = (String)req.getParameter("id");

        boolean removeFail = workService.removeWork(Integer.parseInt(ID));

        if(removeFail){
            System.out.println("Remove Fail.");
        }
    }

    public void updateWork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String ID = (String)req.getParameter("id");
        String method = req.getMethod();

        if(method.equalsIgnoreCase("GET")){
            req.setAttribute("work", workService.getWorkByID(Integer.parseInt(ID)));
            req.setAttribute("usersList",workService.getAllUser());
            req.setAttribute("projectsList",workService.getAllProject());
            req.setAttribute("tasksList",workService.getAllTask());
            req.setAttribute("statusList",workService.getAllStatus());
            req.getRequestDispatcher("/work-update.jsp").forward(req, resp);
        }else{
            String taskID = (String)req.getParameter("taskID");
            String projectID = (String)req.getParameter("projectID");
            String createDate = (String)req.getParameter("createDate");
            String endDate = (String)req.getParameter("endDate");
            String statusID = (String)req.getParameter("statusID");

            if(createDate.isEmpty() || endDate.isEmpty()){
                req.setAttribute("work", workService.getWorkByID(Integer.parseInt(ID)));
                req.setAttribute("usersList",workService.getAllUser());
                req.setAttribute("projectsList",workService.getAllProject());
                req.setAttribute("tasksList",workService.getAllTask());
                req.setAttribute("statusList",workService.getAllStatus());
                req.setAttribute("msg","Please don't give blank fields.");
                req.getRequestDispatcher("/work-update.jsp").forward(req, resp);
            }else{
                boolean updateFail = workService.updateWork(ID, taskID, projectID, createDate, endDate, statusID);

                if(!updateFail){
                    resp.sendRedirect(req.getContextPath() + "/work");
                }else{
                    System.out.println("Update Work Fail.");
                }
            }
        }
    }

    private void searchWork(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        workService.searchWork(req, resp);
    }

    private void workDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ID = (String)req.getParameter("id");
        resp.sendRedirect(req.getContextPath() + "/user/work?id="+workService.getWorkByID(Integer.parseInt(ID)).getEmployeeID());
    }
}
