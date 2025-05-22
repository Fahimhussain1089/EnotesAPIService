package com.hussain.service.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;

//public class NotesServiceImpl {
//
//}


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hussain.dto.CategoryDto;
import com.hussain.dto.FavouriteNoteDto;
import com.hussain.dto.NotesDto;
import com.hussain.dto.NotesDto.FilesDto;
import com.hussain.dto.NotesResponse;
import com.hussain.entity.Category;
import com.hussain.entity.FavouriteNote;
import com.hussain.entity.Notes;
import com.hussain.exception.GlobalExceptionHandler;
import com.hussain.exception.ResourceNotFoundException;
import com.hussain.repo.CategoryRepository;
import com.hussain.repo.FavouriteNoteRepository;
import com.hussain.repo.FileRepository;
import com.hussain.repo.NotesRepository;
import com.hussain.service.NotesService;
import com.hussain.util.CommonUtil;

import org.apache.commons.io.FilenameUtils;
import com.hussain.entity.FileDetails;



@Service
public class NotesServiceImpl implements NotesService {

	@Autowired
	private NotesRepository notesRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CategoryRepository categoryRepo;
	
	
	@Value("${file.upload.path}")
	private String uploadpath;

	@Autowired
	private FileRepository fileRepo;
	
	@Autowired
	private FavouriteNoteRepository favouriteNoteRepo;
	
	
	
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	
	
	

	@Override
	public Boolean saveNotes(String notes, MultipartFile file) throws Exception {
		
		ObjectMapper ob = new ObjectMapper();
		NotesDto notesDto = ob.readValue(notes, NotesDto.class);
		
		// update notes if id is given in request
		if (!ObjectUtils.isEmpty(notesDto.getId())) {
			updateNotes(notesDto, file);
		}

		// category validation
		checkCategoryExist(notesDto.getCategory());

		Notes notesMap = mapper.map(notesDto, Notes.class);
		log.error("check waht is the notesDto \n :: notesDto ::",notesDto);
		log.info("Logged-in user notesMap : {}", notesMap);
		
		

		FileDetails fileDtls = saveFileDetails(file);
		log.info("fileDtls is here :: \n ", fileDtls);


		if (!ObjectUtils.isEmpty(fileDtls)) {
			notesMap.setFileDetails(fileDtls);
		} else {
			//notesMap.setFileDetails(null);
			if (ObjectUtils.isEmpty(notesDto.getId())) { //agr user ne file nai di . tu null save na hoo. hos akta hai isbar file nai di . data base already the isliye
				notesMap.setFileDetails(null);
			}
		}

		Notes saveNotes = notesRepo.save(notesMap);
		log.info("saveNotes is here :: \n ", saveNotes);

		if (!ObjectUtils.isEmpty(saveNotes)) {
			return true;
		}
		return false;
	}



	@Override
	public List<NotesDto> getAllNotes() {
		return notesRepo.findAll().stream().map(note -> mapper.map(note, NotesDto.class)).toList();
	}
	
	@Override
	public byte[] downloadFile(FileDetails fileDetails) throws Exception {

		InputStream io = new FileInputStream(fileDetails.getPath());

		return StreamUtils.copyToByteArray(io);
	}

	@Override
	public FileDetails getFileDetails(Integer id) throws Exception {
		FileDetails fileDtls = fileRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("File is not available"));
		return fileDtls;
	}
	
	
	//___________________________Below is the Methode Define_________________________________________________
	
