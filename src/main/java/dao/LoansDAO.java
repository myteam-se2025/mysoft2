package dao;

import java.sql.Connection;
import java.sql.Date;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modl.*;
import dao.*;

public class LoansDAO extends BaseDAO {

	public void allOverDueLoans() throws SQLException {
		Date loanduedate = null;
		int loanid = 0;

		String sql = "SELECT * FROM public.loans ";

		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				loanduedate = rs.getDate("due_date");
				loanid = rs.getInt("loan_id");
				LocalDate loanduedatelocal = loanduedate.toLocalDate();

				if (LocalDate.now().isAfter(loanduedatelocal)) {
					
				}

			}
		}
	}

	public boolean deleteloan(int loanid) {

		String sql = "DELETE FROM public.loans WHERE loan_id = ?";

		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, loanid);

			int rowsAffected = ps.executeUpdate();

			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public Loan findeLoanBybookId(int id) {

		String sql = "SELECT * FROM public.loans WHERE book_id = ?";

		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Loan(rs.getInt("Loan_id"), rs.getInt("user_id"), rs.getInt("book_id"), rs.getInt("cd_id"),
						rs.getDate("loan_date") != null ? rs.getDate("loan_date").toLocalDate() : null,
						rs.getDate("due_date") != null ? rs.getDate("due_date").toLocalDate() : null

				);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<Loan> findeLoanByUserId(int id) {
		List<Loan> loans = new ArrayList<>();
		String sql = "SELECT * FROM public.loans WHERE user_id = ?";

		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Loan loan = new Loan(rs.getInt("Loan_id"), rs.getInt("user_id"), rs.getInt("book_id"),
						rs.getInt("cd_id"),
						rs.getDate("loan_date") != null ? rs.getDate("loan_date").toLocalDate() : null,
						rs.getDate("due_date") != null ? rs.getDate("due_date").toLocalDate() : null

				);
				if (loans != null) {
					loans.add(loan);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loans;

	}

	public int insertintloan(Loan loan) {
		String sql = "INSERT INTO public.loans ( user_id, book_id,  loan_date, due_date) VALUES (?, ?, ?, ?) RETURNING loan_id";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, loan.getUserId());
			ps.setInt(2, loan.getBookId());

			ps.setDate(3, java.sql.Date.valueOf(loan.getLoanDate()));
			ps.setDate(4, java.sql.Date.valueOf(loan.getDueDate()));

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int generatedId = rs.getInt("loan_id");
				loan.setLoanId(generatedId);
				return generatedId;
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return -1;
	}

	public boolean insertloan(Loan loan) {
		String sql = "INSERT INTO public.loans ( user_id, book_id,  loan_date, due_date) VALUES (?, ?, ?, ?)";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, loan.getUserId());
			ps.setInt(2, loan.getBookId());

			ps.setDate(3, java.sql.Date.valueOf(loan.getLoanDate()));
			ps.setDate(4, java.sql.Date.valueOf(loan.getDueDate()));

			ps.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
