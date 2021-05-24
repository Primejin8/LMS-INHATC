package kr.inhatc.spring.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
@Data
@Entity
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class) //jpa에 해당 entity는 auditing 기능 사용한다고 알림
public class Board {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int boardId;
   
   private String boardWriter;

   @NotNull
   private String boardTitle;

   @Lob	//썸머노트 쓰기 위해 대용량 데이터(이미지,비디오 등 데이터 저장) 어노테이션
   private String boardContent;

   private Long fileId;

   private int hitCnt;
   
   @CreatedDate
   @Column(updatable = false)
   private LocalDateTime createdDate;

   @LastModifiedDate
   private LocalDateTime modifiedDate;
   
   @Builder
   public Board(int boardId, String boardWriter, String boardTitle, String boardContent, Long fileId, int hitCnt) {
	   this.boardId = boardId;
	   this.boardWriter= boardWriter;
	   this.boardTitle= boardTitle;
	   this.boardContent= boardContent;
	   this.fileId= fileId;
	   this.hitCnt= hitCnt;
   }
}