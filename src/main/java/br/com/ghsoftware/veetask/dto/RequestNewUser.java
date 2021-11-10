package br.com.ghsoftware.veetask.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.ghsoftware.veetask.model.User;

public class RequestNewUser {

	@NotBlank
	private String name;

	@NotBlank
	private String username;

	@NotBlank
	private String password;
	
	public User toUser() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		User user = new User();
		
		user.setName(name);
		user.setUsername(username);
		user.setPassword(encoder.encode(password));
		user.setEnabled(true);
		
		return user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
