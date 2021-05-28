package kr.inhatc.spring.dto;

import java.time.LocalDateTime;

import kr.inhatc.spring.model.LikeInfo;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeInfoDto {

	private int seq;
	private int boardId;
	private int empSeq;
	private String chkYn;
	private String useYn;
	private String createSeq;
	private LocalDateTime createdDate;
	private String modifySeq;
	private LocalDateTime modifiedDate;

	// DTO에서 필요한 부분을 빌더 패턴을 통해 Entity로 만드는 역할
	public LikeInfo toEntity() {
		LikeInfo build = LikeInfo.builder()
				.seq(seq)
				.boardId(boardId)
				.empSeq(empSeq)
				.chkYn(chkYn)
				.useYn(useYn)
				.createSeq(createSeq)
				.modifySeq(modifySeq)
				.build();
		return build;
	}

	@Builder
	public LikeInfoDto(int seq, int boardId, int empSeq,
			String chkYn, 
			String useYn,
			String createSeq,
			LocalDateTime createdDate,
			String modifySeq,
			LocalDateTime modifiedDate) {
		this.seq = seq;
		this.boardId = boardId;
		this.empSeq = empSeq;
		this.chkYn = chkYn;
		this.useYn = useYn;
		this.createSeq = createSeq;
		this.modifySeq = modifySeq;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
}