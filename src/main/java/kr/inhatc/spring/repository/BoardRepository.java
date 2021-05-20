package kr.inhatc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

	Page<Board> findAllByboardTitleContaining(String boardTitle, Pageable pageable);
	Page<Board> findAllByboardContentContaining(String boardContent, Pageable pageable);
	Page<Board> findAllByboardWriterContaining(String boardWriter, Pageable pageable);
	
}