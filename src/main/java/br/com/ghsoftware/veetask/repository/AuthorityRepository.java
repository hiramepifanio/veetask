package br.com.ghsoftware.veetask.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ghsoftware.veetask.model.Authority;
import br.com.ghsoftware.veetask.model.AuthorityId;

public interface AuthorityRepository extends CrudRepository<Authority, AuthorityId> {

}
