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

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import jxl.WorkbookSettings;

public class ExcelCreator {

     BuildingInformation bInfo;
     ClientInformation clients;
     DamageAssmntInformation dmgAssmnt;
     CaseInformation caseInfo;
     VolunteerInformation vInfo;
     int sheetIndex = 1;
Workbook wb = new HSSFWorkbook();
 Sheet sheet;
      String filename = "Client.xls";
      //  filename += fileIndex;
      
        FileOutputStream out = new FileOutputStream(filename);
    public ExcelCreator() throws SQLException, FileNotFoundException, IOException {
        loadData();

    }

//    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
//        loadData();
//        // createDocument();
//    }

    public  void createDocument() throws FileNotFoundException, IOException {
       

        String sheetName="Client"+sheetIndex;
        sheet = wb.createSheet(sheetName);
       

        sheetIndex++;


        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);
        cell.setCellStyle(style);
        cell.setCellValue("Client Information");

        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("Disaster Address");
        cell = row.createCell(1);
        cell.setCellValue(clients.getAddress());

        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("Apt/Unit#");
        cell = row.createCell(1);
        cell.setCellValue(clients.getAptNo());

        row = sheet.createRow(3);
        cell = row.createCell(0);
        cell.setCellValue("City");
        cell = row.createCell(1);
        cell.setCellValue(clients.getCity());

        row = sheet.createRow(4);
        cell = row.createCell(0);
        cell.setCellValue("State");
        cell = row.createCell(1);
        cell.setCellValue(clients.getState());

        row = sheet.createRow(5);
        cell = row.createCell(0);
        cell.setCellValue("Zip Code");
        cell = row.createCell(1);
        cell.setCellValue(clients.getZipCode());

        row = sheet.createRow(6);
        cell = row.createCell(0);
        cell.setCellValue("Municipality");
        cell = row.createCell(1);
        cell.setCellValue(clients.getMunicipality());

        row = sheet.createRow(7);
        cell = row.createCell(0);
        cell.setCellValue("County");
        cell = row.createCell(1);
        cell.setCellValue(clients.getCounty());

        row = sheet.createRow(8);
        cell = row.createCell(0);
        cell.setCellValue("First Name");
        cell = row.createCell(1);
        cell.setCellValue(clients.getFirstName());

        row = sheet.createRow(9);
        cell = row.createCell(0);
        cell.setCellValue("Last Name");
        cell = row.createCell(1);
        cell.setCellValue(clients.getLastName());

