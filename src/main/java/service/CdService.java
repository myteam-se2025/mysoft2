package service;

import java.sql.SQLException;

import dao.CdDao;
import modl.Cd;

public class CdService {

    private CdDao cdDao;

    public CdService() throws SQLException {
        cdDao = new CdDao();
    }

    public void addCd(Cd cd) {
        cdDao.addCd(cd);
    }

}