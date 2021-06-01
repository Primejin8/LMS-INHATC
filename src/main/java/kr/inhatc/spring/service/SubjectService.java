package kr.inhatc.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.dto.SubjectDto;
import kr.inhatc.spring.model.Subject;
import kr.inhatc.spring.repository.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	public SubjectService(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}

	@Transactional
	public int saveSubject(SubjectDto subjectDto) {
		return subjectRepository.save(subjectDto.toEntity()).getSeq();
	}

	// 강의 정보 가져오기
	@Transactional
	public SubjectDto getSubject(int seq) {
		Subject subject = subjectRepository.findById(seq).get();

		SubjectDto subjectDto = SubjectDto.builder()
				.seq(subject.getSeq())
				.bizSeq(subject.getBizSeq())
				.groupSeq(subject.getGroupSeq())
				.compSeq(subject.getCompSeq())
				.gubun(subject.getGubun())
				.subName(subject.getSubName())
				.empName(subject.getEmpName())
				.hakjum(subject.getHakjum())
				.sDate(subject.getSDate())
				.eDate(subject.getEDate())
				.stuCnt(subject.getStuCnt())
				.useYn(subject.getUseYn())
				.createSeq(subject.getCreateSeq())
				.createdDate(subject.getCreatedDate())
				.modifySeq(subject.getModifySeq())
				.modifiedDate(subject.getModifiedDate())
				.build();
		return subjectDto;
	}

}