//	private void checkCategoryExist(CategoryDto category) throws Exception {
//		categoryRepo.findById(category.getId()).orElseThrow(() -> new ResourceNotFoundException("category id invalid"));
//	}
	private void checkCategoryExist(NotesDto.CategoryDto category) throws Exception {
	    categoryRepo.findById(category.getId())
	              .orElseThrow(() -> new ResourceNotFoundException("Category ID invalid"));
	}
	
	
	private FileDetails saveFileDetails(MultipartFile file) throws IOException {

		if (!ObjectUtils.isEmpty(file) && !file.isEmpty()) {

			String originalFilename = file.getOriginalFilename();
			String extension = FilenameUtils.getExtension(originalFilename);

			List<String> extensionAllow = Arrays.asList("pdf", "xlsx", "jpg", "png");
			if (!extensionAllow.contains(extension)) {
				throw new IllegalArgumentException("invalid file format ! Upload only .pdf , .xlsx,.jpg");
			}

			String rndString = UUID.randomUUID().toString();
			String uploadfileName = rndString + "." + extension; // sdfsafbhkljsf.pdf

			File saveFile = new File(uploadpath);
			if (!saveFile.exists()) {
				saveFile.mkdir();
			}
			// path : enotesapiservice/notes/java.pdf
			String storePath = uploadpath.concat(uploadfileName);

			// upload file
			long upload = Files.copy(file.getInputStream(), Paths.get(storePath));
			if (upload != 0) {
				FileDetails fileDtls = new FileDetails();
				fileDtls.setOriginalFileName(originalFilename);
				fileDtls.setDisplayFileName(getDisplayName(originalFilename));
				fileDtls.setUploadFileName(uploadfileName);
				fileDtls.setFileSize(file.getSize());
				fileDtls.setPath(storePath);
				FileDetails saveFileDtls = fileRepo.save(fileDtls);
				return saveFileDtls;
			}
		}

		return null;
	}

	private String getDisplayName(String originalFilename) {
		// java_programming_tutorials.pdf
		// java_prog.pdf
		String extension = FilenameUtils.getExtension(originalFilename);
		String fileName = FilenameUtils.removeExtension(originalFilename);

		if (fileName.length() > 8) {
			fileName = fileName.substring(0, 7);
		}
		fileName = fileName + "." + extension;
		return fileName;
	}




//	@Override
//	public NotesResponse getAllNotesByUser( Integer pageNo, Integer pageSize) {
//		// 10 = 5,5 = 2 pages
//		Pageable pageable = PageRequest.of(pageNo, pageSize);
//		Integer userId= CommonUtil.getLoggedInUser().getId();
//
//		Page<Notes> pageNotes = notesRepo.findByCreatedBy(userId, pageable);
//
//		List<NotesDto> notesDto = pageNotes.get().map(n -> mapper.map(n, NotesDto.class)).toList();
//
////		NotesResponse notes = NotesResponse.builder()
////				.notes(notesDto)
////				.pageNo(pageNotes.getNumber())
////				.pageSize(pageNotes.getSize())
////				.totalElements(pageNotes.getTotalElements())
////				.totalPages(pageNotes.getTotalPages())
////				.isFirst(pageNotes.isFirst())
////				.isLast(pageNotes.isLast())
////				.build();
//		
//		 NotesResponse notes = new NotesResponse();
//		 	notes.setNotes(notesDto);
//		 	notes.setPageNo(pageNotes.getNumber());
//		 	notes.setPageSize(pageNotes.getSize());
//		 	notes.setTotalElements(pageNotes.getTotalElements());
//		 	notes.setTotalPages(pageNotes.getTotalPages());
//		 	notes.setIsFirst(pageNotes.isFirst());
//		 	notes.setIsLast(pageNotes.isLast());
//
//		return notes;
//	}
	
