package com.hussain.repo;

//public class CategoryRepository {
//
//}


import org.springframework.data.jpa.repository.JpaRepository;

import com.hussain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
