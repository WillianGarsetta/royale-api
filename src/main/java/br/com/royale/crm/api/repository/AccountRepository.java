package br.com.royale.crm.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.royale.crm.api.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

//	@Query("SELECT account FROM AccountEntity as account where account.emailAcesso = :login and account.password = :senha")
//	AccountEntity authentication(@Param("login")String login, @Param("senha") String senha);
	
	@Query("SELECT account FROM AccountEntity as account where account.emailAcesso = :login")
	Optional<AccountEntity> authentication(@Param("login")String login);
	
	@Query("SELECT count(*) from AccountEntity as account where account.emailAcesso = :email")
	Integer validaUsuario(@Param("email") String email);
	
}
