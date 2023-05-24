package service;

import model.Role;
import repository.RoleRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RoleService {
    private RoleRepository roleRepository = new RoleRepository();


    public void getAllRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Role> rlist =  roleRepository.getAllRole();

        if(rlist!=null || rlist.size()>0){
            req.setAttribute("rolesList", rlist);
        }else{
            req.setAttribute("msg","Not found any Role.");
        }

        req.getRequestDispatcher("role-table.jsp").forward(req,resp);
    }

    public Role findRoleByID(int roleID){
        return roleRepository.findRoleByID(roleID);
    }
    public boolean addRole(String name, String createDate, String describe){
        return roleRepository.addRole(new Role(name,createDate,describe));
    }

    public boolean removeRole(int ID){
        return roleRepository.removeRole(ID);
    }

    public boolean updateRole(int id, String name, String createDate, String describe){
        return roleRepository.updateRole(id, name, createDate, describe);
    }

    public void searchByName(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setContentType("text/html; charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            String txtSearch = req.getParameter("txt");
            ArrayList<Role> rlist = roleRepository.searchRoleByName(txtSearch);
            PrintWriter out = resp.getWriter();
            for(Role r : rlist){
                out.println("<tr>\n" +
                        "                                                <td>"+ r.getID() +"</td>\n" +
                        "                                                <td>"+ r.getName() +"</td>\n" +
                        "                                                <td>"+ r.getDescribe() +"</td>\n" +
                        "                                                <td style=\"display: flex;\">\n" +
                        "                                                        <form action=\"role\" method=\"GET\">\n" +
                        "                                                            <button type=\"submit\" class=\"btn btn-sm btn-primary\">Sửa</button>\n" +
                        "                                                        </form>\n" +
                        "                                                        <form action=\"role\" method=\"GET\">\n" +
                        "                                                            <button type=\"submit\" class=\"btn btn-sm btn-danger\" style=\"margin-left:5px;\">Xóa</button>\n" +
                        "                                                        </form>\n" +
                        "                                                </td>\n" +
                        "                                            </tr>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
