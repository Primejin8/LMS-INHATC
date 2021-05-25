package kr.inhatc.spring.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.inhatc.spring.model.LikeInfo;

public interface LikeInfoRepository extends JpaRepository<LikeInfo, Integer>{

	@Query("SELECT count(*)" + 
			"FROM LikeInfo p " +
			"WHERE p.boardId = :boardId " + 
			"AND p.empSeq = :empSeq")
	int existLikeInfo(@Param("boardId") int boardId, @Param("empSeq") int empSeq);
	@Transactional
	@Modifying
	@Query("UPDATE LikeInfo p SET p.chkYn = :chkYn WHERE p.boardId = :baordId AND p.empSeq = :empSeq")
	int updateChkYn(int baordId, int empSeq, String chkYn);
	LikeInfo findAllByBoardIdAndEmpSeq(int boardId, int empSeq);
}