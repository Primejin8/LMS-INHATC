package kr.inhatc.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import kr.inhatc.spring.dto.BoardDto;
import kr.inhatc.spring.repository.BoardRepository;
import kr.inhatc.spring.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardService boardService;
	
//	없어도 되는 생성자?
//	public BoardController(BoardService boardService) {
//		this.boardService = boardService;
//	}
	
	//Model 객체를 이용하여 데이터를 가져오고 View에 데이터를 넘겨줌
	@GetMapping("/boardList")
	public String boardList(Model model) {
		List<BoardDto> boardDtoList = boardService.getBoardList();
		model.addAttribute("postList", boardDtoList);	//(key, value)형태로 view에 전달
		return "board/boardList";
	}
	@GetMapping("/post")
	public String post() {
		return "board/post";
	}
	@PostMapping("/post")
	public String write(BoardDto boardDto) {
		boardService.savePost(boardDto);
		return "redirect:/boardList";
	}
	//글 상세보기 창 매핑
	//URL경로에 변수를 넘겨주는 역할 Pathvariable 
	@GetMapping("/post/{board_id}")
	public String detail(@PathVariable("board_id") int board_id, Model model) {
		BoardDto boardDto = boardService.getPost(board_id);
		model.addAttribute("post",boardDto);
		return "board/detail";
	}
	//수정하는 창 매핑
	@GetMapping("/post/edit/{board_id}")
	public String edit(@PathVariable("board_id") int board_id, Model model) {
		BoardDto boardDto = boardService.getPost(board_id);
		model.addAttribute("post", boardDto);
		return "board/edit";
	}
	//수정버튼을 누르면 put형식으로 서버에게 /post/edit/{id} 요청이 가게됨
	@PutMapping("/post/edit/{board_id}")
	public String update(BoardDto boardDto) {
		boardService.savePost(boardDto);
		return "redirect:/boardList";
	}
	//삭제
	@DeleteMapping("/post/{board_id}")
	public String delete(@PathVariable("board_id") int board_id) {
		boardService.deltePost(board_id);
		return "redirect:/boardList";
	}
}
