package kr.inhatc.spring.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 과목번호
	private int seq;

	// 그룹시퀀스
	@Column(columnDefinition = "varchar(32)")
	private String groupSeq;

	// 회사시퀀스
	@Column(columnDefinition = "varchar(32)")
	private String compSeq;

	// 사업장시퀀스
	@Column(columnDefinition = "varchar(32)")
	private String bizSeq;

	// 이수구분
	@Column(columnDefinition = "varchar(32)")
	private String gubun;

	// 강의 명
	@Column(columnDefinition = "varchar(32)")
	private String subName;

	// 담당교수
	@Column(columnDefinition = "varchar(32)")
	private String empName;

	// 학점
	@Column(columnDefinition = "varchar(32)")
	private String hakjum;

	// 개설기간
	// 시작일자
	@Column(columnDefinition = "varchar(32)")
	private String sDate;

	// 종료일자
	@Column(columnDefinition = "varchar(32)")
	private String eDate;

	// 수강인원
	@Column(columnDefinition = "varchar(32)")
	private String stuCnt;

	// 사용여부
	@Column(updatable = false, columnDefinition = "char(1)")
	@ColumnDefault("'Y'")
	private String useYn;

	// 생성자 시퀀스(아이디)
	@Column(columnDefinition = "varchar(32)")
	private String createSeq;

	// 생성일시
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdDate;

	// 수정자 시퀀스(아이디)
	@Column(columnDefinition = "varchar(32)")
	private String modifySeq;

	// 수정일시
	@LastModifiedDate
	private LocalDateTime modifiedDate;

	@Builder
	public Subject(int seq, String groupSeq, String compSeq, String bizSeq, String gubun, String subName, String empName, String hakjum,
			String sDate, String eDate, String stuCnt, String useYn, String createSeq, LocalDateTime createdDate,
			String modifySeq, LocalDateTime modifiedDate) {
		this.seq = seq;
		this.groupSeq = groupSeq;
		this.compSeq = compSeq;
		this.bizSeq = bizSeq;
		this.gubun = gubun;
		this.subName = subName;
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