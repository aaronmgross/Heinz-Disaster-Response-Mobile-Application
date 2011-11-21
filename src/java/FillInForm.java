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
//import com.sun.org.apache.xml.internal.serialize.OutputFormat;

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

                //System.out.println("request: "+request.toString());
       // System.out.println("jsonObject:"+request.getParameter("jsonObject"));
        //ObjectMapper mapper = new ObjectMapper();
        //jsonObject object = mapper.readValue(jsonObject, jsonObject.class);

        String ojbec = (String)request.getParameter("electric");

        System.out.println("electric:"+ojbec);

        String houseNum = (String) request.getParameter("tel_house_num");
        //Can not retrieve street name because it is hard coded in html
        String streetName = (String) request.getParameter("txt_street_name");
        String apt = (String) request.getParameter("tel_apt");
        String city = (String) request.getParameter("txt_city");
        String state = (String) request.getParameter("txt_state");
        String zip = (String) request.getParameter("tel_zip");
        String address = houseNum + " " + streetName + "#" + apt + " " + city + " " + state + " " + zip;
        System.out.println(address);

        


        String firstName = (String) request.getParameter("txt_first_name");
        String lastName = (String) request.getParameter("txt_last_name");

        String dwellingType = (String) request.getParameter("dwelling_type");
        String ownerInfo = (String) request.getParameter("owner_information");
        System.out.println(firstName + " " + lastName);
        System.out.println(dwellingType);
        System.out.println(ownerInfo);
        String landlordName=null;
        String landlordPhone=null;
        if (!ownerInfo.equals("OW")) {
            landlordName = (String) request.getParameter("landlord_name");
            landlordPhone = (String) request.getParameter("landlord_tel");
            System.out.println("landlord_name:" + landlordName);
            System.out.println("landlord_tel:" + landlordPhone);
        }
        String insuranceInfo = (String) request.getParameter("insurance_information");
        System.out.println("insurance_information:" + insuranceInfo);


        //General Damage Assess
        String floorNum = (String) request.getParameter("number_floors");
        String waterLiving = (String) request.getParameter("water_in_living_area");

        System.out.println("number_floors:" + floorNum);
        System.out.println("water_in_living_area:" + waterLiving);

        //from Yao
        boolean isBasement =
                ((String) request.getParameter("is_basement")).equals("yes");
        System.out.println("is_basement:" + isBasement);

        //mandatory if isBasement is true
        if(isBasement){
        boolean isBasementOccupied =
                ((String) request.getParameter("is_basement_occupied")).equals("yes");
        System.out.println("is_basement_occupied:" + isBasementOccupied);

        String basementComment =
                (String) request.getParameter("txtarea_basement_comment");
        System.out.println("txtarea_basement_comment:" + basementComment);

        String waterBasement = (String) request.getParameter("water_in_basement");
        System.out.println("water_in_basement:" + waterBasement);
        }
        boolean isElectricOn =
                ((String) request.getParameter("electric")).equals("yes");
        boolean isGasOn =
                ((String) request.getParameter("gas")).equals("yes");
        boolean needElectricity =
                ((String) request.getParameter("d_electrical")).equals("yes");
        boolean needFurnace =
                ((String) request.getParameter("d_furnace")).equals("yes");
        boolean needWater =
                ((String) request.getParameter("d_water")).equals("yes");
        boolean needWasher =
                ((String) request.getParameter("d_washer")).equals("yes");
        boolean needDryer =
                ((String) request.getParameter("d_dryer")).equals("yes");
        boolean needStove =
                ((String) request.getParameter("d_stove")).equals("yes");
        boolean needFridge =
                ((String) request.getParameter("d_fridge")).equals("yes");
        System.out.println("electric:" + isElectricOn);
        System.out.println("gas:" + isGasOn);
        System.out.println("d_electrical:" + needElectricity);
        System.out.println("d_furnace:" + needFurnace);
        System.out.println("d_water:" + needWater);
        System.out.println("d_washer:" + needWasher);
        System.out.println("d_dryer:" + needDryer);
        System.out.println("d_stove:" + needStove);
        System.out.println("d_fridge:" + needFridge);


        //if choose nothing, classification equals null
        String classification =
                (String) request.getParameter("select_classification");
        classification = classification.equals("") ? null : classification;

        String reason = (String) request.getParameter("txtArea_classification_reason");
        String comments = (String) request.getParameter("txtArea_comment");


         System.out.println("select_classification:" + classification);
         System.out.println("txtArea_classification_reason:" + reason);
         System.out.println("txtArea_comment:" + comments);

         try{
         FillInFormMain fim = new FillInFormMain(streetName,apt,city,state,zip,"","",firstName,lastName,"",landlordName,landlordPhone,dwellingType,insuranceInfo,ownerInfo);
        }
         catch(Exception e){
             e.printStackTrace();
         }

    }
}