        row = sheet.createRow(11);
        cell = row.createCell(0);
        style = wb.createCellStyle();
        font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);
        cell.setCellStyle(style);
        cell.setCellValue("Building Information");

        row = sheet.createRow(12);
        cell = row.createCell(0);
        cell.setCellValue("Dwelling Type");
        cell = row.createCell(1);
        cell.setCellValue(bInfo.getDwellingType());

        row = sheet.createRow(13);
        cell = row.createCell(0);
        cell.setCellValue("Ownership");
        cell = row.createCell(1);
        cell.setCellValue(bInfo.getownership());

        row = sheet.createRow(14);
        cell = row.createCell(0);
        cell.setCellValue("Insurance");
        cell = row.createCell(1);
        cell.setCellValue(bInfo.getinsurance());


        row = sheet.createRow(15);
        cell = row.createCell(0);
        cell.setCellValue("Landlord Name");
        cell = row.createCell(1);
        cell.setCellValue(bInfo.getlandlordName());

        row = sheet.createRow(16);
        cell = row.createCell(0);
        cell.setCellValue("Contact Information");
        cell = row.createCell(1);
        cell.setCellValue(bInfo.getcontactInfo());

        row = sheet.createRow(18);
        cell = row.createCell(0);
        style = wb.createCellStyle();
        font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);
        cell.setCellStyle(style);
        cell.setCellValue("Damage Assessment");

        row = sheet.createRow(19);
        cell = row.createCell(0);
        cell.setCellValue("Start Time");
        cell = row.createCell(1);
      
       cell.setCellValue(dmgAssmnt.getStartTime().toString());

        row = sheet.createRow(20);
        cell = row.createCell(0);
        cell.setCellValue("End Time");
        cell = row.createCell(1);
        cell.setCellValue(dmgAssmnt.getCompletionTime().toString());

        row = sheet.createRow(21);
        cell = row.createCell(0);
        cell.setCellValue("Structural Damage");
        cell = row.createCell(1);
        cell.setCellValue(dmgAssmnt.getStructuralDamage());

        row = sheet.createRow(22);
        cell = row.createCell(0);
        cell.setCellValue("Debris Amount");
        cell = row.createCell(1);
        cell.setCellValue(dmgAssmnt.getDebrisAmount());

        row = sheet.createRow(23);
        cell = row.createCell(0);
        cell.setCellValue("Business Inventory Loss");
        cell = row.createCell(1);
        cell.setCellValue(dmgAssmnt.getBusinessInventoryLoss());

        row = sheet.createRow(24);
        cell = row.createCell(0);
        cell.setCellValue("Number of Floors");
        cell = row.createCell(1);
        cell.setCellValue(((Integer)dmgAssmnt.getnumberOfFloors()).toString());

        row = sheet.createRow(25);
        cell = row.createCell(0);
        cell.setCellValue("Is there a basement?");
        cell = row.createCell(1);
        cell.setCellValue(dmgAssmnt.getIsThereBasement());

        row = sheet.createRow(26);
        cell = row.createCell(0);
        cell.setCellValue("Water Level in Living Area");
        cell = row.createCell(1);
        cell.setCellValue(((Integer)dmgAssmnt.getWaterLevelLivingArea()).toString());

        row = sheet.createRow(27);
        cell = row.createCell(0);
        cell.setCellValue("Water Level in Basement");
        cell = row.createCell(1);
        cell.setCellValue(((Integer)dmgAssmnt.getWaterLevelBasement()).toString());

        row = sheet.createRow(28);
        cell = row.createCell(0);
        cell.setCellValue("Is Electricity On?");
        cell = row.createCell(1);
        cell.setCellValue(dmgAssmnt.getIsElectricityOn());

        row = sheet.createRow(29);
        cell = row.createCell(0);
        cell.setCellValue("Is Gas On?");
        cell = row.createCell(1);
        cell.setCellValue(dmgAssmnt.getIsGasOn());

        row = sheet.createRow(30);
        cell = row.createCell(0);
        cell.setCellValue("Basement Occupied?");
        cell = row.createCell(1);
        cell.setCellValue(dmgAssmnt.getBasementOccupied());

         row = sheet.createRow(31);
        cell = row.createCell(0);
        cell.setCellValue("If basement is occupied, describe occupancy use");
        cell = row.createCell(1);
        cell.setCellValue(dmgAssmnt.getOccupiedDescription());

         row = sheet.createRow(32);
        cell = row.createCell(0);
        cell.setCellValue("Reason for Damage Classification");
        cell = row.createCell(1);
        cell.setCellValue(dmgAssmnt.getClassificationReason());

         row = sheet.createRow(33);
        cell = row.createCell(0);
        cell.setCellValue("Are the following appliances damaged?");

         row = sheet.createRow(34);
        cell = row.createCell(1);
        cell.setCellValue("Electrical Service Box");
        cell = row.createCell(2);
        cell.setCellValue(dmgAssmnt.getElectricalServiceBox());

         row = sheet.createRow(35);
        cell = row.createCell(1);
        cell.setCellValue("Furnace");
        cell = row.createCell(2);
        cell.setCellValue(dmgAssmnt.getFurnace());

         row = sheet.createRow(36);
        cell = row.createCell(1);
        cell.setCellValue("Hot Water Heater");
        cell = row.createCell(2);
        cell.setCellValue(dmgAssmnt.getHotWaterHeater());

         row = sheet.createRow(37);
        cell = row.createCell(1);
        cell.setCellValue("Washer");
        cell = row.createCell(2);
        cell.setCellValue(dmgAssmnt.getWasher());

         row = sheet.createRow(38);
        cell = row.createCell(1);
        cell.setCellValue("Dryer");
        cell = row.createCell(2);
        cell.setCellValue(dmgAssmnt.getDryer());

         row = sheet.createRow(39);
        cell = row.createCell(1);
        cell.setCellValue("Stove");
        cell = row.createCell(2);
        cell.setCellValue(dmgAssmnt.getstove());

         row = sheet.createRow(40);
        cell = row.createCell(1);
        cell.setCellValue("Refrigerator");
        cell = row.createCell(1);
        cell.setCellValue(dmgAssmnt.getRefrigerator());

         row = sheet.createRow(41);
        cell = row.createCell(0);
        cell.setCellValue("Comments");
        cell = row.createCell(1);
        cell.setCellValue(caseInfo.getComment());

         row = sheet.createRow(42);
        cell = row.createCell(0);
        cell.setCellValue("Assessor Name");
        cell = row.createCell(1);
        cell.setCellValue(vInfo.getFName()+" "+vInfo.getLName());
       
