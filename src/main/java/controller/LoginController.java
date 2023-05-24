package controller;

import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private LoginService loginService = new LoginService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loginService.logOut(req,resp);
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get user info to login
        String username = (String)req.getParameter("email");
        String password = (String)req.getParameter("password");

        int checkLogin = loginService.logIn(username, password,req);

        // -1 --> user give blank fields
        // 0 --> wrong username/password
        // 1 --> login sucess
        if(checkLogin==-1){
            req.setAttribute("msg","Please input username/password.");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }else if(checkLogin==0){
            // else --> not found in database
            // --> username/password is not wrong/exist --> send msg --> forward user back to login.jsp
            req.setAttribute("msg","Wrong username or password.");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }else{
            // set time exist for session Scope
            //session.setMaxInactiveInterval(10);
            // forward user to home.jsp
            resp.sendRedirect(req.getContextPath()+"/home"); // localhost:8080/crm_app/home
        }

    }
}
