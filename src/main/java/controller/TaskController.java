package controller;

import model.Task;
import service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/task","/task/add","/task/remove","/task/update","/task/search"})
public class TaskController extends HttpServlet {
    private TaskService taskService = new TaskService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch(path){
            case "/task":
                this.getAllTask(req,resp);
                break;
            case "/task/add":
                this.addTask(req,resp);
                break;
            case "/task/remove":
                this.removeTask(req,resp);
                break;
            case "/task/update":
                this.updateTask(req,resp);
                break;
            case "/task/search":
                this.searchTask(req,resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch(path){
            case "/task":
                break;
            case "/task/add":
                this.addTask(req,resp);
                break;
            case "/task/remove":
                break;
            case "/task/update":
                this.updateTask(req,resp);
                break;
        }
    }

    public void getAllTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        ArrayList<Task> tlist = taskService.getAllTask();

        if(tlist.size()>0 && tlist!=null){
            req.setAttribute("tasksList",tlist);
        }else{
            req.setAttribute("msg","Not found any Task.");
        }
        req.getRequestDispatcher("/task-table.jsp").forward(req,resp);
    }

    public void addTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String method = req.getMethod();

        if(method.equalsIgnoreCase("GET")){
            req.getRequestDispatcher("/task-add.jsp").forward(req,resp);
        }else{
            String name = (String)req.getParameter("name");

            if(!name.isEmpty()){
                taskService.addTask(name);
                resp.sendRedirect(req.getContextPath() + "/task");
            }else{
                req.setAttribute("msg","Please don't give blank fields.");
                req.getRequestDispatcher("/task-add.jsp").forward(req,resp);
            }
        }
    }

    public void removeTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String ID = (String)req.getParameter("id");
        taskService.removeTask(Integer.parseInt(ID));
    }

    public void updateTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String ID = (String)req.getParameter("id");
        String method = req.getMethod();

        if(method.equalsIgnoreCase("GET")){
            req.setAttribute("task",taskService.getTaskByID(Integer.parseInt(ID)));
            req.getRequestDispatcher("/task-update.jsp").forward(req,resp);
        }else{
            String name = (String)req.getParameter("name");
            if(name.isEmpty()){
                req.setAttribute("message","Please don't give blank fields!");
            }else{
                taskService.updateTask(Integer.parseInt(ID), name);
             }
            resp.sendRedirect(req.getContextPath() + "/task");
        }
    }

    public void searchTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        taskService.searchTask(req, resp);
    }
}
