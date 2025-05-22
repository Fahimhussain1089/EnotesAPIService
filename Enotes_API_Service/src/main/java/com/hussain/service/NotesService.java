package com.hussain.service;

//public interface NotesService {
//
//}


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hussain.dto.FavouriteNoteDto;
import com.hussain.dto.NotesDto;
import com.hussain.dto.NotesResponse;
import com.hussain.entity.FileDetails;

public interface NotesService {

//	public Boolean saveNotes(NotesDto notesDto) throws Exception;
	public Boolean saveNotes(String notes,MultipartFile file) throws Exception;

	
	public List<NotesDto> getAllNotes();
	
	public byte[] downloadFile(FileDetails fileDtls) throws Exception;

	public FileDetails getFileDetails(Integer id) throws Exception;
	
	public NotesResponse getAllNotesByUser( Integer pageNo, Integer pageSize);//here is change
	
	public NotesResponse getNotesByUserSearch(Integer pageNo, Integer pageSize,String keyword);

	//********************************************************************************************
	public void softDeleteNotes(Integer id) throws Exception;

	public void restoreNotes(Integer id) throws Exception;

	public List<NotesDto> getUserRecycleBinNotes();//here is change

	public void hardDeleteNotes(Integer id) throws Exception;

	public void emptyRecycleBin(); //here is change

	public void favoriteNotes(Integer noteId) throws Exception;//here is change

	public void unFavoriteNotes(Integer noteId) throws Exception;

	public List<FavouriteNoteDto> getUserFavoriteNotes() throws Exception;
	
	public Boolean copyNotes(Integer id) throws Exception;



}