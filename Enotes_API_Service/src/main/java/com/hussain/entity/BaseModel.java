package com.hussain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) 
public abstract class BaseModel {
	
//	private Boolean isActive;
//
//	private Boolean isDeleted;
	
	@CreatedBy
	@Column(updatable = false) 
	private Integer createdBy;
	
	@CreatedDate
	@Column(updatable= false)
	private Date createdOn;
	
	@LastModifiedBy 
	@Column(insertable = false)
	private Integer updatedBy;

	@LastModifiedDate
	@Column(insertable = false)
	private Date updatedOn;

//	public Boolean getIsActive() {
//		return isActive;
//	}
//
//	public void setIsActive(Boolean isActive) {
//		this.isActive = isActive;
//	}
//
//	public Boolean getIsDeleted() {
//		return isDeleted;
//	}
//
//	public void setIsDeleted(Boolean isDeleted) {
//		this.isDeleted = isDeleted;
//	}

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

	public BaseModel(
//			Boolean isActive,
//			Boolean isDeleted, 
			Integer createdBy,
			Date createdOn,
			Integer updatedBy,
			Date updatedOn) {
		super();
//		this.isActive = isActive;
//		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}

	public BaseModel() {
		super();
	}
	
	
	
}
