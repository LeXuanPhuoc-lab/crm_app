package service;

import model.User;
import repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginService {
    private UserRepository userRepository = new UserRepository();
    private HttpSession session;
    public void logOut(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        if(session.getAttribute("admin")!=null){
            session.removeAttribute("admin");
        }else if(session.getAttribute("user")!=null){
            session.removeAttribute("user");
        }
    }

    public int logIn(String username, String password, HttpServletRequest req){
        session = req.getSession();

        // check if username/password is Empty --> send msg --> forward user back to login.jsp
        if(username.isEmpty() || password.isEmpty()){
            return -1;
        }else{
            // connection to DB and executeQuery to get user
            User user = userRepository.checkLogin(username,password);

            // check if user exist in DB --> foward them to index.jsp and
            if(user!=null){
                // setAttribute(user) to session --> this will use for links without login
                if(user.getRoleID().equals("1")){
                    session.setAttribute("admin",user);
                }else{
                    session.setAttribute("user",user);
                }
                return 1;
            }else{
                return 0;
            }
        }
    }
}
