package com.hussain.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//public class NotesDto {
//
//}


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotesDto {

	private Integer id;

	private String title;

	private String description;

//	private CategoryDto category;
    private CategoryDto category; // Uses top-level CategoryDto


	

	private Integer createdBy;

	private Date createdOn;

	private Integer updatedBy;

	private Date updatedOn;
	
	private FilesDto fileDetails;
	
	private Boolean isDeleted;

	private LocalDateTime deletedOn;
	
	
	public NotesDto(Integer id, String title, String description, CategoryDto category, Integer createdBy,
			Date createdOn, Integer updatedBy, Date updatedOn,FilesDto fileDetails,Boolean isDeleted,LocalDateTime deletedOn) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.category = category;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.fileDetails =  fileDetails;
		this.isDeleted = isDeleted;
		this.deletedOn = deletedOn;
	}
	
	public NotesDto() {
		super();
	}

	
	
	
	

	public Boolean getIsDeleted() {
		return isDeleted;
	}



	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}



	public LocalDateTime getDeletedOn() {
		return deletedOn;
	}



	public void setDeletedOn(LocalDateTime deletedOn) {
		this.deletedOn = deletedOn;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}

	public FilesDto getFileDetails() {
		return fileDetails;
	}



	public void setFileDetails(FilesDto fileDetails) {
		this.fileDetails = fileDetails;
	}



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



	public Integer getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}



	public Date getCreatedOn() {
		return createdOn;
	}



	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}



	public Integer getUpdatedBy() {
		return updatedBy;
	}



	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}



	public Date getUpdatedOn() {
		return updatedOn;
	}



	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
	
	
	





	//**************************************************************************
	
	
	//**************************************************************************

	



//	@Getter
//	@Setter
//	@AllArgsConstructor
//	@NoArgsConstructor
//	public static class CategoryDto {
//		private Integer id;
//		private String name;
//	}
//	
//	
//	public Integer getId() {
//		return id;
//	}
//
//
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class FilesDto {
		private Integer id;
		private String originalFileName;
		private String displayFileName;
		
		public FilesDto() {
			super();
		}
		
		public FilesDto(Integer id, String originalFileName, String displayFileName) {
			super();
			this.id = id;
			this.originalFileName = originalFileName;
			this.displayFileName = displayFileName;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getOriginalFileName() {
			return originalFileName;
		}
		public void setOriginalFileName(String originalFileName) {
			this.originalFileName = originalFileName;
		}
		public String getDisplayFileName() {
			return displayFileName;
		}
		public void setDisplayFileName(String displayFileName) {
			this.displayFileName = displayFileName;
		}
	}
	
	//**************************************************************************
	
	
	//**************************************************************************
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CategoryDto {
		private Integer id;
		private String name;
		
		
		public CategoryDto() {
			super();
		}
		public CategoryDto(Integer id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
	
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	
	
	
	

}
