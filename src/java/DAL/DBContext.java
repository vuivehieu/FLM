
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBContext {
    protected Connection connection;
    
    public DBContext() {
        try {
             String url = "jdbc:mysql://localhost:3306/";
            String dbName = "SWP391_SE1632_G2";
            String driver = "com.mysql.cj.jdbc.Driver";
            String userName = "root";
            String password = "123456";
            Class.forName(driver);
            connection = DriverManager.getConnection(url + dbName, userName, password);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (Exception es) {
            System.out.println(es);
        } 
        
    }
    
}
