package com.hussain.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hussain.dto.CategoryDto;
import com.hussain.endpoint.CategoryEndpoint;
import com.hussain.entity.Category;
import com.hussain.exception.ExistDataException;
import com.hussain.service.CategoryService;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

	@Mock
	private CategoryService categoryService;

	@InjectMocks
	private CategoryController categoryController;
	
	private CategoryDto categoryDto = null;
	private Category category = null;
	private List<Category> categories = new ArrayList<>();
	private List<CategoryDto> categoriesDto = new ArrayList<>();

//	@BeforeEach
//	public void initalize() {
//		categoryDto = CategoryDto.builder().id(null).name("Java Notes").description("java notes").isActive(true)
//				.build();
//
//		category = Category.builder().id(null).name("Java Notes").description("java notes").isActive(true)
//				.isDeleted(false).build();
//
//		categories.add(category);
//		categoriesDto.add(categoryDto);
//	}
	//***************************************************************
	@BeforeEach
	public void initialize() {
	    // Initialize CategoryDto using constructor
	    categoryDto = new CategoryDto();
	    categoryDto.setId(null);
	    categoryDto.setName("Java Notes");
	    categoryDto.setDescription("java notes");
	    categoryDto.setIsActive(true);
	    
	    // Initialize Category using constructor
	    category = new Category();
	    category.setId(null);
	    category.setName("Java Notes");
	    category.setDescription("java notes");
	    category.setIsActive(true);
	    category.setIsDeleted(false);
	    
	    // Initialize lists
	    categories = new ArrayList<>();
	    categories.add(category);
	    
	    categoriesDto = new ArrayList<>();
	    categoriesDto.add(categoryDto);
	}


	@Test
	public void testSaveCategory_Success() {
	    // Arrange
	    when(categoryService.saveCategory(categoryDto)).thenReturn(true);
	    
	    // Act
	    ResponseEntity<String> response = categoryController.saveCategory(categoryDto);
	    
	    // Assert
	    assertEquals(HttpStatus.CREATED, response.getStatusCode());
	    assertEquals("saved success", response.getBody());
	}

	@Test
	public void testCategoryNotSaved() {
	    // Arrange
	    when(categoryService.saveCategory(categoryDto)).thenReturn(false);
	    
	    // Act
	    ResponseEntity<String> response = categoryController.saveCategory(categoryDto);
	    
	    // Assert
	    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	    assertEquals("not saved", response.getBody());
	}

	@Test
	public void testSaveCategory_WhenExists() {
	    // Arrange
	    when(categoryService.saveCategory(categoryDto))
	        .thenThrow(new ExistDataException("Category already exists"));
	    
	    // Act & Assert
	    ResponseEntity<String> response = categoryController.saveCategory(categoryDto);
	    
	    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	    assertEquals("Category already exists", response.getBody());
	}

}