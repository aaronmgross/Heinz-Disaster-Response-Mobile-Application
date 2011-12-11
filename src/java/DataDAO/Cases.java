package DataDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 *
 * @author Michelle
 */
public class Cases {

    private int caseId;
    private String comment;
    private int clientId;
    private int damageAssessmentId;
    private int buildingId;
    private int userId;
    private java.sql.Date startTime;
    private java.sql.Date endTime;

    public Cases(String comment, int clientId, int damageAssessmentId, int buildingId, int userId, Date startTime, Date endTime) {
        this.comment = comment == null ? "" : comment;
        this.clientId = clientId;
        this.damageAssessmentId = damageAssessmentId;
        this.buildingId = buildingId;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Cases() {
    }

    public void Insert(Connection con) throws SQLException {


        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("Insert into Cases(Comment,Client_Id,Damage_Assessment_Id,Building_Id,User_Id,Start_Time,Completion_Time) values(?,?,?,?,?,?,?)");

            statement.setString(1, comment);
            statement.setInt(2, clientId);
            statement.setInt(3, damageAssessmentId);
            statement.setInt(4, buildingId);
            statement.setInt(5, userId);
            statement.setDate(6, startTime);
            statement.setDate(7, endTime);

            statement.executeUpdate();

        }finally {          
                statement.close();
        }

    }

    public void getById(Connection con, int caseID) throws SQLException {
        PreparedStatement stat = con.prepareStatement("SELECT * FROM Cases WHERE Case_Id = ? ");
        stat.setInt(1, caseID);
        ResultSet rs = stat.executeQuery();

        if (rs.next()) {
            this.caseId = caseID;
            this.comment = rs.getString("Comment");
            this.damageAssessmentId = rs.getInt("Damage_Assessment_Id");
            this.buildingId = rs.getInt("Building_Id");
            this.userId = rs.getInt("User_Id");
            this.startTime = rs.getDate("Start_Time");
            this.endTime = rs.getDate("Completion_Time");
        }
           stat.close();
           rs.close();

    }

    public static Cases CheckDuplicate(Connection con, int userID, java.sql.Date StartTime, java.sql.Date EndTime) throws SQLException {

        PreparedStatement statement = null;
        ResultSet rs = null;
        Cases case1 = null;
        try {
            statement = con.prepareStatement("select * from Cases where User_Id=? and Start_Time=? and Completion_Time=?");
            statement.setInt(1, userID);
            statement.setDate(2, StartTime);
            statement.setDate(3, EndTime);
            rs = statement.executeQuery();
            if (!rs.next()) {
                case1 = null;
            } else {
                case1 = new Cases();
                case1.setCaseId(rs.getInt("Case_Id"));
                case1.setStartTime(rs.getDate("Start_Time"));
            }
        } finally {          
           statement.close();
           rs.close();
        }
        return case1;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getDamageAssessmentId() {
        return damageAssessmentId;
    }

    public void setDamageAssessmentId(int damageAssessmentId) {
        this.damageAssessmentId = damageAssessmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
