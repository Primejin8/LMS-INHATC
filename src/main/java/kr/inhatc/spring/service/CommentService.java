package kr.inhatc.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.dto.CommentDto;
import kr.inhatc.spring.model.Comment;
import kr.inhatc.spring.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	@Transactional
	public int saveComment(CommentDto commentDto) {
		return commentRepository.save(commentDto.toEntity()).getCommentId();
	}
	@Transactional
	public List<CommentDto> getCommentList(){
		List<Comment> commentList = commentRepository.findAll();
		List<CommentDto> commentDtoList = new ArrayList<>();
		
		for(Comment comment : commentList) {
			CommentDto commentDto = CommentDto.builder()
					.commentId(comment.getCommentId())
					.commentWriter(comment.getCommentWriter())
					.commentConntent(comment.getCommentContent())
					.createDate(comment.getCreateDate())
					.modifiedDate(comment.getModifiedDate())
					.boardId(comment.getBoardId())
					.build();
			commentDtoList.add(commentDto);
		}
		return commentDtoList;
	}
	
	@Transactional
	public CommentDto getComment(int commentId) {
		Comment comment = commentRepository.findById(commentId).get();
		
		CommentDto commentDto = CommentDto.builder()
				.commentId(commentId)
				.commentWriter(comment.getCommentWriter())
				.commentConntent(comment.getCommentWriter())
				.createDate(comment.getCreateDate())
				.modifiedDate(comment.getModifiedDate())
				.boardId(comment.getBoardId())
				.build();
		
		return commentDto;
	}
	
	// 댓글 삭제
	@Transactional
	public void deleteComment(int commentId) {
		commentRepository.deleteById(commentId);
	}
	
}
