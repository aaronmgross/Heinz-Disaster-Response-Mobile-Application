package DataDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CaseNeeds {
	private int case_id;
	private int need_id;
	private String need_value;

	public CaseNeeds() {
	};

	public CaseNeeds(int case_id, int need_id, String need_value) {
		this.case_id = case_id;
		this.need_id = need_id;
		this.need_value = need_value==null?"":need_value;
	}

	public void insert(Connection con) throws SQLException {
		PreparedStatement stat = null;
		try {
			stat = con.prepareStatement("INSERT INTO Case_Needs VALUES (?,?,?)");
			stat.setInt(1, case_id);
			stat.setInt(2, need_id);
			stat.setString(3, need_value);
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
