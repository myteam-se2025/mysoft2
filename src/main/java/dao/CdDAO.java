package dao;

import java.sql.*;
import modl.Cd;

/**
 * CdDAO handles database operations related to CDs.
 * Provides methods to insert new CDs and search for CDs by title and ID.
 * 
 * @author Library
 * @version 1.0
 * @since 2025
 */
public class CdDAO extends BaseDAO {

	/**
     * Inserts a new CD into the database.
     *
     * @param cd the Cd object containing CD details
     * @return true if insertion was successful, false otherwise
     */
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

	/**
     * Searches for a CD by its title and ID.
     *
     * @param title the title of the CD
     * @param id    the unique ID of the CD
     * @return the Cd object if found, null otherwise
     */
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