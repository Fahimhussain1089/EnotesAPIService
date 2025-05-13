package com.hussain.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.hussain.dto.CategoryDto;
import com.hussain.dto.CategoryReponse;
import com.hussain.entity.Category;
import com.hussain.repo.CategoryRepository;
import com.hussain.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService  {
	
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	
	@Autowired
	private ModelMapper mapper;

	
	
	@Override
	public Boolean saveCategory(CategoryDto categoryDto) {
		//Category category =  new Category();
//		category.setName(categoryDto.getName());
//		category.setDescription(categoryDto.getDescription());
//		category.setIsActive(categoryDto.getIsActive());
		
		Category category = mapper.map(categoryDto, Category.class); //categoryDto == category ka parametter ki speeling same ho hona chahiye 


		
//		category.setIsDeleted(false);
//		category.setCreatedBy(1);
//		category.setCreatedOn(new Date());
		
		if (ObjectUtils.isEmpty(category.getId())) {
			category.setIsDeleted(false);
			category.setCreatedBy(1);
			category.setCreatedOn(new Date());
		} else {
			updateCategory(category);
		}
		
		Category saveCategory = categoryRepo.save(category);
		if (ObjectUtils.isEmpty(saveCategory)) {
			return false;
		}
		return true;
	}

	

	@Override
	public List<CategoryDto> getAllCategory() {
		
		List<Category> categories = categoryRepo.findByIsDeletedFalse();
		List<CategoryDto> categoryDtoList = categories.stream().map(cat -> mapper.map(cat, CategoryDto.class)).toList();
		
		return categoryDtoList;
	}
	
	

	@Override
	public List<CategoryReponse> getActiveCategory() {
		//List<Category> categories = categoryRepo.findAll(); //See here if any error show
		List<Category> categories = categoryRepo.findByIsActiveTrueAndIsDeletedFalse();

		
		List<CategoryReponse> categoryList = categories.stream().map(cat -> mapper.map(cat, CategoryReponse.class))
				.toList();
		return categoryList;
	
	}
	
	

	@Override
	public CategoryDto getCategoryById(Integer id) {

		Optional<Category> findByCatgeory = categoryRepo.findByIdAndIsDeletedFalse(id);
		

		if (findByCatgeory.isPresent()) {
			Category category = findByCatgeory.get();
			return mapper.map(category, CategoryDto.class);
		}
		return null;
	}
	
	
	

	@Override
	public Boolean deleteCategory(Integer id) {
		Optional<Category> findByCatgeory = categoryRepo.findById(id);

		if (findByCatgeory.isPresent()) {
			Category category = findByCatgeory.get();
			category.setIsDeleted(true);
			categoryRepo.save(category);
			return true;
		}
		return false;
	}
	
	//***************created methode *********************************
	private void updateCategory(Category category) {
		Optional<Category> findById = categoryRepo.findById(category.getId());
		if (findById.isPresent()) {
			Category existCategory = findById.get();
			
			category.setCreatedBy(existCategory.getCreatedBy());
			category.setCreatedOn(existCategory.getCreatedOn());
			category.setIsDeleted(existCategory.getIsDeleted());
			
			category.setUpdatedBy(1);
			category.setUpdatedOn(new Date());
		}
		
		
	}
	
	

}
