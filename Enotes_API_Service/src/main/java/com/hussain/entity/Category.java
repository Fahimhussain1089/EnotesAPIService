package com.hussain.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Category extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String description;
	
	private Boolean isActive;

	private Boolean isDeleted;
	public Category(
			Boolean isActive,
			Boolean isDeleted,
			Integer id,
			String name,
			String description
			) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		
		this.isActive = isActive;
		this.isDeleted = isDeleted;
	}

	public Category() {
		super();
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

	public String getDescription() {
		return description;
	}
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	//*************************************************************
	 public static class Builder {
	        private Integer id;
	        private String name;
	        private String description;
	        private Boolean isActive;
	        private Boolean isDeleted;

	        public Builder id(Integer id) {
	            this.id = id;
	            return this;
	        }

	        public Builder name(String name) {
	            this.name = name;
	            return this;
	        }

	        public Builder description(String description) {
	            this.description = description;
	            return this;
	        }

	        public Builder isActive(Boolean isActive) {
	            this.isActive = isActive;
	            return this;
	        }

	        public Builder isDeleted(Boolean isDeleted) {
	            this.isDeleted = isDeleted;
	            return this;
	        }

	        public Category build() {
	            Category category = new Category();
	            category.setId(id);
	            category.setName(name);
	            category.setDescription(description);
	            category.setIsActive(isActive);
	            category.setIsDeleted(isDeleted);
	            return category;
	        }
	    }

	    public static Builder builder() {
	        return new Builder();
	    }
	


	
	
	
}