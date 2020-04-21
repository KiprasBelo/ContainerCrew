

public class Account {
	
	private String username;
	private String password;
	private String name;
	private String email;
	private String address;
	private String phoneNumber;
	private char type;
	private boolean loggedIn;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setType(char t) {
		type = t;
	}
	public char getType() {
		return type;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setLoginStatus(boolean login) {
		loggedIn = login;
	}
	public boolean getLoginStatus() {
		return loggedIn;
	}
	
	public ResponceObject logIn(String user, String pass) {
		
		ResponceObject responce = new ResponceObject("Logged in");
		
		if(user.contentEquals(username)) {
			if(pass.contentEquals(password)) {
				setLoginStatus(true);
			}
			else {
				responce = new ResponceObject("Incorrect username or password");
			}
		}
		else {
			responce = new ResponceObject("Incorrect username or password");
		}
		return responce;
	}
	
	public ResponceObject logOut() {
		return new ResponceObject("Successfully logged out");
	}
	

}
