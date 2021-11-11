package br.com.ghsoftware.veetask.api;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ghsoftware.veetask.api.form.TaskForm;
import br.com.ghsoftware.veetask.dto.RequestCheckTask;
import br.com.ghsoftware.veetask.dto.TaskDto;
import br.com.ghsoftware.veetask.model.Task;
import br.com.ghsoftware.veetask.model.TaskStatus;
import br.com.ghsoftware.veetask.repository.TaskRepository;
import br.com.ghsoftware.veetask.repository.UserRepository;

@RestController
@RequestMapping("/api/tasks")
public class TaskRest {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<TaskDto> getTasks(Principal principal) {
		
		String username = principal.getName();
		List<Task> tasks = taskRepository.findByUsername(username);
		
		return TaskDto.convertList(tasks);
	}
	
	@GetMapping("/{id}")
	public TaskDto getTask(@PathVariable Integer id) {
		
		Task task = taskRepository.findById(id).get();
		
		return new TaskDto(task);
	}
	
	@PostMapping("/register")
	@Transactional
	public ResponseEntity<TaskDto> register(@RequestBody TaskForm form, UriComponentsBuilder uriBuilder) {
		System.out.println(form.getDescription());
		System.out.println(form.getOwnerUsername());
		Task task = form.convert(userRepository);
		taskRepository.save(task);
		
		URI uri = uriBuilder.path("/api/tasks/{id}").buildAndExpand(task.getId()).toUri();
		return ResponseEntity.created(uri).body(new TaskDto(task));
	}
	
	@PutMapping("/check")
	@Transactional
	public ResponseEntity<TaskDto> checkTask(@RequestBody RequestCheckTask request, UriComponentsBuilder uriBuilder) {
		
		Integer id = request.getId();
		
		Task task = taskRepository.findById(id).get();
		task.setStatus(TaskStatus.DONNE);
		taskRepository.save(task);
	
		
		URI uri = uriBuilder.path("api/tasks/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).body(new TaskDto(task));
	}
	
	@PutMapping("/uncheck")
	@Transactional
	public ResponseEntity<TaskDto> uncheckTask(@RequestBody RequestCheckTask request, UriComponentsBuilder uriBuilder) {
		
		Integer id = request.getId();
		
		Task task = taskRepository.findById(id).get();
		task.setStatus(TaskStatus.TODO);
		taskRepository.save(task);
	
		
		URI uri = uriBuilder.path("api/tasks/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).body(new TaskDto(task));
	}
	
	@DeleteMapping("/remove/{id}")
	@Transactional
	public ResponseEntity<?> removeTask(@PathVariable Integer id) {
		taskRepository.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
}
