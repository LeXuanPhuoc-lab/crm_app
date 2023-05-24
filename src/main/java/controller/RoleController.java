package controller;

import model.Role;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/role", "/role/add", "/role/remove","/role/update","/role/search"})
public class RoleController extends HttpServlet {
    private RoleService roleService = new RoleService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch(path){
            case "/role":
                this.getAllRole(req,resp);
                break;
            case "/role/add":
                this.addRole(req,resp);
                break;
            case "/role/remove":
                this.removeRole(req,resp);
                break;
            case "/role/update":
                this.updateRole(req,resp);
                break;
            case "/role/search":
                this.searchByName(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch(path){
            case "/role":
                this.getAllRole(req,resp);
                break;
            case "/role/add":
                this.addRole(req,resp);
                break;
            case "/role/remove":
                break;
            case "/role/update":
                this.updateRole(req,resp);
                break;
        }
    }

    public void getAllRole(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        roleService.getAllRole(req, resp);
    }

    public void addRole(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String method = req.getMethod();

        if(method.equalsIgnoreCase("GET")){
            req.getRequestDispatcher("/role-add.jsp").forward(req,resp);
        }else{
            String name = (String)req.getParameter("name");
            String createDate = (String)req.getParameter("createDate");
            String describe = (String)req.getParameter("describe");

            if(name.isEmpty() || createDate.isEmpty() || describe.isEmpty()){
                req.setAttribute("msg","Please don't give blank fields");
                resp.sendRedirect(req.getContextPath() + "/role/add");
            }else{
                boolean addFail = roleService.addRole(name,createDate,describe);

                if(!addFail){
                    resp.sendRedirect(req.getContextPath() + "/role");
                }else{
                    System.out.println("Add Role Fail, Please check Repository.");
                }
            }
        }
    }

    public void removeRole(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String ID = (String)req.getParameter("id");
        boolean removeFail = roleService.removeRole(Integer.parseInt(ID));

        if(removeFail){
            req.setAttribute("msg","Remove Fail.");
        }
    }

    public void updateRole(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String ID = (String)req.getParameter("id");
        String method = req.getMethod();

        if(method.equalsIgnoreCase("GET")){
            Role r = roleService.findRoleByID(Integer.parseInt(ID));
            req.setAttribute("role",r);
            req.getRequestDispatcher("/role-update.jsp").forward(req,resp);
        }else{
            String name = (String)req.getParameter("name");
            String createDate = (String)req.getParameter("createDate");
            String describe = (String)req.getParameter("describe");

            if(name.isEmpty() || createDate.isEmpty() || describe.isEmpty()){
                Role r = roleService.findRoleByID(Integer.parseInt(ID));
                req.setAttribute("role",r);
                req.setAttribute("msg","Please don't give blank fields");
                req.getRequestDispatcher("/role-update.jsp").forward(req,resp);
            }else{
                boolean updateFail = roleService.updateRole(Integer.parseInt(ID),name,createDate,describe);

                if(!updateFail){
                    resp.sendRedirect(req.getContextPath() + "/role");
                }else{
                    System.out.println("Update Fail.");
                }
            }
        }
    }

    public void searchByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        roleService.searchByName(req, resp);
    }
}
