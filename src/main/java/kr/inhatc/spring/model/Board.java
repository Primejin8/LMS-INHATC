package kr.inhatc.spring.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
@Data
@Entity
//null 인 필드값이 insert 나 update 시 제외되게 하는 방법은 org.hibernate.annotations. 패키지의
//@DynamicInsert   (insert 시 null 인필드 제외)
//@DynamicUpdate (update 시 null 인필드 제외)
@DynamicInsert
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class) //jpa에 해당 entity는 auditing 기능 사용한다고 알림
public class Board {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   // 게시물 번호
   private int boardId;
   
   // 게시물 제목
   @Column(columnDefinition = "varchar(250)")
   private String boardTitle;

   //썸머노트 쓰기 위해 대용량 데이터(이미지,비디오 등 데이터 저장) 어노테이션
   @Lob	
   @Column(columnDefinition = "varchar(10000)")
   private String boardContent;

   // 좋아요
   @Column(updatable = false)
   private int goodCnt;
   
   // 조회수
   @Column(updatable = false)
   private int hitCnt;

   // 게시물 작성자
   @Column(columnDefinition = "varchar(32)")
   private String boardWriter;

   // 게시물 사용여부
   @Column(updatable = false, columnDefinition = "char(1)")
   @ColumnDefault("'Y'")
   private String useYn;

   // 게시물 작성자 시퀀스(아이디)
   @Column(columnDefinition = "varchar(32)")
   private String createSeq;
   
   // 게시물 작성일시
   @CreatedDate
   @Column(updatable = false)
   private LocalDateTime createdDate;

   // 게시물 수정자 시퀀스(아이디)
   @Column(columnDefinition = "varchar(32)")
   private String modifySeq;
   
   @LastModifiedDate
   private LocalDateTime modifiedDate;
   
   @Builder
   public Board(int boardId, String boardTitle, String boardContent, int goodCnt, int hitCnt, String boardWriter, String createSeq, String modifySeq) {
	   this.boardId = boardId;
	   this.boardTitle= boardTitle;
	   this.boardContent= boardContent;
	   this.goodCnt= goodCnt;
	   this.hitCnt= hitCnt;
	   this.boardWriter= boardWriter;
	   this.createSeq= createSeq;
	   this.modifySeq= modifySeq;
   }
}