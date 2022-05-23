package br.com.royale.crm.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

	ResponseEntity<?> listAccounts();

}
