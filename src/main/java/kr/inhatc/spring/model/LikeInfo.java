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
public class LikeInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 좋아요 번호
	private int seq;

	// 게시물 번호
	private int boardId;

	// 회원 시퀀스(번호)
	private int empSeq;

	// 종아요 버튼 여부
	@Column(updatable = false, columnDefinition = "char(1)")
	@ColumnDefault("'N'")
	private String chkYn;

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
	public LikeInfo(int seq, int boardId, int empSeq, String chkYn, String useYn, String createSeq, String modifySeq) {
		this.seq = seq;
		this.boardId = boardId;
		this.empSeq = empSeq;
		this.chkYn = chkYn;
		this.useYn = useYn;
		this.createSeq = createSeq;
		this.modifySeq = modifySeq;
	}

}