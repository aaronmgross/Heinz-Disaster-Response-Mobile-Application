
import DataDAO.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Michelle
 */
public class FillInForm extends HttpServlet {

    private Connection con;
    private String dbuser = null;
    private String dbpw = null;

    @Override
    public void init() throws ServletException {

        dbuser = getInitParameter("dbUser");
        dbpw = getInitParameter("dbPassword");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String jsonArray = (String) request.getParameter("recordJSON");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionStr = "jdbc:mysql://localhost/DisasterAssessment";
            con = DriverManager.getConnection(connectionStr, dbuser, dbpw);
            JSONArray x = new JSONArray(jsonArray);
            String idsInserted = "";
            for (int i = 0; i < x.length(); i++) {
                JSONObject j = x.getJSONObject(0);

                int flag = StoreData(j, con,idsInserted);
                System.out.println(flag);
                if (flag == 1) {
                    request.setAttribute("submitStatus", "OK");
                    request.setAttribute("idsInserted", idsInserted);
            request.setAttribute("formSuccessMessage", "The disaster assessment record has been sent to the server successfully.");
                } else if (flag == 0) {
                    request.setAttribute("FormSubmitMessage", "Your Assessment Form has already existed in database.");
                    String destination = "/welcome.jsp";
                    RequestDispatcher red = getServletContext().getRequestDispatcher(destination);
                    red.forward(request, response);
                    break;
                } else {
                    request.setAttribute("submitStatus", "ERROR");
                    //request.setAttribute("idsInserted", idsInserted);
                    request.setAttribute("formFailureMessage", "The disaster assessment could not be saved to the server at this time. The assessment was stored locally on your device. "
                    + "Please visit the Sync page to try again!");
                }
                String destination = "/welcome.jsp";
                RequestDispatcher red = getServletContext().getRequestDispatcher(destination);
                red.forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("submitStatus", "JSON ERROR");
            String destination = "/welcome.jsp";
            //request.setAttribute("FormSubmitMessage", "Your Assessment Form has been submitted successfully!");
            RequestDispatcher red = getServletContext().getRequestDispatcher(destination);
            red.forward(request, response);
            System.out.println(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FillInForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int StoreData(JSONObject j, Connection con,String idsInserted) {
        java.util.Date utilDate = new java.util.Date();

        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        try {
            int id = (Integer) j.get("id");
            //System.out.println("id = " + id);
            String volunteerIdStr = (String) j.get("volunteer_id");
            int volunteerId = Integer.parseInt(volunteerIdStr);
            String volunteerName = (String) j.get("volunteer_name");
            //Date startTime= (Date)j.get("start_time");
            //Date endTime= (Date)j.get("end_time");
            String streetName = (String) j.get("street_name");
            String houseNum = (String) j.get("tel_house_num");
            String Address = houseNum + streetName;
            String apt = (String) j.get("tel_apt");
            String city = (String) j.get("txt_city");
            String state = (String) j.get("txt_state");
            String zip = (String) j.get("tel_zip");
            String firstName = (String) j.get("txt_first_name");
            String lastName = (String) j.get("txt_last_name");
            String landlordName = (String) j.get("landlord_name");
            String landlordPhone = (String) j.get("landlord_tel");
            String dwellingType = (String) j.get("dwelling_type");
            String insuranceInfo_f = (String) j.get("insurance_information_f");
            String insuranceInfo_s = (String) j.get("insurance_information_s");
            String insuranceInfo_c = (String) j.get("insurance_information_c");
            String ownerInfo = (String) j.get("owner_information");
            String floorNum = (String) j.get("number_floors");
            int num_of_floor = -1;
            int waterLivingInt = -1;
            int waterBasementInt = -1;
            if (floorNum != null && !floorNum.equals("")) {
                num_of_floor = Integer.parseInt(floorNum);
            }
            String isBasement = (String) j.get("is_basement");
            String isBasementOccupied = (String) j.get("is_basement_occupied");
            String basementComment = (String) j.get("txtarea_basement_comment");
            String waterLiving = (String) j.get("water_in_living_area");
            if (waterLiving != null && !waterLiving.equals("")) {
                waterLivingInt = Integer.parseInt(waterLiving);
            }
            String waterBasement = (String) j.get("water_in_basement");
            if (waterBasement != null && !waterBasement.equals("")) {
                waterBasementInt = Integer.parseInt(waterBasement);
            }
            String isElectricOn = (String) j.get("electric");
            String isGasOn = (String) j.get("gas");
            String Electrical_service_box = (String) j.get("d_electrical");
            String Furnace = (String) j.get("d_furnace");
            String Heat_Water_Heater = (String) j.get("d_water");
            String Washer = (String) j.get("d_washer");
            String Dryer = (String) j.get("d_dryer");
            String Stove = (String) j.get("d_stove");
            String Regfrigerator = (String) j.get("d_fridge");
            String classification = (String) j.get("select_classification");
            String reason = (String) j.get("txtArea_classification_reason");
            String comments = (String) j.get("txtArea_comment");

            //check whether it is already stored in database
            if (Cases.CheckDuplicate(con, volunteerId, sqlDate, sqlDate) != null) {
                return 0;// return 0 means the record is duplicated. It exists in database
            }
            Client client = new Client(Address, apt, city, state, zip, "", "", lastName, firstName);
            client.Insert(con);
            int clientId = client.getId(con);
            System.out.println("clientId:" + clientId);

            Building building = new Building(landlordName, landlordPhone, dwellingType, insuranceInfo_f, insuranceInfo_s, insuranceInfo_c, ownerInfo);
            building.Insert(con);

            int buildId = building.getId(con);
            System.out.println("BuildingId:" + buildId);

            DamageAssessment ds = new DamageAssessment(classification, "", "", num_of_floor, isBasement, waterLivingInt, waterBasementInt, isGasOn, isElectricOn, isBasementOccupied, basementComment, reason,
                    Electrical_service_box, Furnace, Heat_Water_Heater, Washer, Dryer, Stove, Regfrigerator);
            ds.insert(con);
            int damageAssessmentId = ds.getId(con);

            Cases caseInstance = new Cases(comments, clientId, damageAssessmentId, buildId, volunteerId, sqlDate, sqlDate);
            caseInstance.Insert(con);
            idsInserted+= id;
            return 1;
        } catch (Exception e) {
            return -1;
        }

    }
}
