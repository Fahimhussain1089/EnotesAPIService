package com.hussain.service;

import java.util.List;

import com.hussain.dto.CategoryDto;
import com.hussain.dto.CategoryReponse;
import com.hussain.entity.Category;

public interface CategoryService {
	
	//public Boolean saveCategory(Category category);
	
	//public List<Category> getAllCategory();
	
	//**************************************
	public Boolean saveCategory(CategoryDto categoryDto);
	
	public List<CategoryDto> getAllCategory();

	public List<CategoryReponse> getActiveCategory();

	public CategoryDto getCategoryById(Integer id) throws Exception;

	public Boolean deleteCategory(Integer id);

	
}


