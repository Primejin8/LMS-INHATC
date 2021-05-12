package kr.inhatc.spring.dto;

import kr.inhatc.spring.model.File;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileDto {

	private long file_id;
	private String origFilename;
	private String fileName;
	private String filePath;
	
	public File toEntity() {
		File build =  File.builder()
				.file_id(file_id)
				.origFilename(origFilename)
				.fileName(fileName)
				.filePath(filePath)
				.build();
		return build;
	}
	@Builder
	public FileDto(Long file_id, String origFilename, String fileName, String filePath) {
		this.file_id= file_id;
		this.origFilename= origFilename;
		this.fileName= fileName;
		this.filePath= filePath;
	}
}
