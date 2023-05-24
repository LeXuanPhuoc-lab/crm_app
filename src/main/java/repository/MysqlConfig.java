package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConfig {
    private static final String url = "jdbc:mysql://localhost:3308/crm_app";
    private static final String user = "root";
    private static final  String pass = "100203";

    //protected static final Connection conn = getConnection();
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url,user,pass);
        }catch(ClassNotFoundException e){
                System.out.println(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