//	@Override
//	public NotesResponse getAllNotesByUser( Integer pageNo, Integer pageSize) {
//		// 10 = 5,5 = 2 pages
//		Integer userId = CommonUtil.getLoggedInUser().getId();
//		Pageable pageable = PageRequest.of(pageNo, pageSize);
//		Page<Notes> pageNotes = notesRepo.findByCreatedByAndIsDeletedFalse(userId, pageable);
//
//		List<NotesDto> notesDto = pageNotes.get().map(n -> mapper.map(n, NotesDto.class)).toList();
//
//		NotesResponse notes = NotesResponse.builder().notes(notesDto).pageNo(pageNotes.getNumber())
//				.pageSize(pageNotes.getSize()).totalElements(pageNotes.getTotalElements())
//				.totalPages(pageNotes.getTotalPages()).isFirst(pageNotes.isFirst()).isLast(pageNotes.isLast()).build();
//		
////		 NotesResponse notes = new NotesResponse();
////		 	notes.setNotes(notesDto);
////		 	notes.setPageNo(pageNotes.getNumber());
////		 	notes.setPageSize(pageNotes.getSize());
////		 	notes.setTotalElements(pageNotes.getTotalElements());
////		 	notes.setTotalPages(pageNotes.getTotalPages());
////		 	notes.setIsFirst(pageNotes.isFirst());
////		 	notes.setIsLast(pageNotes.isLast());
//			log.error("check what is the store in the log\n :: notes ::",notes);
//			log.info("Logged-in user ID: {}", userId);
//			log.info("Logged-in user notesDto : \n", notesDto);
//			log.info("Query executed. Found {} notes", pageNotes.getTotalElements());
//
//
//
//		return notes;
//	}
	
	
	@Override
	public NotesResponse getAllNotesByUser(Integer pageNo, Integer pageSize) {
	    Integer userId = CommonUtil.getLoggedInUser().getId();
	    log.info("Fetching notes for user ID: {}", userId);
	    
	    Pageable pageable = PageRequest.of(pageNo, pageSize);
	    Page<Notes> pageNotes = notesRepo.findByCreatedByAndIsDeletedFalse(userId, pageable);

	    List<NotesDto> notesDto = pageNotes.getContent().stream()
	            .map(n -> mapper.map(n, NotesDto.class))
	            .toList();

	    // Debug logging
	    log.debug("Retrieved {} notes from repository", pageNotes.getNumberOfElements());
	    notesDto.forEach(note -> log.debug("Note ID: {}, Title: {}", note.getId(), note.getTitle()));

	    NotesResponse response = NotesResponse.builder()
	            .notes(notesDto)
	            .pageNo(pageNotes.getNumber())
	            .pageSize(pageNotes.getSize())
	            .totalElements(pageNotes.getTotalElements())
	            .totalPages(pageNotes.getTotalPages())
	            .isFirst(pageNotes.isFirst())
	            .isLast(pageNotes.isLast())
	            .build();

	    log.info("Constructed response with {} notes (page {} of {})", 
	            notesDto.size(), pageNo + 1, pageNotes.getTotalPages());
	    
	    return response;
	}


	//******************************************************************************
	
	
//	@Override
//	public void softDeleteNotes(Integer id) throws Exception {
//
//		Notes notes = notesRepo.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Notes id invalid ! Not Found"));
//		notes.setIsDeleted(true);
//		notes.setDeletedOn(LocalDateTime.now());
//		notesRepo.save(notes);
//	}
	@Override
	public void softDeleteNotes(Integer id) throws Exception {
	    Notes notes = notesRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Notes id invalid ! Not Found"));
	    
	    // Add null check
	    if (notes.getIsDeleted() == null) {
	        notes.setIsDeleted(false);
	    }
	    
	    notes.setIsDeleted(true);
	    notes.setDeletedOn(LocalDateTime.now());
	    notesRepo.save(notes);
	}

	@Override
	public void restoreNotes(Integer id) throws Exception {
		Notes notes = notesRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Notes id invalid ! Not Found"));
		notes.setIsDeleted(false); //false mean restore kr liya
		notes.setDeletedOn(null);
		notesRepo.save(notes);

	}

	@Override
	public List<NotesDto> getUserRecycleBinNotes() {
		Integer userId= CommonUtil.getLoggedInUser().getId();

		List<Notes> recycleNotes = notesRepo.findByCreatedByAndIsDeletedTrue(userId);
		List<NotesDto> notesDtoList = recycleNotes.stream().map(note -> mapper.map(note, NotesDto.class)).toList();
		return notesDtoList;
	}

	@Override
