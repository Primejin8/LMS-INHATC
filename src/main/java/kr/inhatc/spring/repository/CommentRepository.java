package kr.inhatc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	Page<Comment> findAllBycommentIdContaining(String commentId, Pageable pageable);
}
