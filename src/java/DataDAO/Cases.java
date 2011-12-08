package DataDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public Cases(String comment, int clientId, int damageAssessmentId, int buildingId, int userId) {
        this.comment = comment == null ? "" : comment;
        this.clientId = clientId;
        this.damageAssessmentId = damageAssessmentId;
        this.buildingId = buildingId;
        this.userId = userId;
    }

    public Cases() {
    }

    public void Insert(Connection con) throws SQLException {


        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("Insert into Cases(Comment,Client_Id,Damage_Assessment_Id,Building_Id,User_Id) values(?,?,?,?,?)");

            statement.setString(1, comment);
            statement.setInt(2, clientId);
            statement.setInt(3, damageAssessmentId);
            statement.setInt(4, buildingId);
            statement.setInt(5, userId);

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {
            if (statement != null) {
                statement.close();
            }
        }

    }

        public void getById(Connection con,int caseID) throws SQLException{
          PreparedStatement stat = con.prepareStatement("SELECT * FROM Cases WHERE Case_Id = ? ");
        stat.setInt(1, caseID);
        ResultSet rs = stat.executeQuery();

        if (rs.next()) {
           this.caseId = caseID;
            this.comment = rs.getString("Comment");
            this.damageAssessmentId = rs.getInt("Damage_Assessment_Id");
            this.buildingId = rs.getInt("Building_Id");
            this.userId = rs.getInt("User_Id");


        }

    }

    public int getId(Connection con) throws SQLException {

        PreparedStatement statement = null;
        try {
            String sql = "select Case_Id from Cases where Comment =? and Client_Id=? and Damage_Assessment_Id =? and Building_Id=? and User_Id=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, comment);
            statement.setInt(2, clientId);
            statement.setInt(3, damageAssessmentId);
            statement.setInt(4, buildingId);
            statement.setInt(5, userId);

            ResultSet rs = statement.executeQuery();
            //buildId=100;
            while (rs.next()) {
                caseId = rs.getInt("Case_Id");

            }
            System.out.println("Successfully selected from Building");
            return caseId;
        } catch (SQLException e) {

            e.printStackTrace();
            return -1;

        } finally {
            if (statement != null) {
                statement.close();
            }
        }

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
