package kr.inhatc.spring.dto;

import java.time.LocalDateTime;

import kr.inhatc.spring.model.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@ToString
@Data
@NoArgsConstructor
public class BoardDto {
	private int boardId;
	private String boardTitle;
	private String boardContent;
	private int goodCnt;
	private int hitCnt;
	private String boardWriter;
	private String useYn;
	private String createSeq;
	private LocalDateTime createdDate;
	private String modifySeq;
	private LocalDateTime modifiedDate;
//	   private String delete_yn;

	// DTO에서 필요한 부분을 빌더 패턴을 통해 Entity로 만드는 역할
	public Board toEntity() {
		Board build = Board.builder() // Board에 @Builder
				.boardId(boardId)
				.boardTitle(boardTitle)
				.boardContent(boardContent)
				.boardWriter(boardWriter)
				.goodCnt(goodCnt)
				.hitCnt(hitCnt)
				.createSeq(createSeq)
				.modifySeq(modifySeq)
				.build();
		return build;
	}

	@Builder
	public BoardDto(int boardId, String boardTitle, String boardContent, int goodCnt, int hitCnt, String boardWriter
			,String useYn, String createSeq, LocalDateTime createdDate, String modifySeq, LocalDateTime modifiedDate) {
		this.boardId = boardId;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.goodCnt = goodCnt;
		this.hitCnt = hitCnt;
		this.boardWriter = boardWriter;
		this.useYn = useYn;
		this.createSeq = createSeq;
		this.modifySeq = modifySeq;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
}