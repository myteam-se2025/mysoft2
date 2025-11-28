package modl;

import java.sql.Date;

/**
 * @auther khadeja and masa
 * @param User            class to represent a user in the system
 * @param user_id         int unique identifier for the user
 * @param full_name       String full name of the user
 * @param email           String email address of the user
 * @param phone           String phone number of the user
 * @param address         String physical address of the user
 * @param membership_date Date date when the user joined the system
 */

public class User {

	int user_id = 0;
	String full_name = "null";
	String email = "null";
	String phone = "null";
	String address = "null"; 
	Date membership_date = null;

	public User(int user_id, String full_name, String email, String phone, String address, Date membership_date) {
		this.user_id = user_id;
		this.full_name = full_name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.membership_date = membership_date;
	}
	public User() {}

	public User(String full_name, String email, String phone, String address, Date membership_date) {

		this.full_name = full_name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.membership_date = membership_date;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getFull_name() {
		return full_name;
	}

	public Date getMembership_date() {
		return membership_date;
	}

	public String getPhone() {
		return phone;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public void setMembership_date(Date membership_date) {
		this.membership_date = membership_date;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
