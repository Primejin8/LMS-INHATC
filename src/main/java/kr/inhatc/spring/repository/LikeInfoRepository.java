package kr.inhatc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.model.LikeInfo;

public interface LikeInfoRepository extends JpaRepository<LikeInfo, Integer>{

}