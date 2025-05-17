package com.hussain.repo;

//public class FileRepository {
//
//}

import org.springframework.data.jpa.repository.JpaRepository;

import com.hussain.entity.FileDetails;

public interface FileRepository extends JpaRepository<FileDetails, Integer> {

}
