package repository;

import model.Project;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectRepository {
    private String sql = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ArrayList<Project> plist = null;

    public boolean addProject(Project p){
        Connection conn = MysqlConfig.getConnection();
        boolean isSucess = false;
        try {
            sql = "INSERT INTO Project(name,createDate,endDate) VALUES(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,p.getName());
            ps.setString(2,p.getCreateDate());
            ps.setString(3,p.getEndDate());
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
    public Project getProjectByID(int ID){
        Connection conn = MysqlConfig.getConnection();
        try {
            sql = "SELECT * FROM Project WHERE ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                return new Project(
                        String.valueOf(rs.getInt("ID")),
                        rs.getString("name"),
                        rs.getString("createDate"),
                        rs.getString("endDate")
                );
            }
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
        return null;
    }
    public ArrayList<Project> getAllProject(){
        Connection conn = MysqlConfig.getConnection();
        try {
            sql = "SELECT * FROM Project";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            plist = new ArrayList<>();
            while(rs.next()) {
                plist.add(new Project(
                        String.valueOf(rs.getInt("ID")),
                        rs.getString("name"),
                        rs.getString("createDate"),
                        rs.getString("endDate")
                ));
            }
            ps.close();
            boolean check = (plist.size()>0) ? true : false;
            if(check) return plist;
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

    public ArrayList<User> getAllWorkers(int projectID){
        Connection conn = MysqlConfig.getConnection();
        try {
            sql = "SELECT * FROM Project";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<User> ulist = new ArrayList<>();
            while(rs.next()) {
            }
            ps.close();
            boolean check = (plist.size()>0) ? true : false;
            if(check) return ulist;
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

    public ArrayList<Project> searchProjectByName(String txtSearch){
        Connection conn = MysqlConfig.getConnection();
        try {
            String sql = "SELECT * FROM Project p WHERE p.ID LIKE ? OR p.name LIKE ? OR p.createDate LIKE ? OR p.endDate LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"%" + txtSearch + "%");
            ps.setString(2,"%" + txtSearch + "%");
            ps.setString(3,"%" + txtSearch + "%");
            ps.setString(4,"%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            ArrayList<Project> list = new ArrayList<>();
            while(rs.next()){
                list.add(new Project(
                        String.valueOf(rs.getInt("ID")),
                        rs.getString("name"),
                        rs.getString("createDate"),
                        rs.getString("endDate")
                ));
            }
            boolean check = list.size()>0;
            if(check) return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public boolean updateProject(Project p){
        Connection conn = MysqlConfig.getConnection();
        boolean isSucess = false;
        try {
            sql = "UPDATE Project SET name = ?, createDate = ?, endDate = ? WHERE ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,p.getName());
            ps.setString(2,p.getCreateDate());
            ps.setString(3,p.getEndDate());
            ps.setInt(4,Integer.parseInt(p.getID()));
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

    public boolean removeProject(int ID){
        Connection conn = MysqlConfig.getConnection();
        boolean isSucess = false;
        try {
            sql = "DELETE FROM Project WHERE ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ID);
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
}
