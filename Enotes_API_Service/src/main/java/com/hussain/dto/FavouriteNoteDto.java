package com.hussain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//public class FavouriteNoteDto {
//
//}


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavouriteNoteDto {
	
	private Integer id;

	private NotesDto note;

	private Integer userId;

	public FavouriteNoteDto(Integer id, NotesDto note, Integer userId) {
		super();
		this.id = id;
		this.note = note;
		this.userId = userId;
	}

	public FavouriteNoteDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NotesDto getNote() {
		return note;
	}

	public void setNote(NotesDto note) {
		this.note = note;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
}
