package kr.inhatc.spring.dto;

import kr.inhatc.spring.model.File;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileDto {

	private long fileId;
	private String origFileName;
	private String fileName;
	private String filePath;
	
	public File toEntity() {
		File build =  File.builder()
				.fileId(fileId)
				.origFileName(origFileName)
				.fileName(fileName)
				.filePath(filePath)
				.build();
		return build;
	}
	@Builder
	public FileDto(Long fileId, String origFileName, String fileName, String filePath) {
		this.fileId= fileId;
		this.origFileName= origFileName;
		this.fileName= fileName;
		this.filePath= filePath;
	}
}
