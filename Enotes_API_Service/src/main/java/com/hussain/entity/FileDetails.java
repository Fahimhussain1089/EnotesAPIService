package com.hussain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//public class FileDetails {
//
//}


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class FileDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String uploadFileName;

	private String originalFileName;

	private String displayFileName;

	private String path;

	private Long fileSize;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public FileDetails(Integer id, String uploadFileName, String originalFileName, String displayFileName, String path,
			Long fileSize) {
		super();
		this.id = id;
		this.uploadFileName = uploadFileName;
		this.originalFileName = originalFileName;
		this.displayFileName = displayFileName;
		this.path = path;
		this.fileSize = fileSize;
	}

	public FileDetails() {
		super();
	}
	
	
	
	

}
