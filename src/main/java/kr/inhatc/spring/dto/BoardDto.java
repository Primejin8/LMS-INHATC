package kr.inhatc.spring.dto;

import java.sql.Timestamp;

import kr.inhatc.spring.model.Board;
import lombok.Data;

@Data
public class BoardDto {
	 private int board_idx;
	   private String board_title;
	   private String board_content;
	   private int board_cnt;
	   private String board_writer;
	   private Timestamp createDate;
	   private String delete_yn;
	   
	   
//	   public Board toEntity() {
//		   Board build = Board.builder()
//				   .idx(idx)
//				   .
//	   }
}
