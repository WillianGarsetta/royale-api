package br.com.royale.crm.api.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.royale.crm.api.dto.Perfil;

@Entity
@Table(name = "accounts", schema = "portal")
public class AccountEntity implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	@NotEmpty
	@NotNull
	@Column(name = "email", nullable = false)
	private String email;

	@NotEmpty
	@NotNull
	@Column(name = "acess_mail", nullable = false)
	private String emailAcesso;

	@Column(name = "role")
	private String role;

	@NotEmpty
	@NotNull
	@Column(name = "password", nullable = false)
	private String password;

	@Transient
	@Column(name = "timestamp", nullable = true)
	private Date timestamp;

	public AccountEntity() {
	};
	
	





	

	public AccountEntity(String uuid, @NotEmpty @NotNull String firstname, String lastname, Boolean blocked,
			@NotEmpty @NotNull String email, @NotEmpty @NotNull String emailAcesso, String role,
			@NotEmpty @NotNull String password, Date timestamp) {
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









	public String getRole() {
		return role;
	}









	public void setRole(String role) {
		this.role = role;
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

	public String getEmailAcesso() {
		return emailAcesso;
	}

	public void setEmailAcesso(String emailAcesso) {
		this.emailAcesso = emailAcesso;
	}









	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}









	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}









	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}









	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}









	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}









	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
