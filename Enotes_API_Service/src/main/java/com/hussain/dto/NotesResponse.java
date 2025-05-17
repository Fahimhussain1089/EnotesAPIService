package com.hussain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//
//public class NotesResponse {
//
//}



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NotesResponse {

	private List<NotesDto> notes;

	private Integer pageNo;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;
	private Boolean isFirst;
	private Boolean isLast;
	
	
	
	public NotesResponse() {
		super();
	}
	
	
	
	public NotesResponse(List<NotesDto> notes, Integer pageNo, Integer pageSize, Long totalElements, Integer totalPages,
			Boolean isFirst, Boolean isLast) {
		super();
		this.notes = notes;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.isFirst = isFirst;
		this.isLast = isLast;
	}
	public List<NotesDto> getNotes() {
		return notes;
	}
	public void setNotes(List<NotesDto> notes) {
		this.notes = notes;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public Boolean getIsFirst() {
		return isFirst;
	}
	public void setIsFirst(Boolean isFirst) {
		this.isFirst = isFirst;
	}
	public Boolean getIsLast() {
		return isLast;
	}
	public void setIsLast(Boolean isLast) {
		this.isLast = isLast;
	}
	
	
	
	
	

}