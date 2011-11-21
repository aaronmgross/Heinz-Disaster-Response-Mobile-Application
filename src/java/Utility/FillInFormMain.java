/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;
import DataDAO.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Michelle
 */
public class FillInFormMain {

   private static Connection con;

    public FillInFormMain(String address, String aptNum, String city, String state, String zipCode,
            String municipality, String county, String lName, String fName,String description, String landlordName, String contactInfo, String dwellingType,
            String insurance, String ownership) throws SQLException {
      
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }
        String connectionStr = "jdbc:mysql://localhost/DisasterAssessment";
        //String connectionStr = "jdbc:mysql:///DisasterAssessment";
        String user = "root";
        String pw = "";
        try {
            //database °∞URL°±, For MySQL it would be "jdbc:mysql:///<dbname>",Optionally you can pass in a user id, & password
            con = DriverManager.getConnection(connectionStr,user,pw);
            Client client = new Client(address,aptNum,city,state,zipCode,municipality,county,lName,fName);
            client.Insert(con);           
            
            Building building = new Building(landlordName,contactInfo,dwellingType,insurance,ownership);
            building.Insert(con);

            int buildId = building.getId(con);
            System.out.println(buildId);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //con.close();
        }
        
         
    }
    
//    public void create(Connection con,String address, String aptNum, String city, String state, String zipCode,
//            String municipality, String county, String lName, String fName) throws SQLException{
//
//        Client client = new Client(address,aptNum,city,state,zipCode,municipality,county,lName,fName);
//        client.Insert(con);
//
//    }


}
