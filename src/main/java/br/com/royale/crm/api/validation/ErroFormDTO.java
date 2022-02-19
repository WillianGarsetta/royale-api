package br.com.royale.crm.api.validation;

public class ErroFormDTO {
	
	private String field;
	private String error;
	
	
	
	public String getField() {
		return field;
	}



	public void setField(String field) {
		this.field = field;
	}



	public String getError() {
		return error;
	}



	public void setError(String error) {
		this.error = error;
	}



	public ErroFormDTO(String field, String error) {
		super();
		this.field = field;
		this.error = error;
	}
}
