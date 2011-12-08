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
    private String landlordName = "";
    private String contactInfo = "";
    private String dwellingType = "";
    private String insuranceFlood = "";
    private String insuranceStructure = "";
    private String insuranceContents = "";
    private String ownership = "";

    public Building(String landlordName, String contactInfo, String dwellingType,
            String insurance_f, String insurance_s, String insurance_c, String ownership) {
        this.landlordName = landlordName == null ? "" : landlordName;
        this.contactInfo = contactInfo == null ? "" : contactInfo;
        this.dwellingType = dwellingType == null ? "" : dwellingType;
        this.insuranceFlood = insurance_f == null ? "" : insurance_f;
        this.insuranceStructure = insurance_s == null ? "" : insurance_s;
        this.insuranceContents = insurance_c == null ? "" : insurance_c;
        this.ownership = ownership == null ? "" : ownership;
    }

    public Building() {
    }

    public void Insert(Connection con) throws SQLException {


        PreparedStatement statement = null;
        try {

            statement = con.prepareStatement("Insert into Building(Landlord_Name,Contact_information,Dwelling_Type,Insurance_Flood,Insurance_Structure,Insurance_Contents,Ownership) values(?,?,?,?,?,?,?)");
            statement.setString(1, landlordName);
            statement.setString(2, contactInfo);
            statement.setString(3, dwellingType);
            statement.setString(4, insuranceFlood);
            statement.setString(5, insuranceStructure);
            statement.setString(6, insuranceContents);
            statement.setString(7, ownership);
            statement.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();

        } finally {
            if (statement != null) {
                statement.close();
            }
        }

    }

        public void getById(Connection con,int bldgID) throws SQLException{
          PreparedStatement stat = con.prepareStatement("SELECT * FROM Building WHERE Build_Id = ? ");
        stat.setInt(1, bldgID);
        ResultSet rs = stat.executeQuery();

        if (rs.next()) {
           this.buildId = bldgID;
            this.landlordName = rs.getString("Landlord_Name");
            this.contactInfo = rs.getString("Contact_information");
            this.dwellingType = rs.getString("Dwelling_Type");
            this.insuranceContents = rs.getString("Insurance_Contents");
            this.insuranceFlood = rs.getString("Insurance_Flood");
            this.insuranceStructure=rs.getString("Insurance_Structure");
            this.ownership = rs.getString("Ownership");

        }

    }

    public int getId(Connection con) throws SQLException {

        PreparedStatement statement = null;
        try {
            String sql = "select Build_Id from Building where Landlord_Name =? and Contact_information=? and Dwelling_Type =? and Insurance_Flood=? and Insurance_Structure=?and Insurance_Contents=? and Ownership=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, landlordName);
            statement.setString(2, contactInfo);
            statement.setString(3, dwellingType);
            statement.setString(4, insuranceFlood);
            statement.setString(5, insuranceStructure);
            statement.setString(6, insuranceContents);
            statement.setString(7, ownership);

            ResultSet rs = statement.executeQuery();
            //buildId=100;
            while (rs.next()) {
                buildId = rs.getInt("Build_Id");

            }
            System.out.println("Successfully selected from Building");
            return buildId;
        } catch (SQLException e) {

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

    public String getInsuranceContents() {
        return insuranceContents;
    }

    public void setInsuranceContents(String insuranceContents) {
        this.insuranceContents = insuranceContents;
    }

    public String getInsuranceFlood() {
        return insuranceFlood;
    }

    public void setInsuranceFlood(String insuranceFlood) {
        this.insuranceFlood = insuranceFlood;
    }

    public String getInsuranceStructure() {
        return insuranceStructure;
    }

    public void setInsuranceStructure(String insuranceStructure) {
        this.insuranceStructure = insuranceStructure;
    }
}
