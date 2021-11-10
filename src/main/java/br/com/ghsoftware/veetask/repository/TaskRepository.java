package br.com.ghsoftware.veetask.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.ghsoftware.veetask.model.Task;
import br.com.ghsoftware.veetask.model.TaskStatus;

public interface TaskRepository extends CrudRepository<Task, Integer> {

	@Query("select t from Task t join t.owner u where u.username = :username")
	List<Task> findByUsername(@Param("username") String username);

	@Query("select t from Task t join t.owner u where u.username = :username and t.status = :status")
	List<Task> findByUsernameAndStatus(@Param("username") String username, @Param("status") TaskStatus taskStatus);

}
