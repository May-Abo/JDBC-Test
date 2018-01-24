package jdbcconnection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestProperties {

    public static Connection openConnectionWithProperties() throws FileNotFoundException, IOException {

        Connection connection = null;

        Properties pr = new Properties();

        FileReader fr = new FileReader("src\\jdbcconnection\\NewProperties.properties");

        pr.load(fr);

        String userName = pr.getProperty("DB_USER_NAME");
        String password = pr.getProperty("DB_PASSWORD");
        String url = pr.getProperty("DB_URL");
        String driver = pr.getProperty("DB_DRIVER");

        Enumeration er = pr.keys();
        
        while (er.hasMoreElements()) {
            System.out.println(er.nextElement());
        }

        try {
            Class.forName(driver);
            System.out.println("The connection with properties is opened");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestConstants.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException ex) {
            Logger.getLogger(TestConstants.class.getName()).log(Level.SEVERE, null, ex);
        }

        return connection;
    }

    public static void closeConnectionWithProperties(Connection connection) {
        try {
            connection.close();
            System.out.println("The connection with properties is closed");
        } catch (SQLException ex) {
            Logger.getLogger(TestConstants.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
