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
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
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

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        int id = -1;
        String volunteerId = null;
        String volunteerName = null;
        //Date startTime;
        //Date endTime;
        String streetName = null;
        String houseNum = null;
        String apt = null;
        String city = null;
        String state = null;
        String zip = null;
        String address = null;
        String firstName = null;
        String lastName = null;
        String dwellingType = null;
        String ownerInfo = null;
        String landlordName = null;
        String landlordPhone = null;
        String insuranceInfo = null;
        String floorNum = null;
        String isBasement = null;
        String isBasementOccupied = null;
        String basementComment = null;
        String waterLiving = null;
        String waterBasement = null;
        String isElectricOn = null;
        String isGasOn = null;
        String Electrical_service_box = null;
        String Furnace = null;
        String Heat_Water_Heater = null;
        String Washer = null;
        String Dryer = null;
        String Stove = null;
        String Regfrigerator = null;
        String classification = null;
        String reason = null;
        String comments = null;
        int num_of_floor = -1;
        int waterLivingInt = -1;
        int waterBasementInt = -1;

        String jsonArray = (String) request.getParameter("recordJSON");

        try {
            JSONArray x = new JSONArray(jsonArray);


            for (int i = 0; i < x.length(); i++) {
                JSONObject j = x.getJSONObject(0);

                //JSONObject j = new JSONObject(json);
                id = (Integer) j.get("id");
                System.out.println("id = " + id);
                volunteerId = (String) j.get("volunteer_id");
                volunteerName = (String) j.get("volunteer_name");
                //Date startTime= (Date)j.get("start_time");
                //Date endTime= (Date)j.get("end_time");
                streetName = (String) j.get("street_name");
                houseNum = (String) j.get("tel_house_num");
                apt = (String) j.get("tel_apt");
                city = (String) j.get("txt_city");
                state = (String) j.get("txt_state");
                zip = (String) j.get("tel_zip");
                firstName = (String) j.get("txt_first_name");
                lastName = (String) j.get("txt_last_name");
                landlordName = (String) j.get("landlord_name");
                landlordPhone = (String) j.get("landlord_tel");
                dwellingType = (String) j.get("dwelling_type");
                insuranceInfo = (String) j.get("insurance_information");
                ownerInfo = (String) j.get("owner_information");
                floorNum = (String) j.get("number_floors");
                if (floorNum != null && !floorNum.equals("")) {
                    num_of_floor = Integer.parseInt(floorNum);
                }
                isBasement = (String) j.get("is_basement");
                isBasementOccupied = (String) j.get("is_basement_occupied");
                basementComment = (String) j.get("txtarea_basement_comment");
                waterLiving = (String) j.get("water_in_living_area");
                if (waterLiving != null && !waterLiving.equals("")) {
                    waterLivingInt = Integer.parseInt(waterLiving);
                }
                if (waterBasement != null && !waterBasement.equals("")) {
                    waterBasementInt = Integer.parseInt(waterBasement);
                }
                waterBasement = (String) j.get("water_in_basement");
                isElectricOn = (String) j.get("electric");
                isGasOn = (String) j.get("gas");
                Electrical_service_box = (String) j.get("d_electrical");
                Furnace = (String) j.get("d_furnace");
                Heat_Water_Heater = (String) j.get("d_water");
                Washer = (String) j.get("d_washer");
                Dryer = (String) j.get("d_dryer");
                Stove = (String) j.get("d_stove");
                Regfrigerator = (String) j.get("d_fridge");
                classification = (String) j.get("select_classification");
                reason = (String) j.get("txtArea_classification_reason");
                comments = (String) j.get("txtArea_comment");

                FillInFormMain fim = new FillInFormMain(streetName, apt, city, state, zip, "", "", lastName, firstName,
                        landlordName, landlordPhone, dwellingType, insuranceInfo, ownerInfo,
                        Electrical_service_box, Furnace, Heat_Water_Heater, Washer, Dryer, Stove, Regfrigerator,
                        classification, num_of_floor, isBasement, waterLivingInt, waterBasementInt, isElectricOn, isGasOn, isBasementOccupied, basementComment, reason, sqlDate, sqlDate,
                        comments);
            }

            String destination = "/welcome.jsp";
            request.setAttribute("FormSubmitMessage", "Your Assessment Form has been submitted successfully!");
            RequestDispatcher red = getServletContext().getRequestDispatcher(destination);
            red.forward(request, response);
            
        } catch (JSONException e) {
            System.out.println(e);
        } catch (SQLException ex) {
            System.out.println(ex);
        }


    }
}
