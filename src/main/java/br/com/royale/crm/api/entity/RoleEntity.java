package br.com.royale.crm.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.royale.crm.api.dto.Perfil;

@Entity
@Table(name = "role", schema = "portal")
public class RoleEntity {

	@Id
	@Column(name = "uuid", nullable = false, unique = true)
	private String uuid;

	@NotEmpty
	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	public RoleEntity() {
	}
	
    

	public RoleEntity(String uuid, @NotEmpty @NotNull String name) {
		super();
		this.uuid = uuid;
		this.name = name;
	}



	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};
	
	





	

}