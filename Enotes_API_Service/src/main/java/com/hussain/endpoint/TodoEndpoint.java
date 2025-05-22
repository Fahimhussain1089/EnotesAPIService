package com.hussain.endpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hussain.dto.TodoDto;

@RequestMapping("/api/v1/todo")
public interface TodoEndpoint {
	
	@PostMapping("/")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> saveTodo(@RequestBody TodoDto todo) throws Exception ;
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getTodoById(@PathVariable Integer id) throws Exception;
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getAllTodoByUser() throws Exception;
	
	
}