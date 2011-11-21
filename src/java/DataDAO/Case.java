/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Michelle
 */
public class Case {

    private int caseId;
    private String comment;
    private int clientId;
    private int damageAssessmentId;
    private int buildingId;
    private int userId;

    public Case(String comment, int clientId, int damageAssessmentId, int buildingId, int userId) {
        this.comment = comment;
        this.clientId = clientId;
        this.damageAssessmentId = damageAssessmentId;
        this.buildingId = buildingId;
        this.userId = userId;
    }
    public Case(){}

    public void Insert(Connection con) throws SQLException {


        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("Insert into Case(Comment,Client_Id,Damage_Assessment_Id,Building_Id,User_Id) values(?,?,?,?,?)");

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
