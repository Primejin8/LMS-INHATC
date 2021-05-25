package kr.inhatc.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.dto.LikeInfoDto;
import kr.inhatc.spring.repository.LikeInfoRepository;

@Service
public class LikeInfoService {
	
	@Autowired
	private LikeInfoRepository likeInfoRepository;
	
	public LikeInfoService(LikeInfoRepository likeInfoRepository) {
		this.likeInfoRepository = likeInfoRepository;
	}
	
	@Transactional
	public int saveLike (LikeInfoDto likeInfoDto) {
		return likeInfoRepository.save(likeInfoDto.toEntity()).getSeq();
	}
	
}