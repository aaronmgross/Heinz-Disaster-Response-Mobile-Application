package DataDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Need {
	private int need_id;
	private String need_name;

	Need() {
	};

	Need(String name) {
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

}
