package kr.inhatc.spring.model.week;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class WeekFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fileId;
	
	private String origFilename;
	
	private String fileName;
	
	private String filePath;
	
	@Builder
	public WeekFile(int fileId, String origFilename, String fileName, String filePath) {
		this.fileId = fileId;
		this.origFilename = origFilename;
		this.fileName = fileName;
		this.filePath = filePath;
	}
}
