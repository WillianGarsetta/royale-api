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

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.royale.crm.api.entity.AccountEntity;
import br.com.royale.crm.api.repository.AccountRepository;
import br.com.royale.crm.api.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository repository;

	@Autowired
	public AccountServiceImpl(AccountRepository repository) {
		super();
		this.repository = repository;
	}

	private String uuid = UUID.randomUUID().toString();

	public ResponseEntity<String> listAccounts() {
		ResponseEntity<String> response = null;
		List<AccountEntity> accounts = new ArrayList<AccountEntity>();
		List<JsonObject> listReturn = new ArrayList<JsonObject>();
		accounts = repository.findAll();
		try {
			for (AccountEntity account : accounts) {
				JsonObject innerObject = new JsonObject();
				innerObject.addProperty("uuid", account.getUuid());
				innerObject.addProperty("firstname", account.getFirstname());
				innerObject.addProperty("lastName", account.getLastname());
				innerObject.addProperty("email", account.getEmail());
				innerObject.addProperty("blocked", account.getBlocked().toString());
				innerObject.addProperty("administrator", account.getAdministrator());
				innerObject.addProperty("password", account.getPassword());

				listReturn.add(innerObject);
			}

			response = new ResponseEntity<String>(new Gson().toJson(listReturn), HttpStatus.OK);

		} catch (Exception e) {
			response = new ResponseEntity(new Gson().toJson("[Error] - [royale-account-list] - " + e.getMessage()),
					HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@Override
	public ResponseEntity<String> createAccount(AccountEntity account) {
		ResponseEntity<String> response = null;
		try {
			if (account.getUuid().isBlank()) {
				account.setUuid(uuid);
			}
			repository.save(account);
			response = new ResponseEntity<String>(new Gson().toJson("Success"), HttpStatus.CREATED);

		} catch (Exception e) {
			response = new ResponseEntity(new Gson().toJson("[Error] - [royale-account-create] - " + e.getMessage()),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return response;
	}

	@Override
	public ResponseEntity<String> updateAccount(@Valid AccountEntity account) {
		ResponseEntity<String> response = null;
		if (Boolean.TRUE.equals(validateAccount(account.getUuid()))) {
			repository.save(account);
			response = new ResponseEntity<String>(new Gson().toJson("Success"), HttpStatus.OK);
		} else {
			response = new ResponseEntity(
					new Gson().toJson("[Error] - [royale-account-update] - Invalid Account - ID Not Found"),
					HttpStatus.NOT_FOUND);

		}

		return response;
	}

	@Override
	public ResponseEntity<String> deleteAccount(String uuid) {
		ResponseEntity<String> response = null;
		if (Boolean.TRUE.equals(validateAccount(uuid))) {
			repository.deleteById(uuid);
			response = new ResponseEntity<String>(new Gson().toJson("Success"), HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>(
					new Gson().toJson("[Error] - [royale-account-delete] - Invalid Account - ID Not Found"),
					HttpStatus.NOT_FOUND);
		}
		return response;

	}

	@Override
	public ResponseEntity<?> excludeAccount(String uuid) {
		ResponseEntity<String> response = null;
		try {
			if (Boolean.TRUE.equals(validateAccount(uuid))) {
				AccountEntity account;
				account = repository.getById(uuid);
				account.setBlocked(Boolean.TRUE);
				repository.save(account);
				response = new ResponseEntity<String>(new Gson().toJson("Success"), HttpStatus.OK);
			}
		} catch (Exception e) {
			response = new ResponseEntity<String>(new Gson().toJson("[Error] - [royale-account-exclude] - Invalid Account - ID Not Found"),
					HttpStatus.NOT_FOUND);

		}
		return response;
	}
	
	@Override
	public ResponseEntity<?> activateAccount(String uuid) {
		ResponseEntity<String> response = null;
		try {
			if (Boolean.TRUE.equals(validateAccount(uuid))) {
				AccountEntity account;
				account = repository.getById(uuid);
				account.setBlocked(Boolean.FALSE);
				repository.save(account);
				response = new ResponseEntity<String>(new Gson().toJson("Success"), HttpStatus.OK);
			}
		} catch (Exception e) {
			response = new ResponseEntity<String>(new Gson().toJson("[Error] - [royale-account-activate] - Invalid Account - ID Not Found"),
					HttpStatus.NOT_FOUND);

		}
		return response;
	}

	

	private Boolean validateAccount(String uuid) {
		if (repository.findById(uuid).isPresent()) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

}
