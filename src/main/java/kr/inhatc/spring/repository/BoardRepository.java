package kr.inhatc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kr.inhatc.spring.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

	Page<Board> findAllByboardContentContaining(String boardContent, Pageable pageable);
	Page<Board> findAllByboardWriterContaining(String boardWriter, Pageable pageable);
	@Modifying
    @Query("UPDATE Board p SET p.hitCnt = p.hitCnt + 1 WHERE p.boardId = :baordId")
    int updateView(int baordId);
}
