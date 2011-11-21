package DataDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DamageAssessment {
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
	private String description;
	private String reason;
	private String dwellingType;
	private Date startTime;
	private Date endTime;

	public DamageAssessment() {

	}

	public DamageAssessment(String sd, String da, String bil, int numFloor,
			String isBasement, int waterLvLivingArea, int waterLvBasement,
			String isGasOn, String isElectricityOn, String isBasementOccupied,
			String desc, String reason, String dwellingType, Date startTime,
			Date endTime) {

		this.structuralDamage = sd;
		this.debrisAmout = da;
		this.bizInventoryLoss = bil;
		this.numFloor = numFloor;
		this.isBasement = isBasement;
		this.waterLevelLivingArea = waterLvLivingArea;
		this.waterLevelBasement = waterLvBasement;
		this.isGasOn = isGasOn;
		this.isElectricityOn = isElectricityOn;
		this.isBasementOccupied = isBasementOccupied;
		this.description = desc;
		this.reason = reason;
		this.dwellingType = dwellingType;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public void insert(Connection con) throws SQLException {
		PreparedStatement stat = null;
		try {
			String SQL = "INSERT INTO Damage_Assessment"
					+ " (Structural_Damage, Debris_Amount, Business_Inventory_Loss,"
					+ " Number_Of_Floor, Is_There_Basement, Water_Level_Living_Area,"
					+ " WaterLevel_Basement, Is_Electricity_On, Is_Gas_On, Basement_Occupied,"
					+ "Description, Classification_Reason, Dwelling_Type, Start_Time, Completion_Time"
					+ ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			stat = con.prepareStatement(SQL);
			stat.setString(1, structuralDamage);
			stat.setString(2, debrisAmout);
			stat.setString(3, bizInventoryLoss);
			stat.setInt(4, numFloor);
			stat.setString(5, isBasement);
			stat.setInt(6, waterLevelLivingArea);
			stat.setInt(7, waterLevelBasement);
			stat.setString(8, isGasOn);
			stat.setString(9, isElectricityOn);
			stat.setString(10, isBasementOccupied);
			stat.setString(11, description);
			stat.setString(12, reason);
			stat.setString(13, dwellingType);
			stat.setDate(14, startTime);
			stat.setDate(15, endTime);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null) {
				stat.close();
			}
		}
	}
}
