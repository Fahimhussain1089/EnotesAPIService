package com.hussain.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hussain.dto.TodoDto;
import com.hussain.endpoint.TodoEndpoint;
import com.hussain.service.TodoService;
import com.hussain.util.CommonUtil;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController implements TodoEndpoint  {

	@Autowired
	private TodoService todoService;

//	@PostMapping("/")
	@Override
	public ResponseEntity<?> saveTodo(@RequestBody TodoDto todo) throws Exception {
		Boolean saveTodo = todoService.saveTodo(todo);
		if (saveTodo) {
			return CommonUtil.createBuildResponseMessage("Todo Saved Success", HttpStatus.CREATED);
		} else {
			return CommonUtil.createErrorResponseMessage("Todo not save", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@GetMapping("/{id}")
//	@Override
//	public ResponseEntity<?> saveTodo(@PathVariable Integer id) throws Exception {
//		TodoDto todo = todoService.getTodoById(id);
//		return CommonUtil.createBuildResponse(todo, HttpStatus.OK);
//	}

//	@GetMapping("/list")
	@Override
	public ResponseEntity<?> getAllTodoByUser() throws Exception {
		List<TodoDto> todoList = todoService.getTodoByUser();
		if (CollectionUtils.isEmpty(todoList)) {
			return ResponseEntity.noContent().build();
		}
		return CommonUtil.createBuildResponse(todoList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getTodoById(Integer id) throws Exception {
		TodoDto todo = todoService.getTodoById(id);
		return CommonUtil.createBuildResponse(todo, HttpStatus.OK);
	}

}