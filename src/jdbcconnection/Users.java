
package jdbcconnection;

public class Users {
    
    private String id;
    private String fname;
    private String lname;
    private String email;
    private String address;

    public Users() {
    }

    public Users(String id, String fname, String lname, String email, String address) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", address=" + address + '}';
    }
    
    
    
    
}
