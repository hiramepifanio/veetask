package br.com.ghsoftware.veetask.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.ghsoftware.veetask.model.Task;
import br.com.ghsoftware.veetask.model.TaskStatus;

public class TaskDto {
	
	private Integer id;
	private String description;
	private TaskStatus status;
	
	public TaskDto(Task task) {
		this.id = task.getId();
		this.description = task.getDescription();
		this.status = task.getStatus();
	}

	public static List<TaskDto> convertList(List<Task> tasks) {
		List<TaskDto> tasksDto = new ArrayList<>();
		
		for (Task task : tasks) {
			TaskDto taskDto = new TaskDto(task);
			tasksDto.add(taskDto);
		}
		
		return tasksDto;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
