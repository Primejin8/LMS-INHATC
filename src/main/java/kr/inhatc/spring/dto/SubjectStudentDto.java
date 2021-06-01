package kr.inhatc.spring.dto;

import java.time.LocalDateTime;

import kr.inhatc.spring.model.SubjectStudent;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubjectStudentDto {

	// 과목번호
	private int seq;

	// 그룹시퀀스
	private String groupSeq;

	// 회사시퀀스
	private String compSeq;

	// 사업장시퀀스
	private String bizSeq;
	
	// 사용자시퀀스
	private String empSeq;
	
	// 사용자이름 (나중에 빼야됌)
	private String empName;
	
	// 강의 시퀀스
	private String subSeq;
	
	// 강의 이름
	private String subName;

	// 사용여부
	private String useYn;

	// 생정자 시퀀스
	private String createSeq;

	// 생성일자
	private LocalDateTime createdDate;

	// 수정자 시퀀스
	private String modifySeq;

	// 수정일자
	private LocalDateTime modifiedDate;

	// DTO에서 필요한 부분을 빌더 패턴을 통해 Entity로 만드는 역할
	public SubjectStudent toEntity() {
		// Board에 @Builder
		SubjectStudent build = SubjectStudent.builder()
				.seq(seq)
				.groupSeq(groupSeq)
				.compSeq(compSeq)
				.bizSeq(bizSeq)
				.empSeq(empSeq)
				.empName(empName)
				.subName(subName)
				.subSeq(subSeq)
				.createSeq(createSeq)
				.modifySeq(modifySeq).build();
		return build;
	}

	@Builder
	public SubjectStudentDto(int seq, String groupSeq, String compSeq, String bizSeq, String subSeq, String subName,
			String useYn, String createSeq, LocalDateTime createdDate, String modifySeq, LocalDateTime modifiedDate) {
		this.seq = seq;
		this.groupSeq = groupSeq;
		this.compSeq = compSeq;
		this.bizSeq = bizSeq;
		this.subSeq = subSeq;
		this.empSeq = empSeq;
		this.empName = empName;
		this.subSeq = subSeq;
		this.subName = subName;
		this.useYn = useYn;
		this.createSeq = createSeq;
		this.modifySeq = modifySeq;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
}