package br.com.royale.crm.api.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountDTO {

	private String uuid;

	private String firstname;

	
	private String lastname;

	private Boolean blocked;

	private String email;

	
	private String emailAcesso;

	
	private Perfil role;

	private String password;


	private Date timestamp;

	public AccountDTO() {
		// TODO Auto-generated constructor stub
	}

	public AccountDTO(String uuid, String firstname, String lastname, Boolean blocked, String email, String emailAcesso,
			Perfil role, String password, Date timestamp) {
		super();
		this.uuid = uuid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.blocked = blocked;
		this.email = email;
		this.emailAcesso = emailAcesso;
		this.role = role;
		this.password = password;
		this.timestamp = timestamp;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailAcesso() {
		return emailAcesso;
	}

	public void setEmailAcesso(String emailAcesso) {
		this.emailAcesso = emailAcesso;
	}

	public Perfil getRole() {
		return role;
	}

	public void setRole(Perfil role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
