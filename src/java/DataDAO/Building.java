/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

/**
 *
 * @author Michelle
 */
public class Building {

    private int buildId;
    private String landlordName="";
    private String contactInfo="";
    private String dwellingType="";
    private String insurance="";
    private String ownership="";

    public Building(String landlordName, String contactInfo, String dwellingType,
            String insurance, String ownership){ 
        this.landlordName = landlordName==null?"":landlordName;
        this.contactInfo = contactInfo==null?"":contactInfo;
        this.dwellingType = dwellingType==null?"":dwellingType;
        this.insurance = insurance==null?"":insurance;
        this.ownership = ownership==null?"":ownership;
    }

    public Building(){}

    public void Insert(Connection con) throws SQLException {


        PreparedStatement statement = null;
        try {
            
            statement = con.prepareStatement("Insert into Building(Landlord_Name,Contact_information,Dwelling_Type,Insurance,Ownership) values(?,?,?,?,?)");
            statement.setString(1, landlordName);           
            statement.setString(2, contactInfo);            
            statement.setString(3, dwellingType);
            statement.setString(4, insurance);
            statement.setString(5, ownership);
            statement.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();           

        } finally {
            if (statement != null) {
                statement.close();
            }
        }

    }

    public int getId(Connection con) throws SQLException{

        PreparedStatement statement = null;
         try {
             String sql ="select Build_Id from Building where Landlord_Name =? and Contact_information=? and Dwelling_Type =? and Insurance=? and Ownership=?";
            statement = con.prepareStatement(sql);            
            statement.setString(1, landlordName);        
            statement.setString(2, contactInfo);            
            statement.setString(3, dwellingType);
            statement.setString(4, insurance);
            statement.setString(5, ownership);
            
            ResultSet rs = statement.executeQuery();
            //buildId=100;
            while (rs.next()) {
            	buildId = rs.getInt("Build_Id");

            }
            System.out.println("Successfully selected from Building");
            return buildId;
         }catch (SQLException e) {

            e.printStackTrace();
            return -1;

        } finally {
            if (statement != null) {
                statement.close();
            }
        }

    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getDwellingType() {
        return dwellingType;
    }

    public void setDwellingType(String dwellingType) {
        this.dwellingType = dwellingType;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getLandlordName() {
        return landlordName;
    }

    public void setLandlordName(String landlordName) {
        this.landlordName = landlordName;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    




}
