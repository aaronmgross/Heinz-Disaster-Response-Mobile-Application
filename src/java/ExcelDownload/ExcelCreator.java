/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcelDownload;

/**
 *
 * @author Soundarya R
 */
import DataDAO.Building;
import DataDAO.Cases;
import DataDAO.DamageAssessment;
import DataDAO.User;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.Timestamp;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import jxl.WorkbookSettings;
import org.apache.poi.ss.usermodel.CreationHelper;

public class ExcelCreator {

     BuildingInformation bInfo;
     ClientInformation clients;
     DamageAssmntInformation dmgAssmnt;
     CaseInformation caseInfo;
     VolunteerInformation vInfo;
     int sheetIndex = 1,rowIndex=1;
Workbook wb = new HSSFWorkbook();
 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
 Sheet sheet;
   String sheetName="Client"+sheetIndex;

      String filename = "Client.xls";
      Timestamp reqdDate;

        FileOutputStream out = new FileOutputStream(filename);
    public ExcelCreator(Timestamp time) throws SQLException, FileNotFoundException, IOException {
        reqdDate=time;
        initializeDocument();
        loadData();

    }


    public void initializeDocument(){
        sheet = wb.createSheet(sheetName);

        Row row = sheet.createRow(0);
         Cell cell = row.createCell(0);
        cell.setCellValue("Disaster Address");
         cell = row.createCell(1);
        cell.setCellValue("Apt/Unit#");
         cell = row.createCell(2);
        cell.setCellValue("City");
         cell = row.createCell(3);
        cell.setCellValue("State");
         cell = row.createCell(4);
        cell.setCellValue("Zip Code");
          cell = row.createCell(5);
        cell.setCellValue("Municipality");
          cell = row.createCell(6);
        cell.setCellValue("County");
         cell = row.createCell(7);
        cell.setCellValue("First Name");
         cell = row.createCell(8);
        cell.setCellValue("Last Name");
         cell = row.createCell(9);
        cell.setCellValue("Dwelling Type");
         cell = row.createCell(10);
        cell.setCellValue("Ownership");
        cell = row.createCell(11);
        cell.setCellValue("Insurance-Flood");
          cell = row.createCell(12);
           cell.setCellValue("Insurance-Structure");
          cell = row.createCell(13);
           cell.setCellValue("Insurance-Contents");
          cell = row.createCell(14);
        cell.setCellValue("Landlord Name");
         cell = row.createCell(15);
        cell.setCellValue("Contact Information");
          cell = row.createCell(16);
        cell.setCellValue("Start Time");
        cell = row.createCell(17);
        cell.setCellValue("End Time");
         cell = row.createCell(18);
        cell.setCellValue("Structural Damage");
         cell = row.createCell(19);
        cell.setCellValue("Debris Amount");
         cell = row.createCell(20);
        cell.setCellValue("Business Inventory Loss");
         cell = row.createCell(21);
        cell.setCellValue("Number of Floors");
          cell = row.createCell(22);
        cell.setCellValue("Is there a basement?");
         cell = row.createCell(23);
          cell.setCellValue("Water Level in Living Area");
         cell = row.createCell(24);
        cell.setCellValue("Water Level in Basement");
         cell = row.createCell(25);
        cell.setCellValue("Is Electricity On?");
        cell = row.createCell(26);
        cell.setCellValue("Is Gas On?");
         cell = row.createCell(27);
        cell.setCellValue("Basement Occupied?");
         cell = row.createCell(28);
        cell.setCellValue("If basement is occupied, describe occupancy use");
         cell = row.createCell(29);
        cell.setCellValue("Reason for Damage Classification");
         cell = row.createCell(30);
        cell.setCellValue("Electrical Service Box");
         cell = row.createCell(31);
        cell.setCellValue("Furnace");

         cell = row.createCell(32);
        cell.setCellValue("Hot Water Heater");
         cell = row.createCell(33);
        cell.setCellValue("Washer");
         cell = row.createCell(34);
        cell.setCellValue("Dryer");
         cell = row.createCell(35);

        cell.setCellValue("Stove");
           cell = row.createCell(36);
         cell.setCellValue("Refrigerator");

           cell = row.createCell(37);
        cell.setCellValue("Comments");
           cell = row.createCell(38);
        cell.setCellValue("Assessor Name");
    }
    public  void createDocument() throws FileNotFoundException, IOException {


GregorianCalendar cal = new GregorianCalendar();



    sdf.setTimeZone(TimeZone.getDefault());
   




        Row row = sheet.createRow(rowIndex);

      Cell cell = row.createCell(0);
        cell.setCellValue(clients.getAddress());
//
//       // row = sheet.createRow(2);
//
        cell = row.createCell(1);
        cell.setCellValue(clients.getAptNo());
//
//        row = sheet.createRow(3);
//
       cell = row.createCell(2);
        cell.setCellValue(clients.getCity());
//
//        row = sheet.createRow(4);
//
        cell = row.createCell(3);
        cell.setCellValue(clients.getState());
//
//        row = sheet.createRow(5);
//
        cell = row.createCell(4);
        cell.setCellValue(clients.getZipCode());
//
//        row = sheet.createRow(6);
//
        cell = row.createCell(5);
        cell.setCellValue(clients.getMunicipality());
//
//        row = sheet.createRow(7);
//
        cell = row.createCell(6);
        cell.setCellValue(clients.getCounty());
//
//        row = sheet.createRow(8);
//
        cell = row.createCell(7);
        cell.setCellValue(clients.getFirstName());
//
//        row = sheet.createRow(9);
//
        cell = row.createCell(8);
        cell.setCellValue(clients.getLastName());

        cell = row.createCell(9);
        cell.setCellValue(bInfo.getDwellingType());

//        row = sheet.createRow(13);

        cell = row.createCell(10);
        cell.setCellValue(bInfo.getownership());

     //   row = sheet.createRow(14);

        cell = row.createCell(11);
        cell.setCellValue(bInfo.getInsuranceFlood());
        
         cell = row.createCell(12);
        cell.setCellValue(bInfo.getInsuranceStructure());

          cell = row.createCell(13);
        cell.setCellValue(bInfo.getInsuranceContents());
       

   //     row = sheet.createRow(15);

        cell = row.createCell(14);
        cell.setCellValue(bInfo.getlandlordName());

//        row = sheet.createRow(16);

        cell = row.createCell(15);
        cell.setCellValue(bInfo.getcontactInfo());


        cell = row.createCell(16);
        CreationHelper createHelper = wb.getCreationHelper();
        CellStyle cellStyle = wb.createCellStyle();
    cellStyle.setDataFormat(
        createHelper.createDataFormat().getFormat("m/d/yy"));
   
    cell.setCellValue(caseInfo.getStartTime());
    cell.setCellStyle(cellStyle);
    //   cell.setCellValue(caseInfo.getStartTime());

 
        cell = row.createCell(17);
         
        cell.setCellValue(caseInfo.getCompletionTime());
cell.setCellStyle(cellStyle);
 
        cell = row.createCell(18);
        cell.setCellValue(dmgAssmnt.getStructuralDamage());

        cell = row.createCell(19);
        cell.setCellValue(dmgAssmnt.getDebrisAmount());

        cell = row.createCell(20);
        cell.setCellValue(dmgAssmnt.getBusinessInventoryLoss());



        cell = row.createCell(21);
        cell.setCellValue(((Integer)dmgAssmnt.getnumberOfFloors()).toString());

 //       row = sheet.createRow(25);

        cell = row.createCell(22);
        cell.setCellValue(dmgAssmnt.getIsThereBasement());

 //       row = sheet.createRow(26);

        cell = row.createCell(23);
        cell.setCellValue(((Integer)dmgAssmnt.getWaterLevelLivingArea()).toString());

   //     row = sheet.createRow(27);

        cell = row.createCell(24);
        cell.setCellValue(((Integer)dmgAssmnt.getWaterLevelBasement()).toString());

 //       row = sheet.createRow(28);

        cell = row.createCell(25);
        cell.setCellValue(dmgAssmnt.getIsElectricityOn());

 //       row = sheet.createRow(29);

        cell = row.createCell(26);
        cell.setCellValue(dmgAssmnt.getIsGasOn());

  //      row = sheet.createRow(30);

        cell = row.createCell(27);
        cell.setCellValue(dmgAssmnt.getBasementOccupied());

  //       row = sheet.createRow(31);

        cell = row.createCell(28);
        cell.setCellValue(dmgAssmnt.getOccupiedDescription());

 //        row = sheet.createRow(32);

        cell = row.createCell(29);
        cell.setCellValue(dmgAssmnt.getClassificationReason());

 //        row = sheet.createRow(33);
       
//         row = sheet.createRow(34);

        cell = row.createCell(30);
        cell.setCellValue(dmgAssmnt.getElectricalServiceBox());

 //        row = sheet.createRow(35);

        cell = row.createCell(31);
        cell.setCellValue(dmgAssmnt.getFurnace());



        cell = row.createCell(32);
        cell.setCellValue(dmgAssmnt.getHotWaterHeater());


        cell = row.createCell(33);
        cell.setCellValue(dmgAssmnt.getWasher());


        cell = row.createCell(34);
        cell.setCellValue(dmgAssmnt.getDryer());

        cell = row.createCell(35);
        cell.setCellValue(dmgAssmnt.getstove());

        cell = row.createCell(36);
        cell.setCellValue(dmgAssmnt.getRefrigerator());

     //    row = sheet.createRow(41);

        cell = row.createCell(37);
        cell.setCellValue(caseInfo.getComment());

       //  row = sheet.createRow(42);

        cell = row.createCell(38);
        cell.setCellValue(vInfo.getFName()+" "+vInfo.getLName());
        rowIndex++;

//        String filename = "Client";
//      //  filename += fileIndex;
//        filename += ".xls";
//
//        FileOutputStream out = new FileOutputStream(filename);
//        wb.write(out);
//        out.close();
//        fileIndex++;

    }

