package jdbcconnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserOperation {

    public Users getUser(String id) {

        Users u = null;
        Connection connection = TestConstants.openConnection();
        PreparedStatement stm = null;
        String sql = "SELECT Id, FirstName, LastName, Email, Address FROM USERS WHERE ID=?";

        try {

            stm = connection.prepareStatement(sql);

            stm.setString(1, id);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {

                String id1 = rs.getString(1);

                String firstname = rs.getString(2);

                String lastname = rs.getString(3);

                String email = rs.getString(4);

                String address = rs.getString(5);

                u = new Users(id1, firstname, lastname, email, address);

            }

        } catch (Exception ex) {

            ex.printStackTrace();
            Logger.getLogger(UserOperation.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            try {

                stm.close();

            } catch (Exception ex) {

                Logger.getLogger(UserOperation.class.getName()).log(Level.SEVERE, null, ex);

            }

            TestConstants.closeConnection(connection);

        }

        return u;
    }

    public int addUser(Users u) {

        Connection connection = TestConstants.openConnection();
        PreparedStatement stm = null;
        int result = 0;
        String sql = "INSERT INTO USERS (FirstName , LastName, Email, Address) VALUES (?,?,?,?)";

        try {

            stm = connection.prepareStatement(sql);
            stm.setString(1, u.getFname());
            stm.setString(2, u.getLname());
            stm.setString(3, u.getEmail());
            stm.setString(4, u.getAddress());

            result = stm.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(UserOperation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
            } catch (Exception ex) {
                Logger.getLogger(UserOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
            TestConstants.closeConnection(connection);
        }

        return result;
    }

    public int updateUser(Users u) {

        Connection connection = TestConstants.openConnection();
        PreparedStatement stm = null;
        int result = 0;
        String sql = "UPDATE USERS SET FirstName = ? , LastName = ?, Email = ?, Address = ? WHERE ID = ? ";

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, u.getFname());
            stm.setString(2, u.getLname());
            stm.setString(3, u.getEmail());
            stm.setString(4, u.getAddress());
            stm.setString(5, u.getId());
            result = stm.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(UserOperation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
            } catch (Exception ex) {
                Logger.getLogger(UserOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
            TestConstants.closeConnection(connection);
        }
        return result;
    }

    public int deleteUser(String id) {
        Connection connection = TestConstants.openConnection();
        int result = 0;
        PreparedStatement stm = null;
        String sql = "UPDATE USERS SET Status = ? WHERE ID = ?";

        try {

            stm = connection.prepareStatement(sql);
            stm.setInt(1, Constants.INACTIVEUSER);
            stm.setString(2, id);

            result = stm.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(UserOperation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
            } catch (Exception ex) {
                Logger.getLogger(UserOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
            TestConstants.closeConnection(connection);
        }
        return result;
    }

    public Vector getAllUsers() {
        Vector v = new Vector();
        Connection connection = TestConstants.openConnection();
        PreparedStatement stm = null;
        Users u = null;
        String sql = "SELECT Id, FirstName, LastName, Email, Address FROM USERS";

        try {
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);

                String firstname = rs.getString(2);

                String lastname = rs.getString(3);

                String email = rs.getString(4);

                String address = rs.getString(5);
                u = new Users(id, firstname, lastname, email, address);
                v.add(u);
            }

        } catch (Exception ex) {
            Logger.getLogger(UserOperation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
            } catch (Exception ex) {
                Logger.getLogger(UserOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
            TestConstants.closeConnection(connection);
        }

        return v;
    }

    public int checkUserEmail(String email) {

        Connection connection = TestConstants.openConnection();
        PreparedStatement stm = null;
        int result = 0;
        String sql = "SELECT 1 FROM USERS WHERE email =?";

        try {

            stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                result = 1;
            }

        } catch (Exception ex) {
            Logger.getLogger(UserOperation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
            } catch (Exception ex) {
                Logger.getLogger(UserOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
            TestConstants.openConnection();
        }

        return result;
    }

    public ArrayList getUsersPR(String type, String id) {
        ArrayList a = new ArrayList();
        Connection connection = TestConstants.openConnection();
        CallableStatement cs = null;
        Users u = null;
        String sql = "CALL getUsersPR (?,?)";

        try {
            cs = connection.prepareCall(sql);
            cs.setInt(1, Integer.parseInt(type));
            cs.setInt(2, Integer.parseInt(id));
            ResultSet re = cs.executeQuery();

            while (re.next()) {

                String id1 = re.getString(1);

                String firstname = re.getString(2);

                String lastname = re.getString(3);

                String email = re.getString(4);

                String address = re.getString(5);

                u = new Users(id1, firstname, lastname, email, address);

                a.add(u);
            }

        } catch (Exception ex) {
            Logger.getLogger(UserOperation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cs.close();
            } catch (Exception ex) {
                Logger.getLogger(UserOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
            TestConstants.openConnection();
        }

        return a;

    }

    public int addDeleteUpdateUserPR(String type, Users u) {
        Connection connection = TestConstants.openConnection();
        CallableStatement cs = null;
        int result = 0;
        String sql = "CALL Add_Delete_UPdate_Procedure (?,?,?,?,?,?,?)";
//CREATE DEFINER=`root`@`localhost` PROCEDURE `Add_Delete_UPdate_Procedure`(in p_Type int, in p_Id int, in p_FirstName varchar(500), in p_LasttName varchar(500), in p_Email varchar(500), in p_Address varchar(500))

        try {
            cs = connection.prepareCall(sql);
            cs.setInt(1, Integer.parseInt(type));
            cs.setInt(2, Integer.parseInt(u.getId()));
            cs.setString(3, u.getFname());
            cs.setString(4, u.getLname());
            cs.setString(5, u.getEmail());
            cs.setString(6, u.getAddress());
            cs.registerOutParameter(7, java.sql.Types.VARCHAR);
            result = cs.executeUpdate();

            System.out.println("   My Operation From SP is    - - - - - " + cs.getString(7));
            System.out.println(" END SP   ");

        } catch (Exception ex) {
            Logger.getLogger(UserOperation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cs.close();
            } catch (Exception ex) {
                Logger.getLogger(UserOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
            TestConstants.openConnection();
        }
        return result;
    }
}
