package com.hussain.service;

import java.util.List;

import com.hussain.entity.Category;

public interface CategoryService {
	
	public Boolean saveCategory(Category category);
	
	public List<Category> getAllCategory();

}
