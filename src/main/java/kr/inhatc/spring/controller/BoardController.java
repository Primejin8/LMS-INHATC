package kr.inhatc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.inhatc.spring.dto.BoardDto;
import kr.inhatc.spring.model.Board;
import kr.inhatc.spring.repository.BoardRepository;
import kr.inhatc.spring.service.BoardService;

@Controller
@SessionAttributes("sessionId")
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
	public String boardList(Model model, @PageableDefault(size = 10, sort = "boardId", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(required = false, defaultValue = "all") String searchType, @RequestParam(required = false, defaultValue = "") String searchText) {
		//Page<Board> boardDtoList = boardRepository.findAll(pageable);
		Page<Board> boardDtoList = null;
		if(searchType.equals("all")) {
			if(!searchText.equals("")) {
				boardDtoList = boardRepository.findAllByboardTitleContaining(searchText, pageable);
			}else {
				boardDtoList = boardRepository.findAll(pageable);
			}
		} else if(searchType.equals("title")) {
			boardDtoList = boardRepository.findAllByboardTitleContaining(searchText, pageable);
		} else if(searchType.equals("contents")) {
			boardDtoList = boardRepository.findAllByboardContentContaining(searchText, pageable);
		} else if(searchType.equals("writer")){
			boardDtoList = boardRepository.findAllByboardWriterContaining(searchText, pageable);
		} else {
			boardDtoList = boardRepository.findAll(pageable);
		}
		
		int startPage = Math.max(1, boardDtoList.getPageable().getPageNumber() - 4);
		int endPage = Math.min(boardDtoList.getTotalPages(), boardDtoList.getPageable().getPageNumber() + 4);
		
		System.out.println("searchType === >" + searchType);
		System.out.println("searchText === >" + searchText);
		
		model.addAttribute("startPage", startPage);	//(key, value)형태로 view에 전달
		model.addAttribute("endPage", endPage);	//(key, value)형태로 view에 전달
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
	@GetMapping("/post/{boardId}")
	public String detail(@PathVariable("boardId") int boardId, Model model) {
		BoardDto boardDto = boardService.getPost(boardId);
		boardService.savePost(boardDto);
		boardService.updateView(boardDto.getBoardId());
		model.addAttribute("post",boardDto);
		return "board/detail";
	}
	//수정하는 창 매핑
	@GetMapping("/post/edit/{boardId}")
	public String edit(@PathVariable("boardId") int boardId, Model model) {
		BoardDto boardDto = boardService.getPost(boardId);
		model.addAttribute("post", boardDto);
		return "board/edit";
	}
	//수정버튼을 누르면 put형식으로 서버에게 /post/edit/{id} 요청이 가게됨
	@PutMapping("/post/edit/{boardId}")
	public String update(BoardDto boardDto) {
		boardService.savePost(boardDto);
		return "redirect:/boardList";
	}
	//삭제
	@DeleteMapping("/post/{boardId}")
	public String delete(@PathVariable("boardId") int boardId) {
		boardService.deltePost(boardId);
		return "redirect:/boardList";
	}
}