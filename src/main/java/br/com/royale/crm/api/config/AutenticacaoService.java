package br.com.royale.crm.api.config;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.royale.crm.api.entity.AccountEntity;
import br.com.royale.crm.api.repository.AccountRepository;

@Service
public class AutenticacaoService implements UserDetailsService{

	@Autowired
	private AccountRepository repository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AccountEntity> account= repository.authentication(username);
		if(account.isPresent()) {
			return  account.get();
		}
		throw new UsernameNotFoundException("Dados Invalidos");
	}

}
