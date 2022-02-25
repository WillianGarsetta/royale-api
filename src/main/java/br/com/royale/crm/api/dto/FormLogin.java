package br.com.royale.crm.api.dto;

public class FormLogin {
	private String user;
	private String password;

	public FormLogin() {
		// TODO Auto-generated constructor stub
	}
	
	public FormLogin(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
