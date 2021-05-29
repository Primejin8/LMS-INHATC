package kr.inhatc.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.dto.SubjectStudentDto;
import kr.inhatc.spring.repository.SubjectStudentRepository;

@Service
public class SubjectStudentService {
	
	@Autowired
	private SubjectStudentRepository subjectStudentRepository;
	
	public SubjectStudentService(SubjectStudentRepository subjectStudentRepository) {
		this.subjectStudentRepository = subjectStudentRepository;
	}
	
	@Transactional
	public int saveSubjectStudent (SubjectStudentDto subjectStudentDto) {
		return subjectStudentRepository.save(subjectStudentDto.toEntity()).getSeq();
	}
	
}