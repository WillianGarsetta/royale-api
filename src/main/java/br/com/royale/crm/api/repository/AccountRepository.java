package br.com.royale.crm.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.royale.crm.api.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

}
