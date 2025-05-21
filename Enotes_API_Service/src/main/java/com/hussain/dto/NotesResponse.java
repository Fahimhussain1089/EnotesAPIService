package com.hussain.dto;

import java.util.List;
import lombok.*;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Builder
//public class NotesResponse {
//
//	private List<NotesDto> notes;
//
//	private Integer pageNo;
//	private Integer pageSize;
//	private Long totalElements;
//	private Integer totalPages;
//	private Boolean isFirst;
//	private Boolean isLast;
//	
//	
//	
//	public NotesResponse() {
//		super();
//	}
//	
//	
//	
//	public NotesResponse(List<NotesDto> notes, Integer pageNo, Integer pageSize, Long totalElements, Integer totalPages,
//			Boolean isFirst, Boolean isLast) {
//		super();
//		this.notes = notes;
//		this.pageNo = pageNo;
//		this.pageSize = pageSize;
//		this.totalElements = totalElements;
//		this.totalPages = totalPages;
//		this.isFirst = isFirst;
//		this.isLast = isLast;
//	}
//	public List<NotesDto> getNotes() {
//		return notes;
//	}
//	public void setNotes(List<NotesDto> notes) {
//		this.notes = notes;
//	}
//	public Integer getPageNo() {
//		return pageNo;
//	}
//	public void setPageNo(Integer pageNo) {
//		this.pageNo = pageNo;
//	}
//	public Integer getPageSize() {
//		return pageSize;
//	}
//	public void setPageSize(Integer pageSize) {
//		this.pageSize = pageSize;
//	}
//	public Long getTotalElements() {
//		return totalElements;
//	}
//	public void setTotalElements(Long totalElements) {
//		this.totalElements = totalElements;
//	}
//	public Integer getTotalPages() {
//		return totalPages;
//	}
//	public void setTotalPages(Integer totalPages) {
//		this.totalPages = totalPages;
//	}
//	public Boolean getIsFirst() {
//		return isFirst;
//	}
//	public void setIsFirst(Boolean isFirst) {
//		this.isFirst = isFirst;
//	}
//	public Boolean getIsLast() {
//		return isLast;
//	}
//	public void setIsLast(Boolean isLast) {
//		this.isLast = isLast;
//	}
//	
//
//        
//    
//	
//	
//	
//	
//	
//
//}




import java.util.List;

public class NotesResponse {
    private List<NotesDto> notes;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean isFirst;
    private Boolean isLast;

    // Builder implementation
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<NotesDto> notes;
        private Integer pageNo;
        private Integer pageSize;
        private Long totalElements;
        private Integer totalPages;
        private Boolean isFirst;
        private Boolean isLast;

        public Builder notes(List<NotesDto> notes) {
            this.notes = notes;
            return this;
        }

        public Builder pageNo(Integer pageNo) {
            this.pageNo = pageNo;
            return this;
        }

        public Builder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder totalElements(Long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public Builder totalPages(Integer totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Builder isFirst(Boolean isFirst) {
            this.isFirst = isFirst;
            return this;
        }

        public Builder isLast(Boolean isLast) {
            this.isLast = isLast;
            return this;
        }

        public NotesResponse build() {
            NotesResponse response = new NotesResponse();
            response.notes = this.notes;
            response.pageNo = this.pageNo;
            response.pageSize = this.pageSize;
            response.totalElements = this.totalElements;
            response.totalPages = this.totalPages;
            response.isFirst = this.isFirst;
            response.isLast = this.isLast;
            return response;
        }
    }

    // Getters
    public List<NotesDto> getNotes() { return notes; }
    public Integer getPageNo() { return pageNo; }
    public Integer getPageSize() { return pageSize; }
    public Long getTotalElements() { return totalElements; }
    public Integer getTotalPages() { return totalPages; }
    public Boolean getIsFirst() { return isFirst; }
    public Boolean getIsLast() { return isLast; }
}