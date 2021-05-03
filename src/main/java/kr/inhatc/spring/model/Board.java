package kr.inhatc.spring.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class) //jpa에 해당 entity는 auditing 기능 사용한다고 알림
public class Board {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private int board_idx;
   private String board_title;
   private String board_content;
   private int board_cnt;
   private String board_writer;
   @CreationTimestamp
   private Timestamp createDate;
   private String delete_yn;
}