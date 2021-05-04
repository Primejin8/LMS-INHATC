package kr.inhatc.spring.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

   private String board_title;

   @Column(columnDefinition = "text")
   private String board_content;

   @CreatedDate
   @Column(updatable = false)
   private LocalDateTime createdDate;

   @LastModifiedDate
   private LocalDateTime modifiedDate;
//   private String delete_yn;
   
   @Builder
   public Board(int board_id, String board_writer, String board_title, String board_content) {
	   this.board_id = board_id;
	   this.board_writer= board_writer;
	   this.board_title= board_title;
	   this.board_content= board_content;
	   
   }
}