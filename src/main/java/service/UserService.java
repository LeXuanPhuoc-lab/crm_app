package service;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import model.*;
import repository.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    private RoleRepository roleRepository = new RoleRepository();
    private CountryRepository countryRepository = new CountryRepository();
    private WorkOnRepository workOnRepository = new WorkOnRepository();
    private StatusRepository statusRepository = new StatusRepository();

    public ArrayList<Country> getAllCountry(){
        return countryRepository.getAllCountry();
    }

    public ArrayList<Role> getAllRole(){
        return roleRepository.getAllRole();
    }

    public ArrayList<Status> getAllStatus(){return statusRepository.getAllStatus();}

    public User findUserByID(int ID){
        return userRepository.getUser(ID);
    }

    public WorkOn getWorkForProfileEdit(int ID){
        return workOnRepository.getWorkByJoin(ID);
    }
    public void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageIndex = (String)req.getParameter("pageIndex");

        ArrayList<User> ulist = null;
        if(pageIndex==null){
            ulist = userRepository.UserPaging(0,3);
        }else{
            int index = Integer.parseInt(pageIndex);
            req.setAttribute("tag",index);
            ulist = userRepository.UserPaging((index-1)*3,3);
        }

        ArrayList<Role> rlist = roleRepository.getAllRole();
        int totalUser = userRepository.getTotalUser();

        int endPage = totalUser/3;
        if(totalUser % 3 !=0){
            endPage++;
        }
        if(ulist.size()<0){
            req.setAttribute("msg","Not Found Any User.");
        }

        // setAttribute to Request Scope and forward to user-table.jsp
        req.setAttribute("endP",endPage);
        req.setAttribute("usersList",ulist);
        req.setAttribute("rolesList",rlist);
        req.getRequestDispatcher("user-table.jsp").forward(req,resp);
    }

    public int addUser(String email, String password,
                        String firstname, String lastname,
                        String phone, String country, String role){
        if(email.isEmpty() || password.isEmpty()
                || firstname.isEmpty() || lastname.isEmpty()
                || phone.isEmpty() || role.isEmpty()){
            return -1;
        }else{
            User user = new User(firstname, lastname, email, password, phone, role, country);
            boolean addFail = userRepository.addUser(user);

            if(!addFail){
                return 1;
            }else{
                return 0;
            }
        }
    }

    public boolean removeUser(String ID){
        return userRepository.removeUser(ID);
    }

    public boolean updateUser(String ID, String newFirstname, String newLastname, String newUsername, String newRole, String newCountry){
        int userID = Integer.parseInt(ID);
        int roleID = Integer.parseInt(newRole);
        int countryID = Integer.parseInt(newCountry);
        boolean updateFail = userRepository.updateUser(userID, newFirstname, newLastname, newUsername, roleID, countryID);

        if(!updateFail){
            return true;
        }else{
            return false;
        }
    }

    public void getAllWorkofUser(int ID, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<WorkOn> wlist = workOnRepository.getUserWorks(ID);

        if(wlist!=null){
            // get User work percentage status
            WorkPercentage wp = new WorkPercentage(wlist);

            // check if user have no work, send message

            // ArrayList number of 3 work status percentage
            ArrayList<Integer> perList = wp.getPerList();

            // 3 ArrayList of working status of a user
            // List of not done work
            ArrayList<WorkOn> notDoneList = new ArrayList<>();
            // list of is doing work
            ArrayList<WorkOn> isDoingList = new ArrayList<>();
            // list of done work
            ArrayList<WorkOn> doneList = new ArrayList<>();

            // loop through all work to distribute 3 type of  user works status
            for(WorkOn w : wlist){
                if(w.getStatusID().equals("Chưa Thực Hiện")){
                    notDoneList.add(w);
                }else if(w.getStatusID().equals("Đang thực hiện")){
                    isDoingList.add(w);
                }else{
                    doneList.add(w);
                }
            }

            // check if any list is empty --> not set Attribute to Request scope
            boolean notDone = (notDoneList.size()>0) ? true : false;
            boolean isDoing = (isDoingList.size()>0) ? true : false;
            boolean done = (doneList.size()>0) ? true : false;

            // send user work status percentage
            req.setAttribute("perList",perList);

            // check if any of 3 list are not empty, set attribute to Request
            if(notDone){
                req.setAttribute("notDoneList",notDoneList);
            }
            if(isDoing){
                req.setAttribute("isDoingList",isDoingList);
            }
            if(done){
                req.setAttribute("doneList",doneList);
            }

            // send userInfo that get userID from parameter
            req.setAttribute("userInfo",userRepository.getUser(ID));
        }else{
            req.setAttribute("msg","Not found any work");
        }
        req.getRequestDispatcher("/user-detail.jsp").forward(req,resp);
    }

    public int getTotalUser(){
        return userRepository.getTotalUser();
    }


    public void searchUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String txtSearch = (String)req.getParameter("txt");
        ArrayList<User> ulist = userRepository.searchUserByName(txtSearch);
        PrintWriter pw = resp.getWriter();
        for(User u : ulist){
            pw.println("<tr>\n" +
                    "                                                    <td>" + u.getID() +"</td>\n" +
                    "                                                    <td>"+ u.getFirstname() +"</td>\n" +
                    "                                                    <td>"+ u.getLastname() +"</td>\n" +
                    "                                                    <td>" + u.getUsername() +"</td>\n" +
                    "                                                    <td>"+ u.getRoleID() +"</td>\n" +
                    "                                                    <td style=\"display: flex;\">\n" +
                    "                                                        <form action=\"user\" method=\"GET\">\n" +
                    "                                                            <input type=\"hidden\" value=\"${user.ID}\" name=\"updateID\">\n" +
                    "                                                            <button type=\"submit\" class=\"btn btn-sm btn-primary\" >Sửa</button>\n" +
                    "                                                        </form>\n" +
                    "                                                        <form action=\"user\" method=\"GET\" style=\"margin-left: 5px;\">\n" +
                    "                                                            <input type=\"hidden\" name=\"detailUserID\" value=\"${user.ID}\">\n" +
                    "                                                            <button type=\"submit\" class=\"btn btn-sm btn-info\">Xem</button>\n" +
                    "                                                        </form>\n" +
                    "                                                        <form action=\"user\" method=\"GET\" style=\"margin-left: 5px;\">\n" +
                    "                                                            <input type=\"hidden\" name=\"removeUserID\" value=\"${user.ID}\">\n" +
                    "                                                            <button type=\"submit\" class=\"btn btn-sm btn-danger\">Xóa</button>\n" +
                    "                                                        </form>\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>");
        }
        pw.close();
    }

    public void showUserProfile(int ID, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get all work of User
        ArrayList<WorkOn> wlist = workOnRepository.getUserWorks(ID);

        // check if work list != null --> this employee have job in company
        // else announce they have no job
        if(wlist!=null){
            // get all Percentage of user work
            WorkPercentage wp = new WorkPercentage(wlist);
            ArrayList<Integer> perList = wp.getPerList();

            req.setAttribute("percentageList",perList);
            req.setAttribute("userWorks",wlist);
        }else{
            req.setAttribute("msg","Not found any work");
        }
        req.getRequestDispatcher("/user-profile.jsp").forward(req,resp);
    }

    public void updateWorkStatus(int ID, int statusID) {
        workOnRepository.updateWorkStatus(ID, statusID);
    }

    public void addAvartar(int ID, String newAvartar) {
        userRepository.addUserAvartar(ID, newAvartar);
    }
}
