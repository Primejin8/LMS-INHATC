package kr.inhatc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
