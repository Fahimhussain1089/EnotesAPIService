package com.hussain.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TodoDto {

	private Integer id;

	private String title;

	private StatusDto status;

	private Integer createdBy;

	private Date createdOn;

	private Integer updatedBy;

	private Date updatedOn;
	
	
	public TodoDto() {
		super();
	}

	public TodoDto(Integer id, String title, StatusDto status, Integer createdBy, Date createdOn, Integer updatedBy,
				Date updatedOn) {
			super();
			this.id = id;
			this.title = title;
			this.status = status;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public StatusDto getStatus() {
		return status;
	}

	public void setStatus(StatusDto status) {
		this.status = status;
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

	





//****************************************************************************************
	
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	public static class StatusDto {
		private Integer id;
		private String name;
		
		public StatusDto() {
			super();
		}
		
		public StatusDto(Integer id, String name) {
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
