package kr.inhatc.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<BoardDto> getBoardList() {
		List<Board> boardList = boardRepository.findAll();
		List<BoardDto> boardDtoList = new ArrayList<>();
		
		for(Board board : boardList) {
			BoardDto boardDto = BoardDto.builder()
					.board_id(board.getBoard_id())
					.board_title(board.getBoard_title())
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
				.board_title(board.getBoard_title())
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
}
