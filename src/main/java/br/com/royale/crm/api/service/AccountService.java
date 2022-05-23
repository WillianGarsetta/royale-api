package br.com.royale.crm.api.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.royale.crm.api.dto.AccountDTO;
import br.com.royale.crm.api.dto.FormLogin;
import br.com.royale.crm.api.entity.AccountEntity;

@Service
public interface AccountService {
	ResponseEntity<String> listAccounts();
	ResponseEntity createAccount(AccountDTO account);
	ResponseEntity<String> updateAccount(@Valid AccountDTO account);
	ResponseEntity<String> deleteAccount(String uuid);
	ResponseEntity<?> excludeAccount(String uuid);
	ResponseEntity<?> activateAccount(String uuid);
//	ResponseEntity authenticationAccount(FormLogin login);
	ResponseEntity validaUsuario(String email);
	String gerarToken(Authentication authentication);
}
