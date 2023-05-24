package repository;

import model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskRepository {
    private String sql = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private ArrayList<Task> tlist = null;

    public void addTask(Task t){
        Connection conn = MysqlConfig.getConnection();
        try {
            sql = "INSERT INTO Task(name) VALUES(?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,t.getName());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public ArrayList<Task> getAllTask(){
        Connection conn = MysqlConfig.getConnection();
        try {
            sql = "SELECT * FROM Task";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            tlist = new ArrayList<>();
            while(rs.next()){
                tlist.add(new Task(
                        String.valueOf(rs.getInt("ID")),
                        rs.getString("name")
                ));
            }
            boolean check = tlist.size()>0;
            if(check) return tlist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
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

    public ArrayList<Task> searchTaskByName(String txtSearch){
        Connection conn = MysqlConfig.getConnection();
        try {
            String sql = "SELECT * FROM Task t WHERE t.ID LIKE ? OR t.name LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setString(2, "%" + txtSearch + "%");
            ArrayList<Task> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Task(
                        String.valueOf(rs.getInt("ID")),
                        rs.getString("name")
                ));
            }
            boolean check = list.size()>0;
            if(check) return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void updateTask(Task t){
        Connection conn = MysqlConfig.getConnection();
        try {
            sql = "UPDATE Task SET name = ? WHERE ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,t.getName());
            ps.setInt(2,Integer.parseInt(t.getID()));
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void removeTask(int ID){
        Connection conn = MysqlConfig.getConnection();
        try {
            sql = "DELETE FROM Task WHERE ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ID);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Task getTaskByID(int ID) {
        Connection conn = MysqlConfig.getConnection();
        try {
            sql = "SELECT * FROM Task WHERE ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Task(
                        String.valueOf(rs.getInt("ID")),
                        rs.getString("name")
                );
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
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
}
