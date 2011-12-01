/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Michelle
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
        //this.role = role;

    }

    public User() {
    }

    public static User lookup(String email, Connection con) throws SQLException {
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM D_User WHERE Email=?");
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            User user;
            if (!rs.next()) {
                user = null;
            } else {
                user = new User();
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setfName(rs.getString("Fname"));
                user.setlName(rs.getString("Lname"));
                user.setAgency(rs.getString("Agency"));
                user.setRole(rs.getString("User_Role"));
                user.setIsApproved(rs.getString("IsApproved"));
            }

            rs.close();
            pstmt.close();
            return user;

        } catch (Exception e) {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e2) {
                ;
            }
            throw new SQLException(e);
        }
    }

    public int Insert(Connection con) throws SQLException {

        int flag = 0;
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("Insert into D_User(Password,lName,fName,Telephone,Agency,Email) values(?,?,?,?,?,?)");

            statement.setString(1, password);
            statement.setString(2, lName);
            statement.setString(3, fName);
            statement.setString(4, telephone);
            statement.setString(5, agency);
            statement.setString(6, email);

            statement.executeUpdate();
            flag=1;

        } catch (SQLException e) {

            e.printStackTrace();
            flag=-1;

        } finally {
            if (statement != null) {
                statement.close();                
            }
            return flag;
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
