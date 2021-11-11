package br.com.ghsoftware.veetask.api.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.ghsoftware.veetask.model.Task;
import br.com.ghsoftware.veetask.model.TaskStatus;
import br.com.ghsoftware.veetask.model.User;
import br.com.ghsoftware.veetask.repository.TaskRepository;
import br.com.ghsoftware.veetask.repository.UserRepository;

public class TaskForm {
	
	@NotNull @NotEmpty
	private String description;
	
	@NotNull @NotEmpty
	private String ownerUsername;
	
	public Task convert(UserRepository userRepository) {
		
		User owner = userRepository.findByUsername(ownerUsername);
		
		return new Task(this.description, owner);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwnerUsername() {
		return ownerUsername;
	}

	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}
}
