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
		return boardRepository.save(boardDto.toEntity()).getBoardId();
	}
	
	@Transactional
	public List<BoardDto> getBoardList() {
		List<Board> boardList = boardRepository.findAll();
		List<BoardDto> boardDtoList = new ArrayList<>();
		
		for(Board board : boardList) {
			BoardDto boardDto = BoardDto.builder()
					.boardId(board.getBoardId())
					.boardTitle(board.getBoardTitle())
					.boardContent(board.getBoardContent())
					.hitCnt(board.getHitCnt())
					.boardWriter(board.getBoardWriter())
					.createdDate(board.getCreatedDate())
					.build();
			boardDtoList.add(boardDto);
		}
		
		return boardDtoList;
	}
	
	@Transactional
	public BoardDto getPost(int boardId) {
		Board board = boardRepository.findById(boardId).get();
		
		BoardDto boardDto = BoardDto.builder()
				.boardId(board.getBoardId())
				.boardWriter(board.getBoardWriter())
				.boardTitle(board.getBoardTitle())
				.fileId(board.getFileId())
				.boardContent(board.getBoardContent())
				.hitCnt(board.getHitCnt())
				.createdDate(board.getCreatedDate())
				.build();
		return boardDto;
	}
	//삭제버튼 누르면 /post/{board_id}로 delete 요청하도록 deletePost()추가
	@Transactional
	public void deltePost(int boardId) {
		boardRepository.deleteById(boardId);
	}
	
	// 조회수 업데이트
	@Transactional
    public int updateView(int boardId) {
        return boardRepository.updateView(boardId);
    }
}