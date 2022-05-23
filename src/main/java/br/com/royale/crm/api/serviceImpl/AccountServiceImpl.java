package br.com.royale.crm.api.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import br.com.royale.crm.api.repository.AccountRepository;
import br.com.royale.crm.api.service.AccountService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository repository;
	
	@Value("${api.jwt.expiration}") 
	private String expiration;
	
	@Value("${api.jwt.secret}") 
	private String secret;

	@Autowired
	public AccountServiceImpl(AccountRepository repository) {
		super();
		this.repository = repository;
	}

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
				innerObject.addProperty("emailAcesso", account.getEmailAcesso());
				innerObject.addProperty("blocked", account.getBlocked().toString());
				innerObject.addProperty("perfil", account.getRole());
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
	public ResponseEntity<String> createAccount(AccountDTO account) {
		ObjectMapper mapper = new ObjectMapper();
		
		String uuid = UUID.randomUUID().toString();
		ResponseEntity<String> response = null;
		try {
			if (account.getUuid().isBlank()) {
				account.setUuid(uuid);
			}
			
			
	
			String accountJson = mapper.writeValueAsString(account);
			JsonNode json = mapper.readTree(accountJson);
			
			json = json.get("role");
			
			String perfil = json.findValue("name").asText();
			
			System.out.println("Valor do perfil = " + perfil);
			
			AccountEntity entity = new AccountEntity(account.getUuid(), account.getFirstname(), account.getLastname(), account.getBlocked(), account.getEmail(), account.getEmailAcesso(), perfil,account.getPassword(),null);
			
			repository.save(entity);
			response = new ResponseEntity<String>(new Gson().toJson("Success"), HttpStatus.CREATED);

		} catch (Exception e) {
			response = new ResponseEntity(new Gson().toJson("[Error] - [royale-account-create] - " + e.getMessage()),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return response;
	}

	@Override
	public ResponseEntity<String> updateAccount(@Valid AccountDTO accountDto) {
		ResponseEntity<String> response = null;
		if (Boolean.TRUE.equals(validateAccount(accountDto.getUuid()))) {
			AccountEntity account = new AccountEntity(accountDto.getUuid(), accountDto.getFirstname(), accountDto.getLastname(),
					accountDto.getBlocked(),accountDto.getEmail(), accountDto.getEmailAcesso(), accountDto.getRole().getName(),accountDto.getPassword(), null);
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
			response = new ResponseEntity<String>(
					new Gson().toJson("[Error] - [royale-account-exclude] - Invalid Account - ID Not Found"),
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
			response = new ResponseEntity<String>(
					new Gson().toJson("[Error] - [royale-account-activate] - Invalid Account - ID Not Found"),
					HttpStatus.NOT_FOUND);

		}
		return response;
	}

//	@Override
//	public ResponseEntity authenticationAccount(FormLogin login) {
//		ResponseEntity<String> response = null;
//		String user = login.getUser();
//		String pass = login.getPassword();
//		AccountEntity account = repository.authentication(user,pass);
//		try {
//
//			if (Boolean.TRUE.equals(account.getBlocked())) {
//				response = new ResponseEntity<String>(new Gson().toJson("User Blocked, Contact ADMIN"),
//						HttpStatus.FORBIDDEN);
//			} else {
//				response = new ResponseEntity<String>(new Gson().toJson("Success"), HttpStatus.OK);
//			}
//		} catch (Exception e) {
//			response = new ResponseEntity<String>(
//					new Gson().toJson("[Error] - [royale-account-authentication] - ERROR" + e.getMessage()),
//					HttpStatus.NOT_FOUND);
//		}
//		return response;
//	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<Boolean> validaUsuario(String email) {
		ResponseEntity<Boolean> response = null;
		Integer valida = repository.validaUsuario(email);
		try {
			if (valida>0) {
				response = new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
			}else{
				response = new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.OK);
			}
		} catch (Exception e) {
			response = new ResponseEntity(
					new Gson().toJson("[Error] - [royale-account-validate-User] - ERROR" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
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

	@Override
	public String gerarToken(Authentication authentication) {
		
		
		AccountEntity usuario = (AccountEntity) authentication.getPrincipal();
		Date hoje = new Date();
		
		Date dtExpiration = new Date(hoje.getTime() +Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API CRM ROYALE")
				.setSubject(usuario.getUuid().toString())
				.setIssuedAt(hoje)
				.setExpiration(dtExpiration)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
		
	}

	

}