//	public void hardDeleteNotes(Integer id) throws Exception {
//		Notes notes = notesRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notes not found"));
//		if (notes.getIsDeleted()) {
//			notesRepo.delete(notes);
//		} else {
//			throw new IllegalArgumentException("Sorry You cant hard delete Directly");
//		}
//	}
//	
	public void hardDeleteNotes(Integer id) throws Exception {
	    // First check if it's already soft-deleted
	    Notes notes = notesRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Notes not found"));
	            
	    if (!Boolean.TRUE.equals(notes.getIsDeleted())) {
	        throw new IllegalArgumentException("You must soft-delete before hard deleting");
	    }
	    
	    notesRepo.deleteById(id);
	}

	@Override
	public void emptyRecycleBin() {
		Integer userId= CommonUtil.getLoggedInUser().getId();

		List<Notes> recycleNotes = notesRepo.findByCreatedByAndIsDeletedTrue(userId);
		if (!CollectionUtils.isEmpty(recycleNotes)) {
			notesRepo.deleteAll(recycleNotes);
		}
	}

	@Override
	public void favoriteNotes(Integer noteId) throws Exception {
		int userId = 2;
		Notes notes = notesRepo.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Notes Not found & Id invalid"));
//		FavouriteNote favouriteNote = FavouriteNote.builder().note(notes).userId(userId).build();
		 
		FavouriteNote favouriteNote = new FavouriteNote();
		    favouriteNote.setNote(notes);
		    favouriteNote.setUserId(userId);
		    favouriteNoteRepo.save(favouriteNote);
		    
		favouriteNoteRepo.save(favouriteNote);
	}

	@Override
	public void unFavoriteNotes(Integer favouriteNoteId) throws Exception {
		FavouriteNote favNote = favouriteNoteRepo.findById(favouriteNoteId)
				.orElseThrow(() -> new ResourceNotFoundException("Favourite Note Not found & Id invalid"));
		favouriteNoteRepo.delete(favNote);
	}

	@Override
	public List<FavouriteNoteDto> getUserFavoriteNotes() throws Exception {
//		int userId = 2;
		Integer userId= CommonUtil.getLoggedInUser().getId();

		List<FavouriteNote> favouriteNotes = favouriteNoteRepo.findByUserId(userId);
		return favouriteNotes.stream().map(fn -> mapper.map(fn, FavouriteNoteDto.class)).toList();
	}
	
	@Override
	public Boolean copyNotes(Integer id) throws Exception {
		Notes notes = notesRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Notes id invalid ! Not Found"));

//		Notes copyNote=new Notes();
//		copyNote.setTitle(notes.getTitle());

//		Notes copyNote = Notes.builder().title(notes.getTitle()).description(notes.getDescription())
//				.category(notes.getCategory()).isDeleted(false).fileDetails(null).build();
		
		
		Notes copyNote = new Notes();
		copyNote.setTitle(notes.getTitle());
		copyNote.setDescription(notes.getDescription());
		copyNote.setCategory(notes.getCategory());
		copyNote.setIsDeleted(false);
		copyNote.setFileDetails(null);
		
		
		// TODO : Need to check User Validation
		Notes saveCopyNote = notesRepo.save(copyNote);
		if (!ObjectUtils.isEmpty(saveCopyNote)) {
			return true;
		}
		return false;
	}
	

	@Override
	public NotesResponse getNotesByUserSearch(Integer pageNo, Integer pageSize,String keyword) {
		Integer userId = CommonUtil.getLoggedInUser().getId();
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Notes> pageNotes = notesRepo.searchNotes(keyword,userId, pageable);

		List<NotesDto> notesDto = pageNotes.get().map(n -> mapper.map(n, NotesDto.class)).toList();

		NotesResponse notes = NotesResponse.builder().notes(notesDto).pageNo(pageNotes.getNumber())
				.pageSize(pageNotes.getSize()).totalElements(pageNotes.getTotalElements())
				.totalPages(pageNotes.getTotalPages()).isFirst(pageNotes.isFirst()).isLast(pageNotes.isLast()).build();

		return notes;
	}


	
	
	//*****************************methode is here******************************
	
	
	
	
	
	private void updateNotes(NotesDto notesDto, MultipartFile file) throws Exception {

		Notes existNotes = notesRepo.findById(notesDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Notes id"));

		// user not choose any file at update time
		if (ObjectUtils.isEmpty(file)) {
			notesDto.setFileDetails(mapper.map(existNotes.getFileDetails(), FilesDto.class));
		}

	}





	

	
	

}