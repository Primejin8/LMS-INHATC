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
	
	public File toEntity() {
		File build =  File.builder()
				.fileId(fileId)
				.origFilename(origFilename)
				.fileName(fileName)
				.filePath(filePath)
				.build();
		return build;
	}
	@Builder
	public FileDto(Long fileId, String origFilename, String fileName, String filePath) {
		this.fileId= fileId;
		this.origFilename= origFilename;
		this.fileName= fileName;
		this.filePath= filePath;
	}
}
