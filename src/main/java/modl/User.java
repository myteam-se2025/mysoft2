package modl;

import java.sql.Date;

/**
 * Represents a User in the library management system.
 * Contains information about the user's personal details and membership date.
 * Provides constructors, getters, and setters for all fields.
 * 
 * @author Khadeja and Masa
 * @version 1.0
 * @since 2025
 */

public class User {

	int user_id = 0;
	String full_name = "null";
	String email = "null";
	String phone = "null";
	String address = "null"; 
	Date membership_date = null;

	/**
     * Constructs a User with all fields specified.
     *
     * @param user_id the unique user ID
     * @param full_name the full name of the user
     * @param email the email address of the user
     * @param phone the phone number of the user
     * @param address the physical address of the user
     * @param membership_date the membership start date
     */
	public User(int user_id, String full_name, String email, String phone, String address, Date membership_date) {
		this.user_id = user_id;
		this.full_name = full_name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.membership_date = membership_date;
	}
	 /**
     * Default constructor initializing fields with default values.
     */
	public User() {
		this.full_name = full_name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.membership_date = membership_date;
	}

	/**
     * Constructs a User without specifying user_id.
     *
     * @param full_name the full name of the user
     * @param email the email address of the user
     * @param phone the phone number of the user
     * @param address the physical address of the user
     * @param membership_date the membership start date
     */
	public User(String full_name, String email, String phone, String address, Date membership_date) {

		this.full_name = full_name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.membership_date = membership_date;
	}

	/** @return the user's address */
    public String getAddress() {
        return address;
    }

    /** @return the user's email */
    public String getEmail() {
        return email;
    }

    /** @return the user's full name */
    public String getFull_name() {
        return full_name;
    }

    /** @return the user's membership date */
    public Date getMembership_date() {
        return membership_date;
    }

    /** @return the user's phone number */
    public String getPhone() {
        return phone;
    }

    /** @return the user's ID */
    public int getUser_id() {
        return user_id;
    }

    /** @param address sets the user's address */
    public void setAddress(String address) {
        this.address = address;
    }

    /** @param email sets the user's email */
    public void setEmail(String email) {
        this.email = email;
    }

    /** @param full_name sets the user's full name */
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    /** @param membership_date sets the user's membership date */
    public void setMembership_date(Date membership_date) {
        this.membership_date = membership_date;
    }

    /** @param phone sets the user's phone number */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** @param user_id sets the user's ID */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
