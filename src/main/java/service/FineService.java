package service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.FineDAO;
import dao.LoansDAO;
import modl.Fine;
import modl.Loan;

public class FineService {

	public String addbookFine(Fine fine) {
		if (fine.getLoanId() == null || fine.getLoanId() == 0) {
			return "Title cannot be empty!";
		}

		if (fine.getLoanId() == null || fine.getLoanId() == 0) {
			return "Title cannot be empty!";
		}

		FineDAO f = new FineDAO();
		f.insertFine(fine);

		return null;
	}

	public List<Fine> findeAlluserfines(String userid) {
		List<Fine> fines = new ArrayList<>();
		List<Loan> loans = new ArrayList<>();

		if (userid == null || userid.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a user ID.");
			return null;
		}

		int idd = 0;
		try {
			idd = Integer.parseInt(userid);
			if (idd <= 0) {
				JOptionPane.showMessageDialog(null, "ID must be a positive number.");
				return null;
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "ID must be a number.");
			return null;
		}

		FineDAO finedao = new FineDAO();

		LoanService loanservice = new LoanService();
		loans = loanservice.findeAllUserLoans(idd);

		for (int i = 0; i < loans.size(); i++) {
			// if (loans.get(i) != null)
			// {
			int loanid = loans.get(i).getLoanId();
			Fine fine = new Fine();
			fine = finedao.findeuserFines(loanid);
			if (fine != null) {
				fines.add(fine);
			}
		}
		// }

		return fines;

	}

	public String payFine(String userid, String fineid) {
		if (userid == null || userid.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a book ID.");
			return null;
		}
		if (fineid == null || fineid.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a book ID.");
			return null;
		}

		int uidd = 0;
		int fidd = 0;
		try {

			uidd = Integer.parseInt(userid);
			if (uidd <= 0) {
				JOptionPane.showMessageDialog(null, "ID must be a positive number.");
				return null;
			}
			fidd = Integer.parseInt(fineid);
			if (fidd <= 0) {
				JOptionPane.showMessageDialog(null, "ID must be a positive number.");
				return null;
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "ID must be a number.");
			return null;
		}

		LoansDAO loandao = new LoansDAO();
		FineDAO finedao = new FineDAO();

		Fine fin = finedao.findeFineByFineId(fidd);
		boolean finestatus = fin.getstatus();

		if (finestatus) {
			finedao.deletefine(fidd);
			loandao.deleteloan(fin.getLoanId());
			return "fine is payed and book is returned ";
		} else {
			return "your loan hasnt expierd yet";
		}

	}

}