//        String filename = "Client";
//      //  filename += fileIndex;
//        filename += ".xls";
//
//        FileOutputStream out = new FileOutputStream(filename);
//        wb.write(out);
//        out.close();
    //    fileIndex++;

    }

    public void loadData() throws SQLException, FileNotFoundException, IOException {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }
        String connectionStr = "jdbc:mysql://localhost/DisasterApp";
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
                bInfo.setInsurance(building.getInsurance());
                bInfo.setLandlordName(building.getLandlordName());
                bInfo.setContactInfo(building.getContactInfo());

                dmgAssmnt = new DamageAssmntInformation();
                int dID = rsDamageAsmnt.getInt("Assessment_Id");
                DamageAssessment dmg = new DamageAssessment();
                dmg.getByID(con, dID);
                dmgAssmnt.setBasementOccupied(dmg.getIsBasementOccupied());
                dmgAssmnt.setBusinessInventoryLoss(dmg.getBizInventoryLoss());
                dmgAssmnt.setClassificationReason(dmg.getReason());
                dmgAssmnt.setCompletionTime(dmg.getStartTime());
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
                dmgAssmnt.setStartTime(dmg.getStartTime());
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
        } catch (Exception e) {
            e.printStackTrace();
       } finally {
       wb.write(out);
        
        out.close();
            con.close();
        }

    }

    public File downloadHelp(){
        File f=new File("ClientInformation.csv");
          try{


      OutputStream os = (OutputStream)new FileOutputStream(f);
      String encoding = "UTF8";
      OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
      BufferedWriter bw = new BufferedWriter(osw);

      //Excel document to be imported

      WorkbookSettings ws = new WorkbookSettings();
      ws.setLocale(new Locale("en", "EN"));
      File file = new File(filename);
      jxl.Workbook w = jxl.Workbook.getWorkbook(file,ws);

      // Gets the sheets from workbook
      for (int sh = 0; sh < w.getNumberOfSheets(); sh++)
      {
        jxl.Sheet s = w.getSheet(sh);

        bw.write(s.getName());
        bw.newLine();

        jxl.Cell[] row = null;

        // Gets the cells from sheet
        for (int i = 0 ; i < s.getRows() ; i++)
        {
          row = s.getRow(i);

          if (row.length > 0)
          {
            bw.write(row[0].getContents());
            for (int j = 1; j < row.length; j++)
            {
              bw.write(',');
              bw.write(row[j].getContents().toString());
            }
          }
          bw.newLine();
        }
      }
      bw.flush();
      bw.close();
    }
    catch (UnsupportedEncodingException e)
    {
      System.err.println(e.toString());
    }
    catch (IOException ex)
    {
      System.err.println(ex.toString());
    }
    catch (Exception e)
    {
      System.err.println(e.toString());
    }
          return f;
    }
}
