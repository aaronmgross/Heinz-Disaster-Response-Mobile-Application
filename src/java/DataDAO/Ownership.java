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
public class Ownership {

      private String code;
      private String description;

      public Ownership(String code,String description){

          this.code = code;
          this.description = description;
      }
      public Ownership(){}

      public void Insert(Connection con) throws SQLException{

          PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("Insert into Ownership(Code,Description) values(?,?)");

            statement.setString(1, code);
            statement.setString(2, description);

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {
            if (statement != null) {
                statement.close();
            }
        }

      }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
