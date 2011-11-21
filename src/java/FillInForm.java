/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Utility.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.sql.Date;
//import java.sql.Timestamp;

/**
 *
 * @author Michelle
 */
public class FillInForm extends HttpServlet {

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


       DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
       Date date = new Date();
       //System.out.println(dateFormat.format(date));
        
        //Missing startTime and endTime
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        String tryone = request.getParameter("jsonStringName");
        System.out.println("jsonStringName"+tryone);

       /*Client Table(missing Munipality and County)*/
        String houseNum = (String) request.getParameter("tel_house_num");
        //Can not retrieve street name because it is hard coded in html
        String streetName = (String) request.getParameter("txt_street_name");
        String apt = (String) request.getParameter("tel_apt");
        String city = (String) request.getParameter("txt_city");
        String state = (String) request.getParameter("txt_state");
        String zip = (String) request.getParameter("tel_zip");
        //String address = houseNum + " " + streetName + "#" + apt + " " + city + " " + state + " " + zip;
       //System.out.println(address);
        String firstName = (String) request.getParameter("txt_first_name");
        String lastName = (String) request.getParameter("txt_last_name");

        //Building Table Info
        String dwellingType = (String) request.getParameter("dwelling_type");
        String ownerInfo = (String) request.getParameter("owner_information");
        System.out.println("Client Name:"+firstName + " " + lastName);
        System.out.println("dwellingType:"+dwellingType);
        System.out.println("ownerInfo:"+ownerInfo);
        
        //if (!ownerInfo.equals("OW")) {
        String landlordName = (String) request.getParameter("landlord_name");
        String landlordPhone = (String) request.getParameter("landlord_tel");
            //System.out.println("landlord_name:" + landlordName);
            //System.out.println("landlord_tel:" + landlordPhone);
        //}
        String insuranceInfo = (String) request.getParameter("insurance_information");
        //System.out.println("insurance_information:" + insuranceInfo);


        //Damage_Assessment Table
        int num_of_floor =-1;
        String floorNum = (String) request.getParameter("number_floors");
        if(!floorNum.equals("")||floorNum!=null)
            num_of_floor = Integer.parseInt(floorNum);
        String waterLiving = (String) request.getParameter("water_in_living_area");
        int waterLivingInt=-1;
        if(!waterLiving.equals(""))
        waterLivingInt = Integer.parseInt(waterLiving);
        String isBasement = (String) request.getParameter("is_basement");
        //mandatory if isBasement is true
        //if(isBasement.equals("yes")){
        String isBasementOccupied = (String) request.getParameter("is_basement_occupied");
        System.out.println("is_basement_occupied:" + isBasementOccupied);
        String basementComment = (String) request.getParameter("txtarea_basement_comment");
        System.out.println("txtarea_basement_comment:" + basementComment);
        String waterBasement = (String) request.getParameter("water_in_basement");
        int waterBasementInt =-1;
        if(!waterBasement.equals(""))
            waterBasementInt = Integer.parseInt(waterBasement);
        System.out.println("water_in_basement:" + waterBasement);
        //}
        String isElectricOn =(String) request.getParameter("electric");
        String isGasOn = (String) request.getParameter("gas");
         //if choose nothing, classification equals null
        String classification = (String) request.getParameter("select_classification");
        classification = classification.equals("") ? null : classification;
        String reason = (String) request.getParameter("txtArea_classification_reason");

        //Case_Needs Table
        String Electrical_service_box = (String) request.getParameter("d_electrical");
        String Furnace = (String) request.getParameter("d_furnace");
        String Heat_Water_Heater = (String) request.getParameter("d_water");
        String Washer =  (String) request.getParameter("d_washer");
        String Dryer = (String) request.getParameter("d_dryer");
        String Stove = (String) request.getParameter("d_dryer");
        String Regfrigerator = (String) request.getParameter("d_fridge");
        
        System.out.println("electric:" + isElectricOn);
        System.out.println("gas:" + isGasOn);
        System.out.println("d_electrical:" + Electrical_service_box);
        System.out.println("d_furnace:" + Furnace);
        System.out.println("d_water:" + Heat_Water_Heater);
        System.out.println("d_washer:" + Washer);
        System.out.println("d_dryer:" + Dryer);
        System.out.println("d_stove:" + Stove);
        System.out.println("d_fridge:" + Regfrigerator);


        /*Case Table*/
        String comments = (String) request.getParameter("txtArea_comment");


         System.out.println("select_classification:" + classification);
         System.out.println("txtArea_classification_reason:" + reason);
         System.out.println("txtArea_comment:" + comments);

         try{
         FillInFormMain fim = new FillInFormMain(streetName,apt,city,state,zip,"","",lastName,firstName,
                 landlordName,landlordPhone,dwellingType,insuranceInfo,ownerInfo,
                 Electrical_service_box,Furnace,Heat_Water_Heater,Washer,Dryer,Stove,Regfrigerator,
                 classification,num_of_floor,isBasement,waterLivingInt,waterBasementInt,isElectricOn,isGasOn,isBasementOccupied,basementComment,reason,sqlDate,sqlDate,
                 comments);
         response.sendRedirect("final_steps.html");
        }
         catch(Exception e){
             e.printStackTrace();
         }

    }
}
