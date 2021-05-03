package kr.inhatc.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.inhatc.spring.dto.BoardDto;
import kr.inhatc.spring.repository.BoardRepository;
import kr.inhatc.spring.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		List<BoardDto> list = boardService.boardList();
		model.addAttribute("postList", list);
		return "board/boardList";
	}
	@GetMapping("/boardWrite")
	public String boardWrite() {
		return "/board/boardWrite";
	}
	@GetMapping("/post")
	public String post() {
		return "board/post";
	}
//	@GetMapping("/list")
//	public String list() {
//		return "list";
//	}
}
