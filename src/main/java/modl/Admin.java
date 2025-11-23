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

	public Admin(int admin_id, String username, String password, String email) {
		this.admin_id = admin_id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
