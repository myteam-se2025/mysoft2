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

/**
 * LoansDAO handles all database operations related to loans in the library system.
 * Provides methods to insert, retrieve, delete, and check overdue loans.
 * 
 * @author Library
 * @version 1.0
 * @since 2025
 */
public class LoansDAO extends BaseDAO {

	 /**
     * Checks all loans and updates overdue fines if needed.
     *
     * @throws SQLException if a database access error occurs
     */
	public void allOverDueLoans() throws SQLException {
	    String sql = "SELECT loan_id, due_date FROM public.loans";
	    
	    try (Connection con = getConnection(); 
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        
	        FineDAO fineDAO = new FineDAO();
	        LocalDate today = LocalDate.now();
	        
	        while (rs.next()) {
	            int loanId = rs.getInt("loan_id");
	            LocalDate dueDate = rs.getDate("due_date").toLocalDate();
	            /*
	            // If loan is overdue
	            if (today.isAfter(dueDate)) {
	                // Find the fine for this loan
	                Fine fine = fineDAO.findeuserFines(loanId);
	                
	                if (fine != null && !fine.getstatus()) {
	                    // Update fine status to true (overdue)
	                    fine.setStatus(true);
	                    String updateSql = "UPDATE fines SET status = true WHERE loan_id = ?";
	                    
	                    try (PreparedStatement updatePs = con.prepareStatement(updateSql)) {
	                        updatePs.setInt(1, loanId);
	                        updatePs.executeUpdate();
	                    }
	                }
	            }*/
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }
	}
	/**
     * Deletes a loan by its ID.
     *
     * @param loanid the ID of the loan to delete
     * @return true if the loan was successfully deleted, false otherwise
     */
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
	/**
     * Finds a loan by book ID.
     *
     * @param id the book ID
     * @return the Loan object if found, null otherwise
     */
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
	 /**
     * Finds all loans for a specific user by user ID.
     *
     * @param id the user ID
     * @return a list of Loan objects for the user
     */
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
	/**
     * Inserts a loan and returns the generated loan ID.
     *
     * @param loan the Loan object to insert
     * @return the generated loan ID, or -1 if insertion failed
     */
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
	/**
     * Inserts a loan into the database.
     *
     * @param loan the Loan object to insert
     * @return true if insertion was successful, false otherwise
     */
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
