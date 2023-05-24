package controller;

import model.*;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/user","/user/add",
        "/user/remove", "/user/update",
        "/user/work","/user/search",
        "/user/profile","/user/profile/edit",
        "/user/avartar"})
public class UserController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch(path){
            case "/user":
                this.getAllUser(req,resp);
                break;
            case "/user/add":
                this.addUser(req,resp);
                break;
            case "/user/remove":
                this.removeUser(req,resp);
                break;
            case "/user/update":
                this.updateUser(req,resp);
                break;
            case "/user/work":
                this.userWork(req,resp);
                break;
            case "/user/search":
                this.searchUser(req,resp);
                break;
            case "/user/profile":
                this.showUserProfile(req,resp);
                break;
            case "/user/profile/edit":
                this.editUserProfile(req,resp);
                break;
            case "/user/avartar":
                this.userAvartar(req,resp);
                break;
            default:
                req.getRequestDispatcher("404.jsp").forward(req,resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch(path){
            case "/user":
                break;
            case "/user/add":
                this.addUser(req,resp);
                break;
            case "/user/remove":
                break;
            case "/user/update":
                this.updateUser(req,resp);
                break;
            case "/user/profile":
                break;
            case "/user/profile/edit":
                this.editUserProfile(req,resp);
                break;
            case "/user/avartar":
                this.userAvartar(req,resp);
                break;
            default:
                req.getRequestDispatcher("/404.jsp").forward(req,resp);
                break;
        }
    }


    public void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        userService.getAllUser(req,resp);
    }


    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String method = req.getMethod();

        if(method.equalsIgnoreCase("GET")){
            ArrayList<Country> clist = userService.getAllCountry();
            ArrayList<Role> rlist = userService.getAllRole();
            req.setAttribute("countryList",clist);
            req.setAttribute("roleList",rlist);
            req.getRequestDispatcher("/user-add.jsp").forward(req,resp);
        }else{
            String email = (String)req.getParameter("email");
            String password = (String)req.getParameter("password");
            String firstname = (String)req.getParameter("firstname");
            String lastname = (String)req.getParameter("lastname");
            String phone = (String)req.getParameter("phone");
            String country = (String)req.getParameter("country");
            String role = (String)req.getParameter("role");

            int checkAddUser = userService.addUser(email, password, firstname, lastname, phone, country, role);
            if(checkAddUser==-1){
                req.setAttribute("msg","Please don't give blank fields");
                resp.sendRedirect(req.getContextPath() + "/user/add");
                return;
            }else if(checkAddUser==1){
                resp.sendRedirect(req.getContextPath() + "/user");
            }else{
                System.out.println("Add Fail!");
            }
        }
    }

    public void removeUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String ID = (String)req.getParameter("id");
        boolean removeFail = userService.removeUser(ID);
        if(removeFail){
            req.setAttribute("msg", "Remove Fail.");
        }
    }

    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String method = req.getMethod();

        if(method.equalsIgnoreCase("get")){
            String ID = (String)req.getParameter("id");
            User user = userService.findUserByID(Integer.parseInt(ID));
            ArrayList<Country> clist = userService.getAllCountry();
            ArrayList<Role> rlist = userService.getAllRole();

            req.setAttribute("countryList",clist);
            req.setAttribute("roleList",rlist);
            req.setAttribute("userInfo",user);
            req.getRequestDispatcher("/user-update.jsp").forward(req,resp);
        }else{
            String ID = (String)req.getParameter("oldID");
            String newFirstname = (String)req.getParameter("newFirstname");
            String newLastname = (String)req.getParameter("newLastname");
            String newUsername = (String)req.getParameter("newUsername");
            String newRole = (String)req.getParameter("newRole");
            String newCountry = (String)req.getParameter("newCountry");

            if(newFirstname.isEmpty() || newLastname.isEmpty() || newUsername.isEmpty() || newRole.isEmpty()){
                req.setAttribute("msg","Please dont't give blank fields");
                resp.sendRedirect(req.getContextPath() + "/user/update?id="+ ID);
                return;
            }else{
                boolean isSucess = userService.updateUser(ID,newFirstname, newLastname, newUsername, newRole, newCountry);

                if(!isSucess){
                    req.setAttribute("msg","Update Fail.");
                }
                resp.sendRedirect(req.getContextPath() + "/user");
            }
        }
    }

    public void userWork(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String ID = (String)req.getParameter("id");
        userService.getAllWorkofUser(Integer.parseInt(ID), req, resp);
    }

    public void searchUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        userService.searchUser(req, resp);
    }

    public void showUserProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String ID = (String)req.getParameter("id");
        userService.showUserProfile(Integer.parseInt(ID), req,resp);
    }

    public void editUserProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String ID = (String)req.getParameter("id");
        String method = req.getMethod();

        if(method.equalsIgnoreCase("GET")){
            // update Work Profile
            WorkOn w = userService.getWorkForProfileEdit(Integer.parseInt(ID));

            // get All Status and forward to user-profile-edit.jsp
            ArrayList<Status> stList = userService.getAllStatus();

            // session Scope
            req.setAttribute("statusList",stList);
            req.setAttribute("updateProfile",w);
            req.getRequestDispatcher("/user-profile-edit.jsp").forward(req,resp);
        }else{
            String newStatusID = (String)req.getParameter("newStatusID");
            userService.updateWorkStatus(Integer.parseInt(ID),Integer.parseInt(newStatusID));
            WorkOn w = userService.getWorkForProfileEdit(Integer.parseInt(ID));
            resp.sendRedirect(req.getContextPath() + "/user/profile?id="+w.getEmployeeID());
        }
    }

    public void userAvartar(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String ID = (String)req.getParameter("id");
        String method = req.getMethod();

        if(method.equalsIgnoreCase("GET")){
            req.setAttribute("userID",ID);
            req.getRequestDispatcher("/user-avartar-add.jsp").forward(req,resp);
        }else{
            String newAvartar = (String)req.getParameter("avartar");

            if(newAvartar.isEmpty()){
                req.setAttribute("msg","Please input!");
                resp.sendRedirect(req.getContextPath() + "/user/avartar?id=" + ID);
            }else{
                userService.addAvartar(Integer.parseInt(ID),newAvartar);
                resp.sendRedirect(req.getContextPath() + "/user/profile?id=" + ID);
            }
        }
    }
}
