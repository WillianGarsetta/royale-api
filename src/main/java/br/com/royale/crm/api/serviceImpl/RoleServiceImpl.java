package br.com.royale.crm.api.serviceImpl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.internal.JsonContext;

import br.com.royale.crm.api.dto.AccountDTO;
import br.com.royale.crm.api.dto.FormLogin;
import br.com.royale.crm.api.entity.AccountEntity;
import br.com.royale.crm.api.entity.RoleEntity;
import br.com.royale.crm.api.repository.AccountRepository;
import br.com.royale.crm.api.repository.RoleRepository;
import br.com.royale.crm.api.service.AccountService;
import br.com.royale.crm.api.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository repository;

	@Autowired
	public RoleServiceImpl(RoleRepository repository) {
		super();
		this.repository = repository;
	}

	public ResponseEntity<String> listAccounts() {
		ResponseEntity<String> response = null;
		List<RoleEntity> roles = new ArrayList<RoleEntity>();
		List<JsonObject> listReturn = new ArrayList<JsonObject>();
		roles = repository.findAll();
		try {
			for (RoleEntity role : roles) {
				JsonObject innerObject = new JsonObject();
				innerObject.addProperty("uuid", role.getUuid());
				innerObject.addProperty("name", role.getName());
				

				listReturn.add(innerObject);
			}

			response = new ResponseEntity<String>(new Gson().toJson(listReturn), HttpStatus.OK);

		} catch (Exception e) {
			response = new ResponseEntity(new Gson().toJson("[Error] - [royale-role-list] - " + e.getMessage()),
					HttpStatus.NO_CONTENT);
		}
		return response;
	}

}
