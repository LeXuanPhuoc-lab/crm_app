package repository;

import model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatusRepository {
    private String sql;
    private PreparedStatement ps;
    private ResultSet rs;
    private ArrayList<Status> stlist;
    public ArrayList<Status> getAllStatus(){
        Connection conn = MysqlConfig.getConnection();
        try {
            sql = "SELECT * FROM Status";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            stlist = new ArrayList<>();
            while(rs.next()){
                stlist.add(new Status(
                        String.valueOf(rs.getInt("ID")),
                        rs.getString("name")
                ));
            }
            boolean check = stlist.size() > 0;
            if(check) return stlist;
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
