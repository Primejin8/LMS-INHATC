package kr.inhatc.spring.dto;

import kr.inhatc.spring.model.File;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileDto {

	private long fileId;
	
	private String origFilename;
	
	private String fileName;
	
	private String filePath;
	
	private int boardId;
	
	public File toEntity() {
		File build =  File.builder()
				.fileId(fileId)
				.origFilename(origFilename)
				.fileName(fileName)
				.filePath(filePath)
				.boardId(boardId)
				.build();
		return build;
	}
	@Builder
	public FileDto(Long fileId, String origFilename, String fileName, String filePath, int boardId) {
		this.fileId= fileId;
		this.origFilename= origFilename;
		this.fileName= fileName;
		this.filePath= filePath;
		this.boardId= boardId;
	}
}
