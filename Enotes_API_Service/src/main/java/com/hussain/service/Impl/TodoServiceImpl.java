package com.hussain.service.Impl;

import java.util.Date;

//public class TodoServiceImpl {
//
//}


import java.util.List;
import java.util.function.ToDoubleBiFunction;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.hussain.dto.TodoDto;
import com.hussain.dto.TodoDto.StatusDto;
import com.hussain.entity.Todo;
import com.hussain.enums.TodoStatus;
//import com.hussain.enums.TodoStatus;
import com.hussain.exception.ResourceNotFoundException;
import com.hussain.repo.TodoRepository;
import com.hussain.service.TodoService;
import com.hussain.util.Validation;


import java.util.Date;  // Fixes the Date type error


@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private Validation validation;

	@Override
	public Boolean saveTodo(TodoDto todoDto) throws Exception {
		// validate todo status
		validation.todoValidation(todoDto);

		Todo todo = mapper.map(todoDto, Todo.class);
		
		todo.setStatusId(todoDto.getStatus().getId());
		
		Todo saveTodo = todoRepo.save(todo);
		
		if (!ObjectUtils.isEmpty(saveTodo)) {
			return true;
		}
		return false;
	}
//	@Override
//	public Boolean saveTodo(TodoDto todoDto) throws Exception {
//	    // validate todo status
//	    validation.todoValidation(todoDto);
//
//	    // Manual conversion from DTO to Entity
//	    Todo todo = new Todo();
//	    todo.setTitle(todoDto.getTitle());
//	    
//	    if (todoDto.getStatus() != null) {
//	        todo.setStatusId(todoDto.getStatus().getId());
//	    }
//	    
//	    // Set audit fields if needed
//	    todo.setCreatedBy(getCurrentUserId()); // Implement this
//	    todo.setCreatedOn(new Date());
//
//	    Todo savedTodo = todoRepo.save(todo);
//	    return savedTodo != null;
//	}
	
	

	@Override
	public TodoDto getTodoById(Integer id) throws Exception {
		Todo todo = todoRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo Not Found ! id invalid"));
		TodoDto todoDto = mapper.map(todo, TodoDto.class);
		setStatus(todoDto,todo);
		return todoDto;
	}

//	private void setStatus(TodoDto todoDto, Todo todo) {
//		
//		for(TodoStatus st:TodoStatus.values())
//		{
//			if(st.getId().equals(todo.getStatusId()))
//			{
//				StatusDto statusDto=StatusDto.builder()
//						.id(st.getId())
//						.name(st.getName())
//						.build();
//				todoDto.setStatus(statusDto);
//			}
//		}
//		
//	}
	private void setStatus(TodoDto todoDto, Todo todo) {
	    if (todo.getStatusId() == null) return;
	    
	    for (TodoStatus status : TodoStatus.values()) {
	        if (status.getId().equals(todo.getStatusId())) {
	            StatusDto statusDto = new StatusDto();
	            statusDto.setId(status.getId());
	            statusDto.setName(status.getName());
	            todoDto.setStatus(statusDto);
	            break; // Exit loop after finding match
	        }
	    }
	}

	@Override
	public List<TodoDto> getTodoByUser() {
		Integer userId = 2;
		List<Todo> todos = todoRepo.findByCreatedBy(userId);
		return todos.stream().map(td -> mapper.map(td, TodoDto.class)).toList();
	}
	
	//***************************here is the methode********************************
	private Integer getCurrentUserId() {
	    // Temporary implementation - replace with your actual user ID retrieval
	    return 1; // Hardcoded for testing
	}
	

}
