package kr.inhatc.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access =AccessLevel.PROTECTED)
public class File {
	
	@Id
	@GeneratedValue
	private long fileId;
	
	@Column(nullable = false)
	private String origFilename;

	@Column(nullable = false)
	private String fileName;

	@Column(nullable = false)
	private String filePath;

	private int boardId;
	
	@Builder
	public File(long fileId, String origFilename, String fileName, String filePath, int boardId) {
		this.fileId= fileId;
		this.origFilename= origFilename;
		this.fileName= fileName;
		this.filePath= filePath;
		this.boardId= boardId;
	}
}
