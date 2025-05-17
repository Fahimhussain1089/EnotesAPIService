package com.hussain.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hussain.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

	List<Todo> findByCreatedBy(Integer userId);

}
