package kr.inhatc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{

}