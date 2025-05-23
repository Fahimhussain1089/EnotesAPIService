package com.hussain.endpoint;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hussain.dto.CategoryDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Category", description = "All the Category operation APIs")
@RequestMapping("/api/v1/category")
public interface CategoryEndpoint {
	
	@Operation(summary = "Save Category", tags = { "Category" }, description = "Admin Save Category")
	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> saveCategory(@RequestBody com.hussain.dto.CategoryDto categoryDto);
	
	
	@Operation(summary = "Get All Category", tags = { "Category" }, description = "Admin Get All Category")
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllCategory();
	
	@Operation(summary = "Get Active Category", tags = { "Category" }, description = "Admin,User Get Active Category")
	@GetMapping("/active")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<?> getActiveCategory();
	
	@Operation(summary = "Get Category By id ", tags = { "Category" }, description = "Admin Get Category Deatils")
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getCategortDetailsById(@PathVariable Integer id) throws Exception ;
	
	@Operation(summary = "Delete Category", tags = { "Category" }, description = "Admin Delete Category")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id);
	
	
}