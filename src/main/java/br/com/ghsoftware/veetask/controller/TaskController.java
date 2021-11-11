package br.com.ghsoftware.veetask.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ghsoftware.veetask.dto.RequestNewTask;
import br.com.ghsoftware.veetask.dto.TaskDto;
import br.com.ghsoftware.veetask.model.Task;
import br.com.ghsoftware.veetask.model.TaskStatus;
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
	public String tasks(RequestNewTask request, Model model, Principal principal) {
		
		String username = principal.getName();
		
		User user = userRepository.findByUsername(username);
		
		model.addAttribute("user", user);
		model.addAttribute("status", "all");
			
		return "user/tasks";
	}
	
	@GetMapping("/filter/{status}")
	public String byStatus(@PathVariable String status, RequestNewTask request, Model model, Principal principal) {
		
		String username = principal.getName();
		
		User user = userRepository.findByUsername(username);
		
		List<Task> tasks = taskRepository.findByUsernameAndStatus(username, TaskStatus.valueOf(status.toUpperCase()));
		
		model.addAttribute("tasks", tasks);
		model.addAttribute("user", user);
		model.addAttribute("status", status);
			
		return "user/tasks";
	}

}
