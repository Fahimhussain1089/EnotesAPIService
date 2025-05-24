package com.hussain.dto;

import java.util.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

	private Integer id;

	private String name;
	
	private String description;

	@NotNull
	private Boolean isActive;

	private Integer createdBy;

	private Date createdOn;

	private Integer updatedBy;

	private Date updatedOn;
	
	public CategoryDto() {
		super();
	}
	
	
	
	
	public CategoryDto(Integer id, String name, String description, Boolean isActive, Integer createdBy, Date createdOn,
			Integer updatedBy, Date updatedOn) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.isActive = isActive;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
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

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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
	//*************************************************************
	 

	    // Manual builder implementation
	    public static Builder builder() {
	        return new Builder();
	    }

	    // Builder class
	    public static class Builder {
	        private Integer id;
	        private String name;
	        private String description;
	        private Boolean isActive;
	        private Integer createdBy;
	        private Date createdOn;
	        private Integer updatedBy;
	        private Date updatedOn;

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

	        public Builder createdBy(Integer createdBy) {
	            this.createdBy = createdBy;
	            return this;
	        }

	        public Builder createdOn(Date createdOn) {
	            this.createdOn = createdOn;
	            return this;
	        }

	        public Builder updatedBy(Integer updatedBy) {
	            this.updatedBy = updatedBy;
	            return this;
	        }

	        public Builder updatedOn(Date updatedOn) {
	            this.updatedOn = updatedOn;
	            return this;
	        }

	        public CategoryDto build() {
	            CategoryDto dto = new CategoryDto();
	            dto.id = this.id;
	            dto.name = this.name;
	            dto.description = this.description;
	            dto.isActive = this.isActive;
	            dto.createdBy = this.createdBy;
	            dto.createdOn = this.createdOn;
	            dto.updatedBy = this.updatedBy;
	            dto.updatedOn = this.updatedOn;
	            return dto;
	        }
	    }

	

	
	
	
	
}