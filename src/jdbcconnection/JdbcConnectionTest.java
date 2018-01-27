package jdbcconnection;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

public class JdbcConnectionTest {

    public static void main(String[] args) throws IOException {

// TESTING CONSTANTS CLASS 
        Connection connection = TestConstants.openConnection();
        TestConstants.closeConnection(connection);
        
// TESTING PROPERTIES CLASS 
        Connection connection2 = TestProperties.openConnectionWithProperties();
        TestProperties.closeConnectionWithProperties(connection2);
        UserOperation up = new UserOperation();

// GET USER METHOD 
        Users u = up.getUser("1");
        if (u == null) {
            JOptionPane.showMessageDialog(null, "User not found ");
        } else {

            JOptionPane.showMessageDialog(null, "User fname " + u.getFname());
        }
        
// ADD USER METHOD 
        Users u1 = new Users("", "bbb", "bbb", "bbb@gmail", "bbb");
        int result = up.addUser(u1);       
        if(result>0){
            System.out.println("User added");
        }
        else{
           System.out.println("User not added"); 
        }
        
// UODATE INFO METHOD
        Users u2 = new Users("1", "ddd", "ddd", "ddd@gmail", "ddd");
        int result2 = up.updateUser(u2);
        if (result2 > 0) {
            System.out.println("User updated");
        } else {
            System.out.println("User not updated");
        }
        
// DELETE USER METHOD
        int result3 = up.deleteUser("1");

        if (result3 > 0) {
            System.out.println("User deleted");
        } else {
            System.out.println("User not deleted");
        }
        
// GET ALL USERS METHOD
            Vector v = up.getAllUsers();
            for(int i=0; i<v.size(); i++){
                
                Users u4 = (Users) v.get(i);
                System.out.println(u4.getFname()); 
                System.out.println(v.get(i));      
                               
            }
            
// CHECK USER EMAIL METHOD
            System.out.println(up.checkUserEmail("sam@gmail"));
            
// GET USERS BY PROCEDURE METHOD
        ArrayList a = up.getUsersPR("0", "0");
        System.out.println(a.size());
        for(int i =0; i<a.size(); i++){
            System.out.println(a.get(i));
        }
        
// ADD/DEELETE/UPDATE USERS BY PROCEDURE METHOD
//        int result= 0 ;
//      // insert //
//      Users u1 = new Users("0", "May", "Ali", "May@hotmail.com", "Amman");      
//      result = up.addDeleteUpdateUserPR("1", u1);
//
//      // Update //        
//      Users u2 = new Users("11", "TareqTest", "Ali", "Tareq@hotma.com", "Amman");
//      result = up.addDeleteUpdateUserPR("2", u2);
//
//      // Delete  // 
//       Users u3 = new Users("11", "TareqTest", "Ali", "Tareq@hotma.com", "Amman");
//       result = up.addDeleteUpdateUserPR("3", u3);
//   
//        if (result > 0) 
//        {
//            System.out.println("PROCEDURE done");
//        } else {
//            System.out.println("PROCEDURE not done");
//        }

    }

}
