package repository;

import model.Role;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleRepository {
    public boolean addRole(Role role){
        Connection conn = MysqlConfig.getConnection();
        String sql = "INSERT INTO Role(name,createDate,`describe`) VALUES(?,?,?)";
        boolean isSucess = false;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,role.getName());
            ps.setString(2,role.getCreateDate());
            ps.setString(3,role.getDescribe());
            isSucess = ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return isSucess;
    }
    public ArrayList<Role> getAllRole(){
        Connection conn = MysqlConfig.getConnection();
        String sql = "SELECT * FROM Role";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ArrayList<Role> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Role r = new Role();
                r.setID(String.valueOf(rs.getInt("ID")));
                r.setName(rs.getString("name"));
                r.setCreateDate(rs.getString("createDate"));
                r.setDescribe(rs.getString("describe"));
                list.add(r);
            }
            boolean check = (list.size()>0) ? true : false;
            if(check) return list;
            else throw new RuntimeException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Role findRoleByID(int ID){
        Connection conn = MysqlConfig.getConnection();
        try {
            String sql = "SELECT * FROM Role WHERE ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,ID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Role(
                    String.valueOf(rs.getInt("ID")),
                    rs.getString("name"),
                    rs.getString("createDate"),
                    rs.getString("describe")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    public ArrayList<Role> searchRoleByName(String txtSearch){
        Connection conn = MysqlConfig.getConnection();
        try {
            String sql = "SELECT *  \n" +
                    "FROM `Role` r \n" +
                    "WHERE r.ID LIKE ? OR r.name LIKE ? OR r.describe LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"%" + txtSearch+ "%");
            ps.setString(2,"%" + txtSearch+ "%");
            ps.setString(3,"%" + txtSearch+ "%");
            ResultSet rs = ps.executeQuery();
            ArrayList<Role> list = new ArrayList<>();
            while(rs.next()){
                list.add(new Role(
                        String.valueOf(rs.getInt("ID")),
                        rs.getString("name"),
                        rs.getString("createDate"),
                        rs.getString("describe")
                ));
            }
            boolean check = list.size()>0;
            if(check) return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean updateRole(int ID, String name, String updateDate, String describe){
        Connection conn = MysqlConfig.getConnection();
        String sql = "UPDATE Role SET name = ?, createDate = ?, `describe` = ? WHERE ID = ?";
        boolean isSucess = false;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,updateDate);
            ps.setString(3,describe);
            ps.setInt(4,ID);
            isSucess=ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return isSucess;
    }

    public boolean removeRole(int ID){
        Connection conn = MysqlConfig.getConnection();
        String sql = "DELETE FROM Role WHERE ID = ?";
        boolean isSucess = false;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,ID);
            isSucess = ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return isSucess;
    }
}
