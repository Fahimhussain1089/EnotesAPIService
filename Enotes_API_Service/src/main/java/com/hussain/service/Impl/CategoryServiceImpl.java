package com.hussain.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.hussain.entity.Category;
import com.hussain.repo.CategoryRepository;
import com.hussain.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService  {
	
	
	@Autowired
	private CategoryRepository categoryRepo;


//	@Override
//	public Boolean saveCategory(Category category) {
//		
//		return null;
//	}
//
//	@Override
//	public List<Category> getAllCategory() {
//		
//		return null;
//	}
	
	
	@Override
	public Boolean saveCategory(Category category) {
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
		Category saveCategory = categoryRepo.save(category);
		if (ObjectUtils.isEmpty(saveCategory)) {
			return false;
		}
		return true;
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> categories = categoryRepo.findAll();
		return categories;
	}
	
	

}
