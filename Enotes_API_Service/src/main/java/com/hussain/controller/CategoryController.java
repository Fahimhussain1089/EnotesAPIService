package com.hussain.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hussain.dto.CategoryDto;
import com.hussain.dto.CategoryReponse;
import com.hussain.endpoint.CategoryEndpoint;
//import com.hussain.entity.Category;
import com.hussain.service.CategoryService;
import com.hussain.util.CommonUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController implements CategoryEndpoint {
	
	
	@Autowired
	private CategoryService categoryService;

	
//	@PostMapping("/save")
//	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public ResponseEntity<?> saveCategory(@Valid @RequestBody CategoryDto categoryDto) {

		Boolean saveCategory = categoryService.saveCategory(categoryDto);
		if (saveCategory) {
			
			return CommonUtil.createBuildResponseMessage("saved success", HttpStatus.CREATED);

			//return new ResponseEntity<>("saved success", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("not saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@GetMapping("/")
//	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public ResponseEntity<?> getAllCategory() {

//		String nm=null;
//		nm.toUpperCase();

		List<CategoryDto> allCategory = categoryService.getAllCategory();
		if (CollectionUtils.isEmpty(allCategory)) {
			
//			return CommonUtil.createBuildResponseMessage("saved success", HttpStatus.CREATED);
			return ResponseEntity.noContent().build();




//			return ResponseEntity.noContent().build();
		} else {
//			return new ResponseEntity<>(allCategory, HttpStatus.OK);
			return CommonUtil.createBuildResponse(allCategory, HttpStatus.OK);
		}

	}
	
//	@GetMapping("/active")
//	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@Override
	public ResponseEntity<?> getActiveCategory() {

		List<CategoryReponse> allCategory = categoryService.getActiveCategory();
		if (CollectionUtils.isEmpty(allCategory)) {
			
			return CommonUtil.createBuildResponse(allCategory, HttpStatus.OK);

//			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<>(allCategory, HttpStatus.OK);
		}
	}
	
//	@GetMapping("/{id}")
//	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public ResponseEntity<?> getCategortDetailsById(@PathVariable Integer id)throws Exception  {
		
		CategoryDto categoryDto = categoryService.getCategoryById(id);
		
		if (ObjectUtils.isEmpty(categoryDto)) {
			
			return CommonUtil.createErrorResponseMessage("Internal Server Error", HttpStatus.NOT_FOUND);

			
//			return new ResponseEntity<>("Category not found with Id=" + id, HttpStatus.NOT_FOUND);
		
		}
		return CommonUtil.createBuildResponse(categoryDto, HttpStatus.OK);

		//return new ResponseEntity<>(categoryDto, HttpStatus.OK);
		
	
	}
	
//	@DeleteMapping("/{id}")
//	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id) {
		Boolean deleted = categoryService.deleteCategory(id);
		if (deleted) {
			return CommonUtil.createBuildResponse("Category deleted success", HttpStatus.OK);

//			return new ResponseEntity<>("Category deleted success", HttpStatus.OK);
		}
		return CommonUtil.createErrorResponseMessage("Category Not deleted", HttpStatus.INTERNAL_SERVER_ERROR);

//		return new ResponseEntity<>("Category Not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
	

}
