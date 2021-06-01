package kr.inhatc.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.dto.FileDto;
import kr.inhatc.spring.model.File;
import kr.inhatc.spring.repository.FileRepository;

@Service
public class FileService {
	@Autowired
	private FileRepository fileRepository;

	@Transactional
	public Long saveFile(FileDto fileDto) {
		return fileRepository.save(fileDto.toEntity()).getFileId();
	}
	
	@Transactional
	public FileDto getFile(long fileId) {
		File file = fileRepository.findById(fileId).get();
		
		FileDto fileDto = FileDto.builder()
				.fileId(fileId)
				.origFilename(file.getOrigFilename())
				.fileName(file.getFileName())
				.filePath(file.getFilePath())
				.boardId(file.getBoardId())
				.build();
		
		return fileDto;
	}
	
}
