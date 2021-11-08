package br.com.ghsoftware.veetask.dto;

import javax.validation.constraints.NotBlank;

import br.com.ghsoftware.veetask.model.Task;
import br.com.ghsoftware.veetask.model.TaskStatus;

public class RequestNewTask {

	@NotBlank
	private String description;
	
	public Task toTask() {
		Task task = new Task();
		
		task.setDescription(description);
		task.setStatus(TaskStatus.TODO);
		
		return task;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
