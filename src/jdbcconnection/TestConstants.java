
package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestConstants {
    
    
    public static Connection openConnection(){
        Connection connection = null;
        
        try {
            Class.forName(Constants.DRIVER);
            System.out.println("The connection with constants is opened");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestConstants.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            connection = DriverManager.getConnection(Constants.URL, Constants.USER_NAME, Constants.PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(TestConstants.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        return connection;
    }
    
    
    public static void closeConnection(Connection connection){
        try {
            connection.close();
            System.out.println("The connection with constants  is closed");
        } catch (SQLException ex) {
            Logger.getLogger(TestConstants.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
