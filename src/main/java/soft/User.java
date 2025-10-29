package soft;

import java.sql.Date;

public class User {

	int user_id =0;
	String full_name="null";
	String email="null";
	String phone="null";
	String address="null";
	Date membership_date=null;
	
	 public User(int user_id, String full_name, String email, String phone, String address, Date membership_date) {
	        this.user_id = user_id;
	        this.full_name = full_name;
	        this.email = email;
	        this.phone = phone;
	        this.address = address;
	        this.membership_date = membership_date;
	    }
	    public int getUser_id() {
	        return user_id;
	    }
	    public String getFull_name() {
	        return full_name;
	    }
	    public String getEmail() {
	        return email;
	    }
	    public String getPhone() {
	        return phone;
	    }
	    public String getAddress() {
	        return address;
	    }
	    public Date getMembership_date() {
	        return membership_date;
	    }

	    public void setUser_id(int user_id) {
	        this.user_id = user_id;
	    }
	    public void setFull_name(String full_name) {
	        this.full_name = full_name;
	    }
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    public void setPhone(String phone) {
	        this.phone = phone;
	    }
	    public void setAddress(String address) {
	        this.address = address;
	    }
	    public void setMembership_date(Date membership_date) {
	        this.membership_date = membership_date;
	    }
}
