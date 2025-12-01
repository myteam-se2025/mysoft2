package modl;

/*
 * @author: your name
 * @param Admin class to represent an admin in the system
 * @param admin_id int unique identifier for the admin
 * @param username String username of the admin
 * @param password String password of the admin
 * @param email String email of the admin
 * @methods: getters and setters for each field
 * @methods: constructor to initialize all fields
 */
public class Admin {

	int admin_id;
	String username;
	String password;
	String email;
	
	 /**
     * Constructs an Admin with all fields specified.
     *
     * @param admin_id unique ID of the admin
     * @param username username of the admin
     * @param password password of the admin
     * @param email email of the admin
     */
	public Admin(int admin_id, String username, String password, String email) {
		this.admin_id = admin_id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	 /**
     * Constructs an Admin without an ID.
     *
     * @param username username of the admin
     * @param password password of the admin
     * @param email email of the admin
     */
	public Admin( String username, String password, String email) {
		//this.admin_id = admin_id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	/**
     * Default constructor initializing fields with default values.
     */
	 public Admin() {
	      
	        this.admin_id = 0;
	        this.username = "null";
	        this.password = "null";
	        this.email = "null";
	    }

	 /** @return the admin ID */
	public int getAdmin_id() {
		return admin_id;
	}

	 /** @return the username */
    public String getUsername() {
        return username;
    }

    /** @return the password */
    public String getPassword() {
        return password;
    }

    /** @return the email */
    public String getEmail() {
        return email;
    }

    /** @param admin_id sets the admin ID */
    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    /** @param username sets the username */
    public void setUsername(String username) {
        this.username = username;
    }

    /** @param password sets the password */
    public void setPassword(String password) {
        this.password = password;
    }

    /** @param email sets the email */
    public void setEmail(String email) {
        this.email = email;
    }
}
