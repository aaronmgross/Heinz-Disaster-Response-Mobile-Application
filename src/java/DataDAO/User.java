package DataDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Xue Zhang
 */
public class User {

    private int userId;
    private String password;
    private String lName;
    private String fName;
    private String telephone;
    private String agency;
    private String email;
    private String role;
    private String IsApproved;

    public User(String password, String lName, String fName, String telephone, String agency, String email) {
        this.password = password;
        this.lName = lName;
        this.fName = fName;
        this.telephone = telephone;
        this.agency = agency;
        this.email = email;

    }

    public User() {
    }

    public static User lookup(String email, Connection con) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
             pstmt = con.prepareStatement("SELECT * FROM D_User WHERE Email=?");
            pstmt.setString(1, email);
             rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("User_Id"));
                user.setEmail(rs.getString("Email"));
                user.setTelephone(rs.getString("Telephone"));
                user.setfName(rs.getString("Fname"));
                user.setlName(rs.getString("Lname"));
                user.setAgency(rs.getString("Agency"));
                user.setRole(rs.getString("User_Role"));
                user.setIsApproved(rs.getString("IsApproved"));
            }           
        }finally{
            rs.close();
            pstmt.close();
        }
        return user;
    }

    public static boolean CheckPassword(String email, Connection con,String pw) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
             pstmt = con.prepareStatement("SELECT * FROM D_User WHERE Email=? and Password=PASSWORD(?)");
             pstmt.setString(1, email);
             pstmt.setString(2, pw);
             rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }
            else return false;
        }finally{
            rs.close();
            pstmt.close();
        }
    }



    public static List getUnapproved(Connection con) throws SQLException {
        List<User> l = new ArrayList<User>();
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("SELECT User_Id,Fname,Lname,Telephone,Agency,Email,IsApproved,User_Role FROM D_User WHERE IsApproved = 'N'");

            ResultSet rs = statement.executeQuery();
            User temp;
            while (rs.next()) {
                temp = new User();
                temp.setUserId(rs.getInt("User_Id"));
                temp.setfName(rs.getString("Fname"));
                temp.setlName(rs.getString("Lname"));
                temp.setTelephone(rs.getString("Telephone"));
                temp.setAgency(rs.getString("Agency"));
                temp.setEmail(rs.getString("Email"));
                temp.setIsApproved(rs.getString("IsApproved"));
                temp.setRole(rs.getString("User_Role"));
                l.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return l;
    }

    public static List getApproved(Connection con) throws SQLException {
        List<User> l = new ArrayList<User>();
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("SELECT User_Id,Fname,Lname,Telephone,Agency,Email,IsApproved,User_Role FROM D_User WHERE IsApproved = 'Y' AND User_Role = 'VOLUNTEER'");

            ResultSet rs = statement.executeQuery();
            User temp;
            while (rs.next()) {
                temp = new User();
                temp.setUserId(rs.getInt("User_Id"));
                temp.setfName(rs.getString("Fname"));
                temp.setlName(rs.getString("Lname"));
                temp.setTelephone(rs.getString("Telephone"));
                temp.setAgency(rs.getString("Agency"));
                temp.setEmail(rs.getString("Email"));
                temp.setIsApproved(rs.getString("IsApproved"));
                temp.setRole(rs.getString("User_Role"));
                l.add(temp);
            }
        }finally {           
                statement.close();
        }
        return l;
    }

    public void getByID(Connection con, int id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("SELECT * FROM D_User WHERE User_Id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            User temp;
            while (rs.next()) {
                setUserId(rs.getInt("User_Id"));
                //setPassword(rs.getString("Password"));
                setfName(rs.getString("Fname"));
                setlName(rs.getString("Lname"));
                setTelephone(rs.getString("Telephone"));
                setAgency(rs.getString("Agency"));
                setEmail(rs.getString("Email"));
                setIsApproved(rs.getString("IsApproved"));
                setRole(rs.getString("User_Role"));
            }
        }finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public void update(Connection con, int id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("UPDATE D_User SET Password=PASSWORD(?), Lname=?, Fname=?, Telephone=?, Agency=?, Email=?, User_Role=?, IsApproved=? WHERE User_Id=?");
            statement.setString(1, password);
            statement.setString(2, lName);
            statement.setString(3, fName);
            statement.setString(4, telephone);
            statement.setString(5, agency);
            statement.setString(6, email);
            statement.setString(7, role);
            statement.setString(8, IsApproved);
            statement.setInt(9, userId);
            statement.executeUpdate();
            //con.commit();

        }finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public int Insert(Connection con) throws SQLException {
        int flag = -1;
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("Insert into D_User(Password,lName,fName,Telephone,Agency,Email) values(PASSWORD(?),?,?,?,?,?)");

            statement.setString(1, password);
            statement.setString(2, lName);
            statement.setString(3, fName);
            statement.setString(4, telephone);
            statement.setString(5, agency);
            statement.setString(6, email);
            statement.executeUpdate();
            flag = 1;
        } finally {           
            statement.close();            
        }
        return flag;
    }

        public void delete(Connection con, int id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("DELETE FROM D_User WHERE User_Id=?");
            statement.setInt(1, id);
            statement.executeUpdate();

        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

            public void approve(Connection con, int id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("UPDATE D_User SET IsApproved=? WHERE User_Id=?");
            statement.setString(1, "Y");
            statement.setInt(2, id);
            statement.executeUpdate();
           
        }finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIsApproved() {
        return IsApproved;
    }

    public void setIsApproved(String IsApproved) {
        this.IsApproved = IsApproved;
    }


}
