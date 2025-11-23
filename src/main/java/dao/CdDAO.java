package dao;

import java.sql.*;
import modl.Cd;

public class CdDAO extends BaseDAO {

	// إدراج CD جديد
	public boolean insertCd(Cd cd) {
		String sql = "INSERT INTO public.cds (title, artist, genre, available_copies) VALUES (?, ?, ?, ?)";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, cd.getTitle());
			ps.setString(2, cd.getArtist());
			ps.setString(3, cd.getGenre());
			ps.setInt(4, cd.getAvailable_copies());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// البحث عن CD حسب الاسم و ID
	public Cd searchCdByTitleAndId(String title, int id) {
		String sql = "SELECT * FROM public.cds WHERE title = ? AND cd_id = ?";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, title);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Cd cd = new Cd(rs.getString("title"), rs.getString("artist"), rs.getString("genre"),
						rs.getInt("available_copies"));
				cd.setCd_id(rs.getInt("cd_id"));
				return cd;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}