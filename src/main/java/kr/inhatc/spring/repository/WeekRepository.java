package kr.inhatc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.model.week.Week;

public interface WeekRepository extends JpaRepository<Week, Integer>{
	Page<Week> findAllByweekNumberContaining(int WeekNumber, Pageable pageable);
}
