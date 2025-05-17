package com.hussain.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//public class Notes {
//
//}
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)

public class Notes extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;

	private String description;

	@ManyToOne
	private Category category;
	
	
	@ManyToOne
	private FileDetails fileDetails;
	
//	private Boolean isDeleted;
    private Boolean isDeleted = Boolean.FALSE; // Use Boolean.FALSE for clarity

	
	

	private LocalDateTime deletedOn;
	public Notes(Integer createdBy, Date createdOn, Integer updatedBy, Date updatedOn, Integer id, String title,
			String description, Category category,FileDetails fileDetails,Boolean isDeleted,LocalDateTime deletedOn ) {
		super(createdBy, createdOn, updatedBy, updatedOn);
		this.id = id;
		this.title = title;
		this.description = description;
		this.category = category;
		this.fileDetails=fileDetails;
//		this.isDeleted = isDeleted;
	    this.isDeleted = isDeleted != null ? isDeleted : Boolean.FALSE; // Null check

		this.deletedOn = deletedOn;
	}

	
	
	
	
	

//	public Boolean getIsDeleted() {
//		return isDeleted;
//	}

	public Boolean getIsDeleted() {
	    return isDeleted != null ? isDeleted : Boolean.FALSE;
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

	public FileDetails getFileDetails() {
		return fileDetails;
	}

	public void setFileDetails(FileDetails fileDetails) {
		this.fileDetails = fileDetails;
	}

	
	public Notes(Integer createdBy, Date createdOn, Integer updatedBy, Date updatedOn) {
		super(createdBy, createdOn, updatedBy, updatedOn);
	}
	public Notes() {
        super(); // Ensure BaseModel has a no-args constructor
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	
	
	
	

}
