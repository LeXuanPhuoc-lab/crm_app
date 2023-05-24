package repository;

import model.User;
import model.WorkOn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkOnRepository {
    private String sql = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ArrayList<WorkOn> wlist;
    public boolean addWorkOn(WorkOn w){
        Connection conn = MysqlConfig.getConnection();
        boolean isSucess = false;
        try {
            sql = "INSERT INTO Work_On(projectID,taskID,employeeID,createDate,endDate,statusID) VALUES(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);

            // convert String to number
            int projectID = Integer.parseInt(w.getProjectID());
            int taskID = Integer.parseInt(w.getTaskID());
            int employeeID = Integer.parseInt(w.getEmployeeID());
            int statusID = Integer.parseInt(w.getStatusID());
            // set parameter to sql statement
            ps.setInt(1,projectID);
            ps.setInt(2,taskID);
            ps.setInt(3,employeeID);
            ps.setString(4,w.getCreateDate());
            ps.setString(5,w.getEndDate());
            ps.setInt(6,statusID);

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

    public WorkOn getWork(String workOnID){
        Connection conn = MysqlConfig.getConnection();
        try {
            sql = "SELECT * FROM Work_On WHERE workOnID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(workOnID));
            rs = ps.executeQuery();
            while(rs.next()){
                return new WorkOn(
                        String.valueOf(rs.getInt("workOnID")),
                        String.valueOf(rs.getInt("taskID")),
                        String.valueOf(rs.getInt("projectID")),
                        String.valueOf(rs.getInt("employeeID")),
                        rs.getString("createDate"),
                        rs.getString("endDate"),
                        String.valueOf(rs.getInt("statusID"))
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
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    public WorkOn getWorkByJoin(int workOnID){
        Connection conn = MysqlConfig.getConnection();
        try {
            sql = "SELECT w.workOnID AS workID,t.name AS taskName, p.name AS projectName, e.ID AS employeeID, w.createDate, w.endDate, s.name AS statusName\n" +
                    "FROM Work_On w \n" +
                    "LEFT JOIN Project p ON w.projectID = p.ID \n" +
                    "LEFT JOIN Task t ON w.taskID = t.ID \n" +
                    "LEFT JOIN Employee e ON w.employeeID = e.ID \n" +
                    "LEFT JOIN Status s ON w.statusID = s.ID \n" +
                    "WHERE w.workOnID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,workOnID);
            rs = ps.executeQuery();
            while(rs.next()){
                return new WorkOn(
                        String.valueOf(rs.getInt("workID")),
                        rs.getString("taskName"),
                        rs.getString("projectName"),
                        rs.getString("employeeID"),
                        rs.getString("createDate"),
                        rs.getString("endDate"),
                        rs.getString("statusName")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<User> getAllUserByProjectID(int projectID){
        Connection conn = MysqlConfig.getConnection();
        try {
            sql = "SELECT DISTINCT e.ID AS employeeID, e.firstname, e.lastname, e.username, e.avartar,e.phone  \n" +
                    "FROM Work_On w \n" +
                    "LEFT JOIN Project p ON w.projectID = p.ID \n" +
                    "LEFT JOIN Task t ON w.taskID = t.ID \n" +
                    "LEFT JOIN Employee e ON w.employeeID = e.ID \n" +
                    "LEFT JOIN Status s ON w.statusID = s.ID \n" +
                    "WHERE p.ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,projectID);
            rs = ps.executeQuery();
            ArrayList<User> list = new ArrayList<>();
            while(rs.next()){
                list.add(new User(
                        String.valueOf(rs.getInt("employeeID")),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("username"),
                        rs.getString("avartar"),
                        rs.getString("phone")
                ));
            }
            boolean check = list.size()>0;
            if(check) return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public int getUserIDByWorkOnID(int workOnID){
        Connection conn = MysqlConfig.getConnection();
        try {
            String sql = "SELECT w.employeeID AS userID FROM Work_On w WHERE w.workOnID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,workOnID);
            rs = ps.executeQuery();
            while(rs.next()){
                return rs.getInt("userID");
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
        return -1;
    }
    public ArrayList<WorkOn> getUserWorks(int userID){
        Connection conn = MysqlConfig.getConnection();
        try {
            sql = "SELECT w.workOnID AS workID,t.name AS taskName, p.name AS projectName, e.ID AS employeeID, w.createDate, w.endDate, s.name AS statusName\n" +
                    "FROM Work_On w \n" +
                    "LEFT JOIN Project p ON w.projectID = p.ID \n" +
                    "LEFT JOIN Task t ON w.taskID = t.ID \n" +
                    "LEFT JOIN Employee e ON w.employeeID = e.ID \n" +
                    "LEFT JOIN Status s ON w.statusID = s.ID \n" +
                    "WHERE e.ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,userID);
            rs = ps.executeQuery();
            ArrayList<WorkOn> list = new ArrayList<>();
            while(rs.next()){
                list.add(new WorkOn(
                        String.valueOf(rs.getInt("workID")),
                        rs.getString("taskName"),
                        rs.getString("projectName"),
                        rs.getString("employeeID"),
                        rs.getString("createDate"),
                        rs.getString("endDate"),
                        rs.getString("statusName")
                ));
            }
            boolean check = list.size()>0;
            if(check) return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<WorkOn> getAllUserWork(){
        Connection conn = MysqlConfig.getConnection();
        try {
            sql = "SELECT w.workOnID, p.name AS projectName, t.name AS taskName,\n" +
                    "concat(e.firstname,' ', e.lastname) AS employeeName,\n" +
                    "s.name AS statusName, w.createDate , w.endDate \n" +
                    "FROM Work_On w \n" +
                    "LEFT JOIN Project p ON w.projectID = p.ID \n" +
                    "LEFT JOIN Task t ON w.taskID = t.ID \n" +
                    "LEFT JOIN Employee e ON w.employeeID = e.ID \n" +
                    "LEFT JOIN Status s ON w.statusID = s.ID ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            wlist = new ArrayList<>();

            while(rs.next()){
                wlist.add(new WorkOn(
                        String.valueOf(rs.getInt("workOnID")),
                        rs.getString("taskName"),
                        rs.getString("projectName"),
                        rs.getString("employeeName"),
                        rs.getString("createDate"),
                        rs.getString("endDate"),
                        rs.getString("statusName")
                ));
            }

            boolean check = wlist.size()>0;
            if(check) return wlist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<WorkOn> getAllUserWorkByProjectID(int projectID){
        Connection conn = MysqlConfig.getConnection();
        try {
            sql = "SELECT w.workOnID, p.name AS projectName, t.name AS taskName,\n" +
                    "e.ID AS employeeID,\n" +
                    "s.ID AS statusID, w.createDate , w.endDate \n" +
                    "FROM Work_On w \n" +
                    "LEFT JOIN Project p ON w.projectID = p.ID \n" +
                    "LEFT JOIN Task t ON w.taskID = t.ID \n" +
                    "LEFT JOIN Employee e ON w.employeeID = e.ID \n" +
                    "LEFT JOIN Status s ON w.statusID = s.ID \n" +
                    "WHERE p.ID = ?\n" +
                    "ORDER BY w.employeeID ASC";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,projectID);
            rs = ps.executeQuery();
            wlist = new ArrayList<>();

            while(rs.next()){
                wlist.add(new WorkOn(
                        String.valueOf(rs.getInt("workOnID")),
                        rs.getString("taskName"),
                        rs.getString("projectName"),
                        rs.getString("employeeID"),
                        rs.getString("createDate"),
                        rs.getString("endDate"),
                        rs.getString("statusID")
                ));
            }

            boolean check = wlist.size()>0;
            if(check) return wlist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<WorkOn> searchWorkByName(String txtSearch){
        Connection conn = MysqlConfig.getConnection();
        try {
            String sql = "SELECT w.workOnID as ID, t.name as taskName, p.name as projectName,\n" +
                    "concat(e.firstname,' ',e.lastname) AS employeeName,\n" +
                    "w.createDate, w.endDate, s.name AS statusName\n" +
                    "FROM Work_On w \n" +
                    "LEFT JOIN Employee e ON w.employeeID = e.ID \n" +
                    "LEFT JOIN Project p ON w.projectID = p.ID \n" +
                    "LEFT JOIN Task t ON w.taskID = t.ID \n" +
                    "LEFT JOIN Status s ON w.statusID = s.ID \n" +
                    "WHERE p.name LIKE ? \n" +
                    "OR t.name LIKE ? \n" +
                    "OR e.firstname LIKE ? \n" +
                    "OR e.lastname LIKE ?\n" +
                    "OR s.name LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setString(2, "%" + txtSearch + "%");
            ps.setString(3, "%" + txtSearch + "%");
            ps.setString(4, "%" + txtSearch + "%");
            ps.setString(5, "%" + txtSearch + "%");
            ArrayList<WorkOn> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new WorkOn(
                        String.valueOf(rs.getInt("ID")),
                        rs.getString("taskName"),
                        rs.getString("projectName"),
                        rs.getString("employeeName"),
                        rs.getString("createDate"),
                        rs.getString("endDate"),
                        rs.getString("statusName")
                ));
            }

            boolean check = list.size() > 0;
            if(check) return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public boolean updateWorkOn(WorkOn w){
        Connection conn = MysqlConfig.getConnection();
        boolean isSucess = false;
        try {
            sql = "UPDATE Work_On SET taskID = ?, projectID = ?, createDate = ?, endDate = ?, statusID = ? WHERE workOnID = ?";
            ps = conn.prepareStatement(sql);

            // convert String to number
            int workOnID = Integer.parseInt(w.getWorkOnID());
            int projectID = Integer.parseInt(w.getProjectID());
            int taskID = Integer.parseInt(w.getTaskID());
            int statusID = Integer.parseInt(w.getStatusID());

            // set parameter to sql statement
            ps.setInt(1,taskID);
            ps.setInt(2,projectID);
            ps.setString(3,w.getCreateDate());
            ps.setString(4,w.getEndDate());
            ps.setInt(5,statusID);
            ps.setInt(6,workOnID);
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

    public void updateWorkStatus(int workOnID, int statusID){
        Connection conn = MysqlConfig.getConnection();
        try {
            sql = "UPDATE Work_On SET statusID = ? WHERE workOnID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,statusID);
            ps.setInt(2,workOnID);
            ps.execute();
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
    }
    public boolean removeWork(int workOnID){
        Connection conn = MysqlConfig.getConnection();
        boolean isSucess = false;
        try {
            sql = "DELETE FROM Work_On WHERE workOnID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,workOnID);
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
