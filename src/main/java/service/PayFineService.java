package service;

import javax.swing.JOptionPane;

import modl.Fine;

public class PayFineService {

    private FineService fineService;

    public PayFineService(FineService fineService) {
        this.fineService = fineService;
    }

    public String payfine(String userid, String fineid) {
        fineService.payFine(userid, fineid);
        return "Paid successfully";
    }
}
