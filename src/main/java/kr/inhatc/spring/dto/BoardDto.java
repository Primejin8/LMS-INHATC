package kr.inhatc.spring.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import kr.inhatc.spring.model.Board;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@ToString
@Data
@NoArgsConstructor
public class BoardDto {
	 private int board_id;
	   private String boardTitle;
	   private String board_writer;
	   private String board_content;
	   private Long file_id;
	   private LocalDateTime createdDate;
	   private LocalDateTime modifiedDate;
//	   private String delete_yn;
	   
	   //DTO에서 필요한 부분을 빌더 패턴을 통해 Entity로 만드는 역할
	   public Board toEntity() {
		   Board build = Board.builder()	//Board에 @Builder
				   .board_id(board_id)
				   .boardTitle(boardTitle)
				   .board_content(board_content)
				   .board_writer(board_writer)
				   .file_id(file_id)
				   .build();
		   return build;
	   }
	   @Builder
	   public BoardDto(int board_id, String board_writer, String boardTitle, String board_content, Long file_id, LocalDateTime createdDate, LocalDateTime modifiedDate) {
		   this.board_id= board_id;
		   this.board_writer= board_writer;
		   this.boardTitle= boardTitle;
		   this.board_content= board_content;
		   this.file_id= file_id;
		   this.createdDate= createdDate;
		   this.modifiedDate= modifiedDate;
	   }
}
