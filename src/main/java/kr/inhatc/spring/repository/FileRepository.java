package kr.inhatc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.model.File;

public interface FileRepository extends JpaRepository<File, Long> {

	
}
