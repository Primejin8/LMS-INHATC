package kr.inhatc.spring.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Getter
@Data
@Entity
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class) //jpa에 해당 entity는 auditing 기능 사용한다고 알림
public class Board {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int board_id;
   
   private String board_writer;

   @NotNull
   private String boardTitle;

   @Lob	//썸머노트 쓰기 위해 대용량 데이터(이미지,비디오 등 데이터 저장) 어노테이션
   private String board_content;

   private Long file_id;
   
   @CreatedDate
   @Column(updatable = false)
   private LocalDateTime createdDate;

   @LastModifiedDate
   private LocalDateTime modifiedDate;
//   private String delete_yn;
   
   @Builder
   public Board(int board_id, String board_writer, String boardTitle, String board_content,Long file_id) {
	   this.board_id = board_id;
	   this.board_writer= board_writer;
	   this.boardTitle= boardTitle;
	   this.board_content= board_content;
	   this.file_id= file_id;
   }
}