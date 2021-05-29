package kr.inhatc.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.dto.SubjectDto;
import kr.inhatc.spring.repository.SubjectRepository;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	public SubjectService(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}
	
	@Transactional
	public int saveSubject (SubjectDto subjectDto) {
		return subjectRepository.save(subjectDto.toEntity()).getSeq();
	}
}