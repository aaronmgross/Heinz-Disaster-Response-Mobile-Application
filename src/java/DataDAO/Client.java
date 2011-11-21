/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author Michelle
 */
public class Client {

    private int clientId;
    private String address;
    private String aptNum;
    private String city;
    private String state;
    private String zipCode;
    private String municipality;
    private String county;
    private String lName;
    private String fName;


    public Client(String address, String aptNum, String city, String state, String zipCode,
            String municipality, String county, String lName, String fName){
        this.address = address;
        this.aptNum = aptNum;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.municipality = municipality;
        this.county = county;
        this.lName = lName;
        this.fName = fName;

    }

    public Client(){}

    public void Insert(Connection con) throws SQLException {


        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("Insert into Client(Address,Apt_No,City,State,Zip_Code,Municipality,County,Last_Name,First_Name) values(?,?,?,?,?,?,?,?,?)");

            statement.setString(1, address);
            statement.setString(2, aptNum);
            statement.setString(3, city);
            statement.setString(4, state);
            statement.setString(5, zipCode);
            statement.setString(6, municipality);
            statement.setString(7, county);
            statement.setString(8, lName);
            statement.setString(9, fName);

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {
            if (statement != null) {
                statement.close();
            }
        }

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAptNum() {
        return aptNum;
    }

    public void setAptNum(String aptNum) {
        this.aptNum = aptNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
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

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}
