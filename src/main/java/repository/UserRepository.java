package repository;

import model.User;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository {
    public boolean addUser(User user){
        Connection conn = MysqlConfig.getConnection();
        String sql = "INSERT INTO Employee(firstname,lastname,username,password,phone,roleID,countryID)" +
                "VALUES(?,?,?,?,?,?,?)";
        boolean isSucess = false;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getFirstname());
            ps.setString(2,user.getLastname());
            ps.setString(3,user.getUsername());
            ps.setString(4,user.getPassword());
            ps.setString(5,user.getPhone());
            ps.setInt(6,Integer.parseInt(user.getRoleID()));
            ps.setInt(7,Integer.parseInt(user.getCountryID()));
            isSucess = ps.execute();
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
        return isSucess;
    }

    public void addUserAvartar(int userID, String avartar){
        Connection conn = MysqlConfig.getConnection();
        String sql = "UPDATE Employee SET avartar = ? WHERE ID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,avartar);
            ps.setInt(2,userID);
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
    public User checkLogin(String username, String password){
        Connection conn = MysqlConfig.getConnection();
        User user = null;
        String sql = "SELECT * FROM Employee WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = new User();
                user.setID(String.valueOf(rs.getInt("ID")));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setAvartar(rs.getString("avartar"));
                user.setRoleID(String.valueOf(rs.getInt("roleID")));
                user.setCountryID(String.valueOf(rs.getInt("countryID")));
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
        return user;
    }

    public ArrayList<User> searchUserByName(String txtSearch){
        Connection conn = MysqlConfig.getConnection();
        try {
            String sql = "SELECT e.ID, e.firstname ,e.lastname ,e.username ,r.name AS roleName\n" +
                    "FROM Employee e \n" +
                    "LEFT JOIN `Role` r ON e.roleID = r.ID \n" +
                    "WHERE e.firstname LIKE ? OR e.lastname LIKE ? OR e.username LIKE ? OR e.ID LIKE ? OR r.name LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"%" + txtSearch +"%");
            ps.setString(2,"%" + txtSearch +"%");
            ps.setString(3,"%" + txtSearch +"%");
            ps.setString(4,"%" + txtSearch +"%");
            ps.setString(5,"%" + txtSearch +"%");
            ResultSet rs = ps.executeQuery();
            ArrayList<User> list = new ArrayList<>();
            while(rs.next()){
                User user = new User();
                user.setID(String.valueOf(rs.getInt("ID")));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setUsername(rs.getString("username"));
                user.setRoleID(rs.getString("roleName"));
                list.add(user);
            }
            boolean check = list.size()>0;
            if(check) return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public User getUser(int userID){
        Connection conn = MysqlConfig.getConnection();
        User user = null;
        String sql = "SELECT * FROM Employee WHERE ID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,userID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = new User();
                user.setID(String.valueOf(rs.getInt("ID")));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setAvartar(rs.getString("avartar"));
                user.setRoleID(String.valueOf(rs.getInt("roleID")));
                user.setCountryID(String.valueOf(rs.getInt("countryID")));
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
        return user;
    }

    // get all user
    public ArrayList<User> getAllUser(){
        Connection conn = MysqlConfig.getConnection();
        try {
            String sql = "SELECT e.ID,e.firstname,e.lastname,e.username, r.describe AS roleID \n" +
                    "FROM Employee e\n" +
                    "LEFT JOIN Role r\n" +
                    "ON e.roleID = r.ID \n" +
                    "LEFT JOIN Country c \n" +
                    "ON e.countryID = c.ID";
            PreparedStatement ps = conn.prepareStatement(sql);
            ArrayList<User> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setID(String.valueOf(rs.getInt("ID")));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setUsername(rs.getString("username"));
                user.setRoleID(rs.getString("roleID"));
                list.add(user);
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

    public ArrayList<User> UserPaging(int beginIndex, int endIndex){
        Connection conn = MysqlConfig.getConnection();
        try {
            String sql = "SELECT e.ID,e.firstname,e.lastname,e.username, r.describe AS roleName FROM Employee e \n" +
                    "LEFT JOIN Role r ON e.roleID = r.ID \n" +
                    "LEFT JOIN Country c ON e.countryID = c.ID \n" +
                    "ORDER BY e.ID ASC\n" +
                    "LIMIT ?,?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, beginIndex);
            ps.setInt(2, endIndex);
            ResultSet rs = ps.executeQuery();
            ArrayList<User> list = new ArrayList<>();
            while(rs.next()){
                list.add(new User(
                        String.valueOf(rs.getInt("ID")),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("username"),
                        rs.getString("roleName")
                ));
            }
            boolean check = list.size()>0;
            if(check) return list;
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

    public boolean updateUser(int userID, String firstname, String lastname, String username, int roleID, int countryID){
        Connection conn = MysqlConfig.getConnection();
        boolean isSucess = false;
        try {
            String sql = "UPDATE Employee SET firstname = ?, lastname = ?, username = ?, roleID = ?, countryID = ? WHERE ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,firstname);
            ps.setString(2,lastname);
            ps.setString(3, username);
            ps.setInt(4,roleID);
            ps.setInt(5,countryID);
            ps.setInt(6,userID);
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

    public boolean removeUser(String userID){
        Connection conn = MysqlConfig.getConnection();
        boolean isSucess = false;
        try {
            String sql = "DELETE FROM Employee WHERE ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,userID);
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

    public int getTotalUser(){
        Connection conn = MysqlConfig.getConnection();
        try {
            String sql = "SELECT count(*) FROM Employee";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
