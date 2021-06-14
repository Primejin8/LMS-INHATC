package kr.inhatc.spring.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import kr.inhatc.spring.model.Subject;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubjectDto {

	// 과목번호
	private int seq;

	// 그룹시퀀스
	private String groupSeq;

	// 회사시퀀스
	private String compSeq;

	// 사업장시퀀스
	private String bizSeq;

	// 이수구분
	private String gubun;
	
	// 강의 명
	private String subName;

	// 담당교수
	private String empName;

	// 학점
	private String hakjum;

	// 개설기간
	// 시작일자
	private Date sDate;

	// 종료일자
	private Date eDate;

	// 수강인원
	private String stuCnt;

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
	public Subject toEntity() {
		// Board에 @Builder
		Subject build = Subject.builder()
				.seq(seq)
				.groupSeq(groupSeq)
				.compSeq(compSeq)
				.bizSeq(bizSeq)
				.gubun(gubun)
				.subName(subName)
				.empName(empName)
				.hakjum(hakjum)
				.sDate(sDate)
				.eDate(eDate)
				.stuCnt(stuCnt)
				.createSeq(createSeq)
				.modifySeq(modifySeq).build();
		return build;
	}

	@Builder
	public SubjectDto(int seq, String groupSeq, String compSeq, String bizSeq, String gubun, String subName, String empName,
			String hakjum, Date sDate, Date eDate, String stuCnt, String useYn, String createSeq,
			LocalDateTime createdDate, String modifySeq, LocalDateTime modifiedDate) {
		this.seq = seq;
		this.groupSeq = groupSeq;
		this.compSeq = compSeq;
		this.bizSeq = bizSeq;
		this.gubun = gubun;
		this.empName = empName;
		this.hakjum = hakjum;
		this.sDate = sDate;
		this.eDate = eDate;
		this.stuCnt = stuCnt;
		this.useYn = useYn;
		this.createSeq = createSeq;
		this.modifySeq = modifySeq;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
}