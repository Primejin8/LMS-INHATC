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
	 private int boardId;
	   private String boardTitle;
	   private String boardWriter;
	   private String boardContent;
	   private Long fileId;
	   private LocalDateTime createdDate;
	   private LocalDateTime modifiedDate;
//	   private String delete_yn;
	   
	   //DTO에서 필요한 부분을 빌더 패턴을 통해 Entity로 만드는 역할
	   public Board toEntity() {
		   Board build = Board.builder()	//Board에 @Builder
				   .boardId(boardId)
				   .boardTitle(boardTitle)
				   .boardContent(boardContent)
				   .boardWriter(boardWriter)
				   .fileId(fileId)
				   .build();
		   return build;
	   }
	   @Builder
	   public BoardDto(int boardId, String boardWriter, String boardTitle, String boardContent, Long fileId, LocalDateTime createdDate, LocalDateTime modifiedDate) {
		   this.boardId= boardId;
		   this.boardWriter= boardWriter;
		   this.boardTitle= boardTitle;
		   this.boardContent= boardContent;
		   this.fileId= fileId;
		   this.createdDate= createdDate;
		   this.modifiedDate= modifiedDate;
	   }
}
