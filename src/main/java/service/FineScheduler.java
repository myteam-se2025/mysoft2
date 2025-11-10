package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Timer;



public class FineScheduler {

	 private Connection connection;

	    public FineScheduler(Connection connection) {
	        this.connection = connection;
	    }
   /*
	    public void startDailyCheck() {
	        // Run immediately, then every 24 hours (86,400,000 ms)
	        Timer timer = new Timer(true);
	        timer.scheduleAtFixedRate(new TimerTask() {
	            @Override
	            public void run() {
	                checkOverdueLoans();
	            }
	        }, 0, 86_400_000);
	    }*/
	    
}
