package br.com.royale.crm.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "accounts", schema = "portal")
public class AccountEntity{

	@Id
	@Column(name = "uuid", nullable = false, unique = true)
	private String uuid;

	@NotEmpty
	@NotNull
	@Column(name = "firstname", nullable = false)
	private String firstname;

	@Column(name = "lastname", nullable = true)
	private String lastname;

	@Column(name = "blocked", nullable = false)
	private Boolean blocked;

	@Column(name = "administrator", nullable = false)
	private Boolean administrator;
	
	@NotEmpty
	@NotNull
	@Column(name = "email", nullable = false)
	private String email;
	
	@NotEmpty
	@NotNull
	@Column(name = "password", nullable = false)
	private String password;

	@Transient
	@Column(name = "timestamp", nullable = true)
	private Date timestamp;

	public AccountEntity() {
	};

	public AccountEntity(String uuid, String firstname, String lastname, Boolean blocked, Boolean administrator,
			String email, String password, Date timestamp) {
		super();
		this.uuid = uuid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.blocked = blocked;
		this.administrator = administrator;
		this.email = email;
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

	public Boolean getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Boolean administrator) {
		this.administrator = administrator;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
