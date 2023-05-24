package repository;

import model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CountryRepository {
    public ArrayList<Country> getAllCountry(){
        Connection conn = MysqlConfig.getConnection();
        try {
            String sql = "SELECT * FROM Country";
            PreparedStatement ps = conn.prepareStatement(sql);
            ArrayList<Country> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Country c = new Country();
                c.setID(rs.getInt("ID"));
                c.setName(rs.getString("name"));
                list.add(c);
            }
            boolean check = (list.size()>0) ? true : false;
            if(check) return list;
            else throw new RuntimeException();
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
}
