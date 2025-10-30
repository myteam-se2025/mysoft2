package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modl.Cd;

public class CdDao {

    private Connection con;

    public CdDao() throws SQLException {
        con = DbConnection.getConnection();
    }

    public void addCd(Cd cd) {

        String sql = "INSERT INTO public.cds (title, artist, genre, available_copies) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, cd.getTitle());
            pstmt.setString(2, cd.getArtist());
            pstmt.setString(3, cd.getGenre());
            pstmt.setInt(4, cd.getAvailable_copies());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchCdByTitleAndId(String title, int id) {

        String sql = "SELECT * FROM public.cds WHERE title = ? AND cd_id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setInt(2, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("CD Found: " + rs.getString("title") +
                                   ", Artist: " + rs.getString("artist") +
                                   ", Genre: " + rs.getString("genre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}