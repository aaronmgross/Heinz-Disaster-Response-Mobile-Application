/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;
import DataDAO.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;


/**
 *
 * @author Michelle
 */
public class FillInFormMain {

   private static Connection con;

    public FillInFormMain(String address, String aptNum, String city, String state, String zipCode,String municipality, String county, String lName, String fName,
            String landlordName, String contactInfo, String dwellingType,String insurance_f,String insurance_s,String insurance_c, String ownership,
            String Electrical_service_box,String Furnace,String Heat_Water_Heater,String Washer,String Dryer,String Stove,String Regfrigerator,
            String classification,int floorNum,String isBasement,int waterLiving,int waterBasement,String isElectricOn,String isGasOn,String isBasementOccupied,String basementComment,String reason,Date StartTime,Date EndTime,
            String comments) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }
        String connectionStr = "jdbc:mysql://localhost/DisasterAssessment";
        String user = "root";
        String pw = "";
        try {
            //database,For MySQL it would be "jdbc:mysql:///<dbname>",Optionally you can pass in a user id, & password
            con = DriverManager.getConnection(connectionStr,user,pw);
            Client client = new Client(address,aptNum,city,state,zipCode,municipality,county,lName,fName);
            client.Insert(con);
            int clientId = client.getId(con);
            System.out.println("clientId:"+clientId);

            Building building = new Building(landlordName,contactInfo,dwellingType,insurance_f,insurance_s, insurance_c, ownership);
            building.Insert(con);

            int buildId = building.getId(con);
            System.out.println("BuildingId:"+buildId);

            DamageAssessment ds = new DamageAssessment(classification,"","",floorNum,isBasement,waterLiving,waterBasement,isGasOn,isElectricOn,isBasementOccupied,basementComment,reason,
                    Electrical_service_box,Furnace,Heat_Water_Heater,Washer,Dryer,Stove,Regfrigerator,StartTime,EndTime);
            ds.insert(con);
            int damageAssessmentId = ds.getId(con);

            int UserId =-1;

            Cases caseInstance = new Cases(comments,clientId,damageAssessmentId,buildId,UserId);
            caseInstance.Insert(con);
            //int case_Id = caseInstance.getId(con);

            //Need need = new Need();
            //int Electrical_Service_Box_code= need.getId("Electrical Service Box",con);
            //int Furnace_code = need.getId("Furnace", con);
            //int Hot_Water_Heater_code = need.getId("Hot Water Heater", con);
//            int Washer_code = need.getId("Washer", con);
//            int Dryer_code = need.getId("Dryer", con);
//            int Stove_code = need.getId("Stove", con);
//            int Refrigerator_code = need.getId("Refrigerrator", con);
//
//            CaseNeeds caseNeed_e = new CaseNeeds(case_Id,Electrical_Service_Box_code,Electrical_service_box);
//            caseNeed_e.insert(con);
//
//            CaseNeeds caseNeed_f = new CaseNeeds(case_Id,Furnace_code,Furnace);
//            caseNeed_f.insert(con);
//
//            CaseNeeds caseNeed_h = new CaseNeeds(case_Id,Hot_Water_Heater_code,Heat_Water_Heater);
//            caseNeed_h.insert(con);
//
//            CaseNeeds caseNeed_w = new CaseNeeds(case_Id,Washer_code,Washer);
//            caseNeed_w.insert(con);
//
//            CaseNeeds caseNeed_d = new CaseNeeds(case_Id,Dryer_code,Dryer);
//            caseNeed_d.insert(con);
//
//            CaseNeeds caseNeed_s = new CaseNeeds(case_Id,Stove_code,Stove);
//            caseNeed_s.insert(con);
//
//            CaseNeeds caseNeed_r = new CaseNeeds(case_Id,Refrigerator_code,Regfrigerator);
//            caseNeed_r.insert(con);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.close();
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
