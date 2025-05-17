package com.hussain.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//public class NotesRepository {
//
//}

import org.springframework.data.jpa.repository.JpaRepository;

import com.hussain.entity.Notes;

public interface NotesRepository extends JpaRepository<Notes, Integer>{
	Page<Notes> findByCreatedBy(Integer userId, Pageable pageable);
	
	
	List<Notes> findByCreatedByAndIsDeletedTrue(Integer userId);

	Page<Notes> findByCreatedByAndIsDeletedFalse(Integer userId, Pageable pageable);

	List<Notes> findAllByIsDeletedAndDeletedOnBefore(boolean b, LocalDateTime cutOffDate);


}
