package com.hussain.dto;
import com.hussain.dto.NotesDto.CategoryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotesRequest {

	private String title;

	private String description;

	private CategoryDto category;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public NotesRequest() {
		super();
	}

	public NotesRequest(String title, String description, CategoryDto category) {
		super();
		this.title = title;
		this.description = description;
		this.category = category;
	}
}