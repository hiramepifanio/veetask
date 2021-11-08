package br.com.ghsoftware.veetask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ghsoftware.veetask.dto.RequestNewTask;
import br.com.ghsoftware.veetask.model.Task;
import br.com.ghsoftware.veetask.model.User;
import br.com.ghsoftware.veetask.repository.TaskRepository;
import br.com.ghsoftware.veetask.repository.UserRepository;

@Controller
@RequestMapping("/user/tasks")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public String tasks(Model model) {
		
		List<Task> tasks = (List<Task>) taskRepository.findAll();
		model.addAttribute("tasks", tasks);
			
		return "user/tasks";
	}
	
	@GetMapping("/new")
	public String newTaskForm(RequestNewTask request) {	
		return "user/new";
	}
	
	@PostMapping("/new")
	public String newTask(RequestNewTask request, BindingResult result) {
		
		User user = userRepository.findByUsername("hiramepifanio");
		
		Task task = request.toTask();
		task.setOwner(user);
		
		taskRepository.save(task);
		
		return "redirect:/user/tasks";
	}
}
