package br.com.royale.crm.api.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.royale.crm.api.entity.AccountEntity;

@Service
public interface AccountService {
	ResponseEntity<String> listAccounts();
	ResponseEntity createAccount(AccountEntity account);
	ResponseEntity<String> updateAccount(@Valid AccountEntity account);
	ResponseEntity<String> deleteAccount(String uuid);
}
