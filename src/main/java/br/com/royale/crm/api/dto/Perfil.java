package br.com.royale.crm.api.dto;

public class Perfil {
	private String uuid;
	private String name;

	public Perfil() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Perfil(String uuid, String name) {
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
	}



	
	
	
}
