package br.com.ghsoftware.veetask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ghsoftware.veetask.dto.RequestNewTask;
import br.com.ghsoftware.veetask.dto.RequestNewUser;
import br.com.ghsoftware.veetask.model.Task;
import br.com.ghsoftware.veetask.model.User;
import br.com.ghsoftware.veetask.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/register")
	public String newTask(RequestNewUser request, BindingResult result) {
		
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		User user = userRepository.findByUsername(username);
//		
//		Task task = request.toTask();
//		task.setOwner(user);
//		
//		taskRepository.save(task);
		
		return "redirect:/user/tasks";
	}
}
