package containersoftware;

/**
 * This class creates an account with the most basic information and no real functionality
 *
 */
public class Account {
	
	//Instance Variables
	
	private String username;
	private String password;
	private String name;
	private String email;
	private String address;
	private String phoneNumber;
	private boolean loggedIn;
	
	/**
	 * Gets the account phone number
	 * @return a string with the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * Manually sets the account phone number
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Gets the account username
	 * @return a string with the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Manually sets the account username
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Gets the account password
	 * @return a string with the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Manually sets the account password
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the name on the account
	 * @return a string with the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Manually sets the name on the account
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the account email
	 * @return a string with the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Manually sets the account email
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the account address
	 * @return a string with the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Manually sets the account address
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Manually sets the account log in status
	 * @param login a boolean with the new account status
	 */
	public void setLoginStatus(boolean login) {
		loggedIn = login;
	}
	
	/**
	 * Gets the current login status of an account
	 * @return a boolean sayin whether or not an account is logged in
	 */
	public boolean getLoginStatus() {
		return loggedIn;
	}
	
}
