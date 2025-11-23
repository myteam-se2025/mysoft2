package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modl.Fine;

public class FineDAO extends BaseDAO {

	public boolean deletefine(int fineid) {

		String sql = "DELETE FROM public.fines WHERE fine_id = ?";

		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, fineid);

			int rowsAffected = ps.executeUpdate();

			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Fine> findeAllFines(int loanid) {
		List<Fine> fines = new ArrayList<>();

		String sql = "SELECT * FROM public.fines WHERE loan_id = ?";

		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, loanid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Fine fine = new Fine(rs.getInt("fine_id"), rs.getInt("loan_id"), rs.getInt("amount"),
						rs.getBoolean("status"),
						rs.getDate("issued_date") != null ? rs.getDate("issued_date").toLocalDate() : null);
				if (fine != null) {
					fines.add(fine);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return fines;
	}

	public Fine findeFineByFineId(int id) {

		String sql = "SELECT * FROM public.fines WHERE fine_id = ?";

		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Fine(rs.getInt("fine_id"), rs.getInt("loan_id"), rs.getInt("amount"),
						rs.getBoolean("status"),
						rs.getDate("issued_date") != null ? rs.getDate("issued_date").toLocalDate() : null);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Fine findeuserFines(int id) {

		String sql = "SELECT * FROM public.fines WHERE loan_id = ?";

		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Fine(rs.getInt("fine_id"), rs.getInt("loan_id"), rs.getInt("amount"),
						rs.getBoolean("status"),
						rs.getDate("issued_date") != null ? rs.getDate("issued_date").toLocalDate() : null);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void insertFine(Fine fine) {
		String sql = "INSERT INTO public.fines (loan_id , amount , issued_date , status ) VALUES (?, ?, ? , ?)";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			con.setAutoCommit(true);

			ps.setInt(1, fine.getLoanId());
			ps.setInt(2, fine.getAmount());

			ps.setDate(3, java.sql.Date.valueOf(fine.getDateIssued()));
			ps.setBoolean(4, fine.getstatus());

			ps.executeUpdate();

		}

		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public Fine insurtFine() {

		return null;

	}

}
