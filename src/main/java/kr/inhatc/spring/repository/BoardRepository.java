package kr.inhatc.spring.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kr.inhatc.spring.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

	Page<Board> findAllByboardTitleContaining(String boardContent, Pageable pageable);
	Page<Board> findAllByboardContentContaining(String boardContent, Pageable pageable);
	Page<Board> findAllByboardWriterContaining(String boardWriter, Pageable pageable);
	@Transactional
	@Modifying
    @Query("UPDATE Board p SET p.hitCnt = p.hitCnt + 1 WHERE p.boardId = :baordId")
    int updateView(int baordId);
	
	@Transactional
	@Modifying
	@Query("UPDATE Board p SET p.goodCnt = p.goodCnt + 1 WHERE p.boardId = :baordId")
	int updatePlusGoodCnt(int baordId);
	
	@Transactional
	@Modifying
	@Query("UPDATE Board p SET p.goodCnt = p.goodCnt - 1 WHERE p.boardId = :baordId")
	int updateMinusGoodCnt(int baordId);

}
