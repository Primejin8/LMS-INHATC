package kr.inhatc.spring.dto;

import java.time.LocalDateTime;

import kr.inhatc.spring.model.Comment;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
	private int commentId;
	private String commentWriter;
	private String commentContent;
	private LocalDateTime createDate;
	private LocalDateTime modifiedDate;
	private int boardId;
	
	public Comment toEntity() {
		Comment build = Comment.builder()
				.commentId(commentId)
				.commentWriter(commentWriter)
				.commentConntent(commentContent)
				.boardId(boardId)
				.build();
		return build;
	}
	
	@Builder
	public CommentDto(int commentId, String commentWriter, String commentConntent, LocalDateTime createDate, LocalDateTime modifiedDate, int boardId) {
		this.commentId = commentId;
		this.commentWriter = commentWriter;
		this.commentContent = commentConntent;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.boardId = boardId;
	}
}
