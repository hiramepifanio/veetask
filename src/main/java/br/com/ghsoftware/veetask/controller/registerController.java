package br.com.ghsoftware.veetask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ghsoftware.veetask.dto.RequestNewTask;
import br.com.ghsoftware.veetask.dto.RequestNewUser;
import br.com.ghsoftware.veetask.model.Authority;
import br.com.ghsoftware.veetask.model.Task;
import br.com.ghsoftware.veetask.model.User;
import br.com.ghsoftware.veetask.repository.AuthorityRepository;
import br.com.ghsoftware.veetask.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class registerController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@GetMapping
	public String register(RequestNewUser request) {
		return "register";
	}
	
	@PostMapping
	public String newUser(RequestNewUser request, BindingResult result) {
		
		User user = request.toUser();
		userRepository.save(user);
		
		Authority authority = new Authority();
		authority.setUsername(user.getUsername());
		authority.setAuthority("ROLE_USER");
		authorityRepository.save(authority);
		
		return "redirect:/user/tasks";
	}
}
