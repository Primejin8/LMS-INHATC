package kr.inhatc.spring.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.inhatc.spring.dto.BoardDto;
import kr.inhatc.spring.dto.FileDto;
import kr.inhatc.spring.model.Board;
import kr.inhatc.spring.repository.BoardRepository;
import kr.inhatc.spring.service.BoardService;
import kr.inhatc.spring.service.FileService;
import kr.inhatc.spring.util.MD5Generator;

@Controller
public class BoardController {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private FileService fileService;

	
//	없어도 되는 생성자?
//	public BoardController(BoardService boardService) {
//		this.boardService = boardService;
//	}
	
	//Model 객체를 이용하여 데이터를 가져오고 View에 데이터를 넘겨줌
	@GetMapping("/boardList")
	public String boardList(Model model, @PageableDefault(size=2) Pageable pageable) {
		List<BoardDto> boardDtoList = boardService.getBoardList(pageable);

		Page<Board> boardList = boardRepository.findAll(pageable);
		int startPage= Math.max(1,boardList.getPageable().getPageNumber() -4);
		int endPage= Math.min(boardList.getTotalPages(),boardList.getPageable().getPageNumber() +4);
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("postList", boardDtoList);	//(key, value)형태로 view에 전달
		return "board/boardList";
	}
	@GetMapping("/post")
	public String post() {
		return "board/post";
	}
	@PostMapping("/post")
	public String write(@RequestParam("file") MultipartFile files, BoardDto boardDto) {
        try {
            String origFilename = files.getOriginalFilename();
            String fileName = new MD5Generator(origFilename).toString();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try{
                    new File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + fileName;
            files.transferTo(new File(filePath));

            FileDto fileDto = new FileDto();
            fileDto.setOrigFilename(origFilename);
            fileDto.setFileName(fileName);
            fileDto.setFilePath(filePath);

            Long file_id = fileService.saveFile(fileDto);
            boardDto.setFile_id(file_id);
            boardService.savePost(boardDto);
        } catch(Exception e) {
            e.printStackTrace();
        }
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
	@GetMapping("/search")
	public String search(@RequestParam(value="keyword") String keyword, Model model) {
	    List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

	    model.addAttribute("boardList", boardDtoList);

	    return "board/boardList";
	}
	
	
//	@GetMapping("/download/{file_id}")
//	public ResponseEntity<Resource> fileDownload(@PathVariable("file_id") Long file_id) throws IOException {
//	    FileDto fileDto = fileService.getFile(file_id);
//	    Path path = Paths.get(fileDto.getFilePath());
//	    Resource resource = new InputStreamResource(Files.newInputStream(path));
//	    return ResponseEntity.ok()
//	            .contentType(MediaType.parseMediaType("application/octet-stream"))
//	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
//	            .body(resource);
//	}
}
