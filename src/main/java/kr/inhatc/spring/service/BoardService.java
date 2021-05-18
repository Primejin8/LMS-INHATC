package kr.inhatc.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.dto.BoardDto;
import kr.inhatc.spring.model.Board;
import kr.inhatc.spring.repository.BoardRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	@Transactional
	public int savePost (BoardDto boardDto) {
		return boardRepository.save(boardDto.toEntity()).getBoard_id();
	}
	
	@Transactional
	public List<BoardDto> getBoardList(Pageable pageable) {
		List<Board> boardList = boardRepository.findAll();
		List<BoardDto> boardDtoList = new ArrayList<>();
		
		for(Board board : boardList) {
			BoardDto boardDto = BoardDto.builder()
					.board_id(board.getBoard_id())
					.boardTitle(board.getBoardTitle())
					.board_content(board.getBoard_content())
					.board_writer(board.getBoard_writer())
					.createdDate(board.getCreatedDate())
					.build();
			boardDtoList.add(boardDto);
		}
		
		return boardDtoList;
	}
	
	@Transactional
	public BoardDto getPost(int board_id) {
		Board board = boardRepository.findById(board_id).get();
		
		BoardDto boardDto = BoardDto.builder()
				.board_id(board.getBoard_id())
				.board_writer(board.getBoard_writer())
				.boardTitle(board.getBoardTitle())
				.file_id(board.getFile_id())
				.board_content(board.getBoard_content())
				.createdDate(board.getCreatedDate())
				.build();
		return boardDto;
	}
	//삭제버튼 누르면 /post/{board_id}로 delete 요청하도록 deletePost()추가
	@Transactional
	public void deltePost(int board_id) {
		boardRepository.deleteById(board_id);
	}
	
	@Transactional
	public List<BoardDto> searchPosts(String keyword) {
	    List<Board> boardEntities = boardRepository.findByBoardTitleContaining(keyword);
	    List<BoardDto> boardDtoList = new ArrayList<>();

	    if (boardEntities.isEmpty()) return boardDtoList;

	    for (Board boardEntity : boardEntities) {
	        boardDtoList.add(this.convertEntityToDto(boardEntity));
	    }

	    return boardDtoList;
	}
	
	private BoardDto convertEntityToDto(Board boardEntity) {
	    return BoardDto.builder()
	            .board_id(boardEntity.getBoard_id())
	            .boardTitle(boardEntity.getBoardTitle())
	            .board_content(boardEntity.getBoard_content())
	            .board_writer(boardEntity.getBoard_writer())
	            .createdDate(boardEntity.getCreatedDate())
	            .build();
	}
	
}
