package kr.inhatc.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kr.inhatc.spring.model.File;

public interface FileRepository extends JpaRepository<File, Long> {

	
	
	@Modifying
    @Query("UPDATE Board p SET p.hitCnt = p.hitCnt + 1 WHERE p.boardId = :baordId")
    int updateView(int baordId);
	@Modifying
	@Query("UPDATE Board p SET p.goodCnt = p.goodCnt + 1 WHERE p.boardId = :baordId")
	int updateGoodCnt(int baordId);
	
}