    public void loadData() throws SQLException, FileNotFoundException, IOException {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }
        String connectionStr = "jdbc:mysql://localhost/DisasterAssessment";
        String user = "root";
        String pw = "hello";
        try {
            con = DriverManager.getConnection(connectionStr, user, pw);

            PreparedStatement clnt = con.prepareStatement("Select * from Client");
            PreparedStatement bldg = con.prepareStatement("Select * from Building");
            PreparedStatement dmgAssmt = con.prepareStatement("Select * from Damage_Assessment");
           PreparedStatement clientCase = con.prepareStatement("Select * from Cases");
            PreparedStatement vtr = con.prepareStatement("Select * from D_User ");


            ResultSet rsBldg = bldg.executeQuery();
            ResultSet rsClient = clnt.executeQuery();
            ResultSet rsDamageAsmnt = dmgAssmt.executeQuery();
            ResultSet rsClientCase = clientCase.executeQuery();
            ResultSet rsVtr = vtr.executeQuery();

            ArrayList volunteerList=new ArrayList();
            while(rsVtr.next()){
                volunteerList.add(rsVtr.getInt("User_Id"));
            }


            while (rsBldg.next() && rsClient.next() && rsDamageAsmnt.next() && rsClientCase.next()  ) {
 if(rsClientCase.getTimestamp("Start_Time").equals(reqdDate) ||rsClientCase.getTimestamp("Start_Time").after(reqdDate) ){
                clients = new ClientInformation();
                int clntId = rsClient.getInt("Client_Id");
                DataDAO.Client client = new DataDAO.Client();
                client.getByID(con, clntId);
                clients.setAddress(client.getAddress());
                clients.setAptNo(client.getAptNum());
                clients.setCity(client.getCity());
                clients.setCounty(client.getCounty());
                clients.setFirstName(client.getfName());
                clients.setLastName(client.getlName());
                clients.setMunicipality(client.getMunicipality());
                clients.setState(client.getState());
                clients.setZipCode(client.getZipCode());

                bInfo = new BuildingInformation();
                int bldgId = rsBldg.getInt("Build_Id");
                Building building = new DataDAO.Building();
                building.getById(con, bldgId);
                bInfo.setDwellingType(building.getDwellingType());
                bInfo.setOwnership(building.getOwnership());
                bInfo.setInsuranceContents(building.getInsuranceContents());
                bInfo.setInsuranceFlood(building.getInsuranceFlood());
                bInfo.setInsuranceStructure(building.getInsuranceStructure());
                bInfo.setLandlordName(building.getLandlordName());
                bInfo.setContactInfo(building.getContactInfo());

                dmgAssmnt = new DamageAssmntInformation();
                int dID = rsDamageAsmnt.getInt("Assessment_Id");
                DamageAssessment dmg = new DamageAssessment();
                dmg.getByID(con, dID);
                dmgAssmnt.setBasementOccupied(dmg.getIsBasementOccupied());
                dmgAssmnt.setBusinessInventoryLoss(dmg.getBizInventoryLoss());
                dmgAssmnt.setClassificationReason(dmg.getReason());
               
                dmgAssmnt.setDebrisAmount(dmg.getDebrisAmout());
                dmgAssmnt.setDryer(dmg.getDryer());
                dmgAssmnt.setElectricalServiceBox(dmg.getElectriccalBox());
                dmgAssmnt.setFurnace(dmg.getFurnace());
                dmgAssmnt.setHotWaterHeater(dmg.getHotWaterHeater());
                dmgAssmnt.setIsElectricityOn(dmg.getIsElectricityOn());
                dmgAssmnt.setIsGasOn(dmg.getIsGasOn());
                dmgAssmnt.setIsThereBasement(dmg.getIsBasement());
                dmgAssmnt.setNumberOfFloors(dmg.getNumFloor());
                dmgAssmnt.setOccupiedDescription(dmg.getOccupiedDescription());
                dmgAssmnt.setRefrigerator(dmg.getRefrigerator());
                
                dmgAssmnt.setStove(dmg.getStove());
                dmgAssmnt.setStructuralDamage(dmg.getStructuralDamage());
                dmgAssmnt.setWasher(dmg.getWasher());
                dmgAssmnt.setWaterLevelBasement(dmg.getWaterLevelBasement());
                dmgAssmnt.setWaterLevelLivingArea(dmg.getWaterLevelLivingArea());

              caseInfo=new CaseInformation();
              int cId=rsClientCase.getInt("Case_Id");
              Cases cs=new Cases();
              cs.getById(con, cId);
              caseInfo.setComment(cs.getComment());
              caseInfo.setStartTime(cs.getStartTime());
              caseInfo.setCompletionTime(cs.getEndTime());

              int vId=rsClientCase.getInt("User_Id");
              vInfo=new VolunteerInformation();
             for(int i=0;i<volunteerList.size();i++){
                 if(vId==(Integer)volunteerList.get(i))
                     break;

             }


              User users=new User();
              users.getByID(con, vId);
              vInfo.setFName(users.getfName());
              vInfo.setLName(users.getlName());
               createDocument();
 }

            }
        } catch (Exception e) {
            e.printStackTrace();
       } finally {
       wb.write(out);

        out.close();
            con.close();
        }

    }

    public File downloadHelp(){

        File f=new File (filename);
          return f;
    }
}
