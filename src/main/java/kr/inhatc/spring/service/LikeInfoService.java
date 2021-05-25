package kr.inhatc.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.dto.LikeInfoDto;
import kr.inhatc.spring.model.LikeInfo;
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
	
	@Transactional
	public LikeInfoDto getLikeInfo(int boardId, int empSeq) {
		LikeInfo like = likeInfoRepository.findAllByBoardIdAndEmpSeq(boardId, empSeq);
		
		LikeInfoDto likeDTO = LikeInfoDto.builder()
				.boardId(like.getBoardId())
				.empSeq(like.getEmpSeq())
				.chkYn(like.getChkYn())
				.useYn(like.getUseYn())
				.createSeq(like.getCreateSeq())
				.createdDate(like.getCreatedDate())
				.modifySeq(like.getModifySeq())
				.modifiedDate(like.getModifiedDate())
				.build();
		return likeDTO;
	}
	
}