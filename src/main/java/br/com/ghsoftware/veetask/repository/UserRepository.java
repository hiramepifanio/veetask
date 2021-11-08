package br.com.ghsoftware.veetask.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ghsoftware.veetask.model.User;

public interface UserRepository extends CrudRepository<User, String> {

	User findByUsername(String string);

}
