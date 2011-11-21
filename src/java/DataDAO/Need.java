package DataDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Need {

    private int need_id;
    private String need_name;

    public Need() {
    }

    ;

    public Need(String name) {
        this.need_name = name;
    }

    public void insert(Connection con) throws SQLException {
        PreparedStatement stat = null;
        try {
            stat = con.prepareStatement("INSERT INTO Needs (Need_name) Values(?)");
            stat.setString(1, need_name);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stat != null) {
                stat.close();
            }
        }
    }

    public int getId(String needName,Connection con) throws SQLException {

        PreparedStatement statement = null;
        int needId=0;

        try {
            String sql = "select Need_Id from Needs where Need_name =?";
            statement = con.prepareStatement(sql);
            statement.setString(1, needName);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                needId = rs.getInt("Need_Id");

            }
            System.out.println("Successfully selected from Needs");
            return needId;
        } catch (SQLException e) {

            e.printStackTrace();
            return -1;

        } finally {
            if (statement != null) {
                statement.close();
            }
        }

    }
}
