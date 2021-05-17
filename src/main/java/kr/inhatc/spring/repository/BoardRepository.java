package kr.inhatc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

	List<Board> findByBoardTitleContaining(String keyword);

}
