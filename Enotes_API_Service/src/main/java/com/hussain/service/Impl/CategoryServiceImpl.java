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
import com.hussain.exception.ExistDataException;
import com.hussain.exception.ResourceNotFoundException;
import com.hussain.repo.CategoryRepository;
import com.hussain.service.CategoryService;
//import com.hussain.service.CategoryServiceTest;
import com.hussain.util.Validation;


@Service
public class CategoryServiceImpl implements CategoryService  {
	
	@Autowired
    private final Validation validation;
	
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	
	@Autowired
	private ModelMapper mapper;


    CategoryServiceImpl(Validation validation) {
        this.validation = validation;
    }
    //***********for the junit *****************************************
    @Autowired // Optional in Spring 4.3+ for single constructor
    public CategoryServiceImpl(Validation validation, 
                             CategoryRepository categoryRepo,
                             ModelMapper mapper) {
        this.validation = validation;
        this.categoryRepo = categoryRepo;
        this.mapper = mapper;
    }
    //**************for the junit ******************************************
    
    
	@Override
	public Boolean saveCategory(CategoryDto categoryDto) {
		

		// Validation Checking
		validation.categoryValidation(categoryDto);
		// check category exist or not
		
		
		// check category exist or not
		Boolean exist = categoryRepo.existsByName(categoryDto.getName().trim());
			if (exist) {
				
			// throw error
				throw new ExistDataException("Category already exist");
			}
		
		

		Category category = mapper.map(categoryDto, Category.class);

		if (ObjectUtils.isEmpty(category.getId())) {
			category.setIsDeleted(false);
			//category.setCreatedBy(1);//becasue weve created the abstract class 
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
	
	

//	@Override
//	public CategoryDto getCategoryById(Integer id) {
//	
//		Optional<Category> findByCatgeory = categoryRepo.findByIdAndIsDeletedFalse(id);
//		if (findByCatgeory.isPresent()) {
//			Category category = findByCatgeory.get();
//			return mapper.map(category, CategoryDto.class);
//		}
//		return null;
//		
//
//	}
	@Override
	public CategoryDto getCategoryById(Integer id) throws Exception {
		
	

		Category category = categoryRepo.findByIdAndIsDeletedFalse(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id=" + id));

		if (!ObjectUtils.isEmpty(category)) {
			category.getName().toUpperCase();
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
			
//			category.setUpdatedBy(1);
//			category.setUpdatedOn(new Date());// becasue weve created the abstract class 
		}
		
		
	}
	
	

}
