package com.hussain.service;

//public class TodoService {
//
//}

import java.util.List;

import com.hussain.dto.TodoDto;

public interface TodoService {

	public Boolean saveTodo(TodoDto todo) throws Exception;

	public TodoDto getTodoById(Integer id) throws Exception;

	public List<TodoDto> getTodoByUser();

}
