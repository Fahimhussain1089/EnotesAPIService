package com.hussain.repo;

import java.util.List;

//public class CategoryRepository {
//
//}


import org.springframework.data.jpa.repository.JpaRepository;

import com.hussain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	List<Category> findByIsActiveTrueAndIsDeletedFalse();

}
