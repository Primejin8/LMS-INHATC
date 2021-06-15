package kr.inhatc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.model.Board;
import kr.inhatc.spring.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{
	Page<Subject> findAllBysubNameContaining(String subName, Pageable pageable);
	Page<Subject> findAllByempNameContaining(String empName, Pageable pageable);
}