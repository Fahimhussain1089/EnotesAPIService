package com.hussain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hussain.dto.FavouriteNoteDto;
import com.hussain.dto.NotesDto;
import com.hussain.dto.NotesResponse;
import com.hussain.endpoint.NotesEnpoint;
import com.hussain.entity.FileDetails;
import com.hussain.service.NotesService;
import com.hussain.util.CommonUtil;
import com.hussain.service.NotesService;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController implements NotesEnpoint {

	@Autowired
	private NotesService notesService;
	

//	@PostMapping("/")
//	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseEntity<?> saveNotes(
//			@RequestBody NotesDto notesDto
			@RequestParam String notes, @RequestParam(required = false) MultipartFile file) throws Exception {
		
//		Boolean saveNotes = notesService.saveNotes(notesDto);
		Boolean saveNotes = notesService.saveNotes(notes,file);

		if (saveNotes) {
			return CommonUtil.createBuildResponseMessage("Notes saved success", HttpStatus.CREATED);
		}
		return CommonUtil.createErrorResponseMessage("Notes not saved", HttpStatus.INTERNAL_SERVER_ERROR);
	}

//	@GetMapping("/")
//	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public ResponseEntity<?> getAllNotes() {
		List<NotesDto> notes = notesService.getAllNotes();
		if (CollectionUtils.isEmpty(notes)) //// notes == null || notes.isEmpty()
		{
			return ResponseEntity.noContent().build();
		}
		return CommonUtil.createBuildResponse(notes, HttpStatus.OK);
	}
	
//	@GetMapping("/download/{id}")
//	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@Override
	public ResponseEntity<?> downloadFile(@PathVariable Integer id) throws Exception {
		FileDetails fileDetails = notesService.getFileDetails(id);
		byte[] data = notesService.downloadFile(fileDetails);

		HttpHeaders headers = new HttpHeaders();
		String contentType = CommonUtil.getContentType(fileDetails.getOriginalFileName());
		headers.setContentType(MediaType.parseMediaType(contentType));
		headers.setContentDispositionFormData("attachment", fileDetails.getOriginalFileName());

		return ResponseEntity.ok().headers(headers).body(data);
	}
	
//	@GetMapping("/user-notes")
//	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseEntity<?> getAllNotesByUser(@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		//Integer userId = 2;
		//Integer userId= CommonUtil.getLoggedInUser().getId();

		NotesResponse notes = notesService.getAllNotesByUser(pageNo,pageSize);
//		if (CollectionUtils.isEmpty(notes)) {
//			return ResponseEntity.noContent().build();
//		}
		return CommonUtil.createBuildResponse(notes, HttpStatus.OK);
		
	}
	
	
//	@DeleteMapping("/delete")
//	public ResponseEntity<?> emptyRecyleBin() throws Exception {
//		int userId = 2;
//		notesService.emptyRecycleBin(userId);
//		return CommonUtil.createBuildResponseMessage("Delete Success", HttpStatus.OK);
//	}

//	@GetMapping("/fav/{noteId}")
//	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseEntity<?> favoriteNote(@PathVariable Integer noteId) throws Exception {
		notesService.favoriteNotes(noteId);
		return CommonUtil.createBuildResponseMessage("Notes added Favorite", HttpStatus.CREATED);
	}

	@DeleteMapping("/un-fav/{favNotId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> unFavoriteNote(@PathVariable Integer favNotId) throws Exception {
		notesService.unFavoriteNotes(favNotId);
		return CommonUtil.createBuildResponseMessage("Remove Favorite", HttpStatus.OK);
	}

//	@GetMapping("/fav-note")
//	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseEntity<?> getUserfavoriteNote() throws Exception {

		List<FavouriteNoteDto> userFavoriteNotes = notesService.getUserFavoriteNotes();
		if (CollectionUtils.isEmpty(userFavoriteNotes)) {
			return ResponseEntity.noContent().build();
		}
		return CommonUtil.createBuildResponse(userFavoriteNotes, HttpStatus.OK);
	}
	
	//********************************************************************************
	
	
//	@GetMapping("/delete/{id}")
//	public ResponseEntity<?> deleteNotes(@PathVariable Integer id) throws Exception {
//		notesService.softDeleteNotes(id);
//		return CommonUtil.createBuildResponseMessage("Delete Success", HttpStatus.OK);
//	}
//	@GetMapping("/delete/{id}")
	@Override
	public ResponseEntity<?> deleteNotes(@PathVariable Integer id) throws Exception {
	    try {
	        notesService.softDeleteNotes(id); // Ensure this is calling soft delete
	        return CommonUtil.createBuildResponseMessage("Delete Success", HttpStatus.OK);
	    } catch (IllegalArgumentException e) {
	        return CommonUtil.createBuildResponseMessage(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}

	
//	@GetMapping("/restore/{id}")
//	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseEntity<?> restoreNotes(@PathVariable Integer id) throws Exception {
		notesService.restoreNotes(id);
		return CommonUtil.createBuildResponseMessage("Notes restore Success", HttpStatus.OK);
	}

//	@GetMapping("/recycle-bin")
//	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseEntity<?> getUserRecycleBinNotes() throws Exception {
//		Integer userId = 2;
		//Integer userId= CommonUtil.getLoggedInUser().getId();
		
		List<NotesDto> notes = notesService.getUserRecycleBinNotes();
		if (CollectionUtils.isEmpty(notes)) {
			return CommonUtil.createBuildResponseMessage("Notes not avaible in Recycle Bin", HttpStatus.OK);
		}
		return CommonUtil.createBuildResponse(notes, HttpStatus.OK);
	}

//	@DeleteMapping("/delete/{id}")
//	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseEntity<?> hardDeleteNotes(@PathVariable Integer id) throws Exception {
		notesService.hardDeleteNotes(id);
		return CommonUtil.createBuildResponseMessage("Delete Success", HttpStatus.OK);
	}

//	@DeleteMapping("/delete")
//	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseEntity<?> emptyUserRecyleBin() throws Exception {
//		int userId=2;
		// Integer userId= CommonUtil.getLoggedInUser().getId();

		notesService.emptyRecycleBin();
		return CommonUtil.createBuildResponseMessage("Delete Success", HttpStatus.OK);
	}
	
//	@GetMapping("/copy/{id}")
//	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseEntity<?> copyNotes(@PathVariable Integer id) throws Exception {
		Boolean copyNotes = notesService.copyNotes(id);
		if (copyNotes) {
			return CommonUtil.createBuildResponseMessage("Copied success", HttpStatus.CREATED);
		}
		return CommonUtil.createErrorResponseMessage("Copy failed ! Try Again", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	@GetMapping("/search")
//	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseEntity<?> searchNotes(
			@RequestParam(name = "key",defaultValue = "") String key,
			@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) 
	{
		NotesResponse notes = notesService.getNotesByUserSearch(pageNo, pageSize,key);
		return CommonUtil.createBuildResponse(notes, HttpStatus.OK);
	}



}