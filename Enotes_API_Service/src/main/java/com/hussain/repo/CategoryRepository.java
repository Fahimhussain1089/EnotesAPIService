package com.hussain.repo;

import java.util.List;
import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;

import com.hussain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	List<Category> findByIsActiveTrueAndIsDeletedFalse();
	
	Optional<Category> findByIdAndIsDeletedFalse(Integer id);
	

	List<Category> findByIsDeletedFalse();
	
	Boolean existsByName(String name);

}
