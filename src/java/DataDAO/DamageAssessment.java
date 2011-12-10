package DataDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DamageAssessment {

    private int assessmentId;
    private String structuralDamage;
    private String debrisAmout;
    private String bizInventoryLoss;
    private int numFloor;
    private String isBasement;
    private int waterLevelLivingArea;
    private int waterLevelBasement;
    private String isGasOn;
    private String isElectricityOn;
    private String isBasementOccupied;
    private String OccupiedDescription;
    private String reason;
    private String electriccalBox;
    private String furnace;
    private String hotWaterHeater;
    private String washer;
    private String dryer;
    private String stove;
    private String refrigerator;

    public String getOccupiedDescription() {
        return OccupiedDescription;
    }

    public void setOccupiedDescription(String OccupiedDescription) {
        this.OccupiedDescription = OccupiedDescription;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getBizInventoryLoss() {
        return bizInventoryLoss;
    }

    public void setBizInventoryLoss(String bizInventoryLoss) {
        this.bizInventoryLoss = bizInventoryLoss;
    }

    public String getDebrisAmout() {
        return debrisAmout;
    }

    public void setDebrisAmout(String debrisAmout) {
        this.debrisAmout = debrisAmout;
    }

    public String getDryer() {
        return dryer;
    }

    public void setDryer(String dryer) {
        this.dryer = dryer;
    }

    public String getElectriccalBox() {
        return electriccalBox;
    }

    public void setElectriccalBox(String electriccalBox) {
        this.electriccalBox = electriccalBox;
    }


    public String getFurnace() {
        return furnace;
    }

    public void setFurnace(String furnace) {
        this.furnace = furnace;
    }

    public String getHotWaterHeater() {
        return hotWaterHeater;
    }

    public void setHotWaterHeater(String hotWaterHeater) {
        this.hotWaterHeater = hotWaterHeater;
    }

    public String getIsBasement() {
        return isBasement;
    }

    public void setIsBasement(String isBasement) {
        this.isBasement = isBasement;
    }

    public String getIsBasementOccupied() {
        return isBasementOccupied;
    }

    public void setIsBasementOccupied(String isBasementOccupied) {
        this.isBasementOccupied = isBasementOccupied;
    }

    public String getIsElectricityOn() {
        return isElectricityOn;
    }

    public void setIsElectricityOn(String isElectricityOn) {
        this.isElectricityOn = isElectricityOn;
    }

    public String getIsGasOn() {
        return isGasOn;
    }

    public void setIsGasOn(String isGasOn) {
        this.isGasOn = isGasOn;
    }

    public int getNumFloor() {
        return numFloor;
    }

    public void setNumFloor(int numFloor) {
        this.numFloor = numFloor;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(String refrigerator) {
        this.refrigerator = refrigerator;
    }


    public String getStove() {
        return stove;
    }

    public void setStove(String stove) {
        this.stove = stove;
    }

    public String getStructuralDamage() {
        return structuralDamage;
    }

    public void setStructuralDamage(String structuralDamage) {
        this.structuralDamage = structuralDamage;
    }

    public String getWasher() {
        return washer;
    }

    public void setWasher(String washer) {
        this.washer = washer;
    }

    public int getWaterLevelBasement() {
        return waterLevelBasement;
    }

    public void setWaterLevelBasement(int waterLevelBasement) {
        this.waterLevelBasement = waterLevelBasement;
    }

    public int getWaterLevelLivingArea() {
        return waterLevelLivingArea;
    }

    public void setWaterLevelLivingArea(int waterLevelLivingArea) {
        this.waterLevelLivingArea = waterLevelLivingArea;
    }

    public DamageAssessment() {
    }

    public DamageAssessment(String sd, String da, String bil, int numFloor,
            String isBasement, int waterLvLivingArea, int waterLvBasement,
            String isGasOn, String isElectricityOn, String isBasementOccupied,
            String desc, String reason, String electriccalBox, String furnace,
            String hotWaterHeater, String washer, String dryer, String stove,
            String refrigerator) {

        this.structuralDamage = sd == null ? "" : sd;
        this.debrisAmout = da == null ? "" : da;
        this.bizInventoryLoss = bil == null ? "" : bil;
        this.numFloor = numFloor;
        this.isBasement = isBasement == null ? "" : isBasement;
        this.waterLevelLivingArea = waterLvLivingArea;
        this.waterLevelBasement = waterLvBasement;
        this.isGasOn = isGasOn == null ? "" : isGasOn;
        this.isElectricityOn = isElectricityOn == null ? "" : isElectricityOn;
        this.isBasementOccupied = isBasementOccupied == null ? "" : isBasementOccupied;
        this.OccupiedDescription = desc == null ? "" : desc;
        this.reason = reason == null ? "" : reason;
        this.electriccalBox = electriccalBox == null ? "" : electriccalBox;
        this.furnace = furnace == null ? "" : furnace;
        this.hotWaterHeater = hotWaterHeater == null ? "" : hotWaterHeater;
        this.washer = washer == null ? "" : washer;
        this.dryer = dryer == null ? "" : dryer;
        this.stove = stove == null ? "" : stove;
        this.refrigerator = refrigerator == null ? "" : refrigerator;
    }

    public void insert(Connection con) throws SQLException {
        PreparedStatement stat = null;
        try {
            String SQL = "INSERT INTO Damage_Assessment"
                    + "(Structural_Damage,"
                    + " Number_Of_Floor, Is_There_Basement, Water_Level_Living_Area,"
                    + " Water_Level_Basement, Is_Electricity_On, Is_Gas_On, Basement_Occupied,"
                    + "Occupied_Description, Classification_Reason, Electrical_service_box,"
                    + "Furnace, Hot_Water_Heater, Washer, Dryer, Stove, Refrigerator,"
                    + "Start_Time, Completion_Time) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            stat = con.prepareStatement(SQL);
            stat.setString(1, structuralDamage);
            //stat.setString(2, debrisAmout);
            //stat.setString(3, bizInventoryLoss);
            stat.setInt(2, numFloor);
            stat.setString(3, isBasement);
            stat.setInt(4, waterLevelLivingArea);
            stat.setInt(5, waterLevelBasement);
            stat.setString(6, isGasOn);
            stat.setString(7, isElectricityOn);
            stat.setString(8, isBasementOccupied);
            stat.setString(9, OccupiedDescription);
            stat.setString(10, reason);
            stat.setString(11, electriccalBox);
            stat.setString(12, furnace);
            stat.setString(13, hotWaterHeater);
            stat.setString(14, washer);
            stat.setString(15, dryer);
            stat.setString(16, stove);
            stat.setString(17, refrigerator);
            stat.executeUpdate();
            System.out.println("Successfully INSERT INTO Damage_Assessment");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stat != null) {
                stat.close();
            }
        }
    }

    public int getId(Connection con) throws SQLException {

        PreparedStatement stat = null;
        try {
            String sql = "select Assessment_Id from Damage_Assessment where Structural_Damage =? and Number_Of_Floor=? and Is_There_Basement=? "
                    + "and Water_Level_Living_Area=? and Water_Level_Basement=? and Is_Electricity_On=? and Is_Gas_On=? "
                    + "and Basement_Occupied=? and Occupied_Description=? and Classification_Reason=? and Electrical_service_box=? "
                    + "and Furnace=? and Hot_Water_Heater=? and Washer=? and Dryer=? and Stove=? and Refrigerator=? "
                    + "and Start_Time=? and Completion_Time=?";
            System.out.println(sql);
            stat = con.prepareStatement(sql);
            stat.setString(1, structuralDamage);
            //stat.setString(2, debrisAmout);
            //stat.setString(3, bizInventoryLoss);
            stat.setInt(2, numFloor);
            stat.setString(3, isBasement);
            stat.setInt(4, waterLevelLivingArea);
            stat.setInt(5, waterLevelBasement);
            stat.setString(6, isGasOn);
            stat.setString(7, isElectricityOn);
            stat.setString(8, isBasementOccupied);
            stat.setString(9, OccupiedDescription);
            stat.setString(10, reason);
            stat.setString(11, electriccalBox);
            stat.setString(12, furnace);
            stat.setString(13, hotWaterHeater);
            stat.setString(14, washer);
            stat.setString(15, dryer);
            stat.setString(16, stove);
            stat.setString(17, refrigerator);

            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                assessmentId = rs.getInt("Assessment_Id");

            }
            System.out.println("Successfully selected from Damage_Assessment");
            return assessmentId;
        } catch (SQLException e) {

            e.printStackTrace();
            return -1;

        } finally {
            if (stat != null) {
                stat.close();
            }
        }

    }

    public void getByID(Connection con, int dmgAsmtID) throws SQLException {
        PreparedStatement stat = con.prepareStatement("SELECT * FROM Damage_Assessment WHERE Assessment_Id = ? ");
        stat.setInt(1, dmgAsmtID);
        ResultSet rs = stat.executeQuery();

        if (rs.next()) {
            this.assessmentId = dmgAsmtID;
            this.structuralDamage = rs.getString("Structural_Damage");
            this.debrisAmout = rs.getString("Debris_Amount");
            this.bizInventoryLoss = rs.getString("Business_Inventory_Loss");
            this.numFloor = rs.getInt("Number_Of_Floor");
            this.isBasement = isBasement = rs.getString("Is_There_Basement");
            this.waterLevelLivingArea = rs.getInt("Water_Level_Living_Area");
            this.waterLevelBasement = rs.getInt("Water_Level_Basement");
            this.isGasOn = rs.getString("Is_Gas_On");
            this.isElectricityOn = rs.getString("Is_Electricity_On");
            this.isBasementOccupied = rs.getString("Basement_Occupied");
            this.OccupiedDescription = rs.getString("Occupied_Description");
            this.reason = rs.getString("Classification_Reason");
            this.electriccalBox = rs.getString("Electrical_service_box");
            this.furnace = rs.getString("Furnace");
            this.hotWaterHeater = rs.getString("Hot_Water_Heater");
            this.washer = rs.getString("Washer");
            this.dryer = rs.getString("Dryer");
            this.stove = rs.getString("Stove");
            this.refrigerator = rs.getString("Refrigerator");
            ;
        }
    }
}
