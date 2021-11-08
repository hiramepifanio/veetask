package br.com.ghsoftware.veetask.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ghsoftware.veetask.model.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {

}
