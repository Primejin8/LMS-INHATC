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
		return fileRepository.save(fileDto.toEntity()).getFile_id();
	}
	
	@Transactional
	public FileDto getFile(long file_id) {
		File file = fileRepository.findById(file_id).get();
		
		FileDto fileDto = FileDto.builder()
				.file_id(file_id)
				.origFilename(file.getOrigFilename())
				.fileName(file.getFileName())
				.filePath(file.getFilePath())
				.build();
		
		return fileDto;
	}
}
