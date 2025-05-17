package com.hussain.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hussain.entity.FavouriteNote;

//public class FavouriteNoteRepository {
//
//}

public interface FavouriteNoteRepository extends JpaRepository<FavouriteNote, Integer> {

	List<FavouriteNote> findByUserId(int userId);

}
