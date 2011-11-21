package DataDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DwellingType {
	private String code;
	private String desc;

	DwellingType() {
	};

	DwellingType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public void insert(Connection con) throws SQLException {
		PreparedStatement stat = null;
		try {
			stat = con.prepareStatement("INSERT INTO Dwelling_Type Values(?,?)");
			stat.setString(1, code);
			stat.setString(2, desc);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(stat != null){
				stat.close();
			}
		}
	}

}
