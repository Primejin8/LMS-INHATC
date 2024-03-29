package kr.inhatc.spring.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.inhatc.spring.dto.BoardDto;
import kr.inhatc.spring.dto.CommentDto;
import kr.inhatc.spring.dto.FileDto;

import org.springframework.web.bind.annotation.ResponseBody;

import kr.inhatc.spring.dto.LikeInfoDto;
import kr.inhatc.spring.model.Board;
import kr.inhatc.spring.model.Comment;
import kr.inhatc.spring.repository.BoardRepository;
import kr.inhatc.spring.repository.CommentRepository;
import kr.inhatc.spring.repository.LikeInfoRepository;
import kr.inhatc.spring.service.BoardService;
import kr.inhatc.spring.service.CommentService;
import kr.inhatc.spring.service.FileService;
import kr.inhatc.spring.util.MD5Generator;
import kr.inhatc.spring.service.LikeInfoService;

@Controller
public class BoardController {

	private Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private LikeInfoRepository likeInfoRepository;
	@Autowired 
	private CommentRepository commentRepository;

	@Autowired
	private BoardService boardService;
	@Autowired
	private FileService fileService;
	@Autowired
	private LikeInfoService likeInfoService;
	@Autowired
	private CommentService commentService;
//	없어도 되는 생성자?
//	public BoardController(BoardService boardService) {
//		this.boardService = boardService;
//	}

	public BoardController() {
		logger.info("############### Create BoardController ###############");
	}

	// Model 객체를 이용하여 데이터를 가져오고 View에 데이터를 넘겨줌
	@GetMapping("/boardList")
	public String boardList(Model model,
			@PageableDefault(size = 10, sort = "boardId", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(required = false, defaultValue = "all") String searchType,
			@RequestParam(required = false, defaultValue = "") String searchText) {
		Page<Board> boardDtoList = null;
		if (searchType.equals("all")) {
			if (!searchText.equals("")) {
				boardDtoList = boardRepository.findAllByboardTitleContaining(searchText, pageable);
			} else {
				boardDtoList = boardRepository.findAll(pageable);
			}
		} else if (searchType.equals("title")) {
			boardDtoList = boardRepository.findAllByboardTitleContaining(searchText, pageable);
		} else if (searchType.equals("contents")) {
			boardDtoList = boardRepository.findAllByboardContentContaining(searchText, pageable);
		} else if (searchType.equals("writer")) {
			boardDtoList = boardRepository.findAllByboardWriterContaining(searchText, pageable);
		} else {
			boardDtoList = boardRepository.findAll(pageable);
		}

		int startPage = Math.max(1, boardDtoList.getPageable().getPageNumber() - 4);
		int endPage = Math.min(boardDtoList.getTotalPages(), boardDtoList.getPageable().getPageNumber() + 4);

		System.out.println("searchType === >" + searchType);
		System.out.println("searchText === >" + searchText);

		model.addAttribute("startPage", startPage); // (key, value)형태로 view에 전달
		model.addAttribute("endPage", endPage); // (key, value)형태로 view에 전달
		model.addAttribute("postList", boardDtoList); // (key, value)형태로 view에 전달
		return "board/boardList";
	}

	@GetMapping("/post")
	public String post() {
		return "board/post";
	}

	@PostMapping("/post")
	public String write(MultipartHttpServletRequest request, BoardDto boardDto) throws Exception {
		List<MultipartFile> files = request.getFiles("file");

		for (MultipartFile mf : files) {
			String origFilename = mf.getOriginalFilename();
			String fileName = new MD5Generator(origFilename).toString();
			String savePath = System.getProperty("user.dir") + "\\files";

			try {
				if (!new File(savePath).exists()) {
					try {
						new File(savePath).mkdir();
					} catch (Exception e) {
						e.getStackTrace();
					}
				}
				String filePath = savePath + "\\" + fileName;
				mf.transferTo(new File(filePath));

				FileDto fileDto = new FileDto();
				fileDto.setOrigFilename(origFilename);
				fileDto.setFileName(fileName);
				fileDto.setFilePath(filePath);

				Long fileId = fileService.saveFile(fileDto);
				boardDto.setFileId(fileId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		boardService.savePost(boardDto);
		return "redirect:/boardList";
	}


	// 글 상세보기 창 매핑
	// URL경로에 변수를 넘겨주는 역할 Pathvariable
	@GetMapping("/post/{boardId}")
	public String detail(@PathVariable("boardId") int boardId, Model model,
			@PageableDefault(size=10, sort="commentId", direction = Sort.Direction.ASC) Pageable pageable) { // 댓글 10개씩 보여주기
		BoardDto boardDto = boardService.getPost(boardId);
		FileDto fileDto = fileService.getFile(boardDto.getFileId());
		Page<Comment> commentList = null; // 댓글 리스트 불러오기
		
		commentList = commentRepository.findAll(pageable);
		
		boardService.savePost(boardDto);
		boardService.updateView(boardDto.getBoardId());
		
		model.addAttribute("post", boardDto);
		model.addAttribute("file", fileDto);
		model.addAttribute("commentList", commentList);
		//TODO logging
		
		logger.info("post :: " + boardDto);
		// boardService.savePost(boardDto);
		// ModelAndView view = new ModelAndView();
		// view.setViewName("detail");
		return "board/detail";
	}
	
	// 댓글 작성
	@PostMapping("/post/{boardId}")
	public String comment(@PathVariable("boardId") int boardId, CommentDto commentDto, Model model) {
		commentDto.setBoardId(boardId);
		commentService.saveComment(commentDto);
		
		model.addAttribute("comment", commentDto);
		
		return "redirect:/post/{boardId}";
	}

	// 좋아요 수 증가
	@RequestMapping(value = "/post/requestObject", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> test(@RequestBody HashMap<String, Object> request) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		logger.info("request :: " + request);
		// boardId를 받아서 좋아요 수 증가
		int boardId = Integer.parseInt(request.get("boardId").toString());
		int empSeq = Integer.parseInt(request.get("empSeq").toString());

		// 좋아요를 눌렀는지 안눌렀는지 체크
		int success = likeInfoRepository.existLikeInfo(boardId, empSeq);
		logger.info("success :: " + success);

		// boardId 게시물 정보 가져오기
		BoardDto boardDto = boardService.getPost(boardId);
		int goodCnt = boardDto.getGoodCnt();

		// success : 0 없음 INSERT
		// success : 1 있음 UPDATE
		// 좋아요
		boolean bool;
		if (success == 0) {
			logger.info("INSERT :: ");
			LikeInfoDto likeInfoDto = new LikeInfoDto();
			likeInfoDto.setBoardId(boardDto.getBoardId());
			likeInfoDto.setEmpSeq(empSeq);
			likeInfoDto.setCreateSeq(Integer.toString(empSeq));
			likeInfoDto.setModifySeq(Integer.toString(empSeq));
			likeInfoService.saveLike(likeInfoDto);
			
			bool = boardService.updatePlusGoodCnt(boardId) == 1;
			goodCnt += 1;
			
		} else {
			
			logger.info("UPDATE :: ");
			LikeInfoDto likeDTO = likeInfoService.getLikeInfo(boardId, empSeq);
			String chkYn = likeDTO.getChkYn();
			logger.info("chkYn :: " + chkYn);
			
			// Y : 증가 N : 감소
			if (chkYn.equals("Y")) {
				bool = boardService.updateMinusGoodCnt(boardId) == 1;
				likeInfoRepository.updateChkYn(boardId, empSeq, "N");
				goodCnt -= 1;
			} else {
				bool = boardService.updatePlusGoodCnt(boardId) == 1;
				likeInfoRepository.updateChkYn(boardId, empSeq, "Y");
				goodCnt += 1;
			}
		}
		
		// 좋아요 수 가져오기
		result.put("goodCnt", goodCnt);
		result.put("resultType", bool);
		return result;
	}

	// 수정하는 창 매핑
		@GetMapping("/post/edit/{boardId}")
		public String edit(@PathVariable("boardId") int boardId, Model model) {

			BoardDto boardDto = boardService.getPost(boardId);
			FileDto fileDto = fileService.getFile(boardDto.getFileId());
			model.addAttribute("post", boardDto);
			model.addAttribute("file", fileDto);
			return "board/edit";
		}

	// 수정버튼을 누르면 put형식으로 서버에게 /post/edit/{id} 요청이 가게됨
	@PutMapping("/post/edit/{boardId}")
	public String update(MultipartHttpServletRequest request, BoardDto boardDto) throws Exception {
		List<MultipartFile> files = request.getFiles("file");

		for (MultipartFile mf : files) {
			String origFilename = mf.getOriginalFilename();
			String fileName = new MD5Generator(origFilename).toString();
			String savePath = System.getProperty("user.dir") + "\\files";

			try {
				if (!new File(savePath).exists()) {
					try {
						new File(savePath).mkdir();
					} catch (Exception e) {
						e.getStackTrace();
					}
				}
				String filePath = savePath + "\\" + fileName;
				mf.transferTo(new File(filePath));

				FileDto fileDto = new FileDto();
				fileDto.setOrigFilename(origFilename);
				fileDto.setFileName(fileName);
				fileDto.setFilePath(filePath);

				Long fileId = fileService.saveFile(fileDto);
				boardDto.setFileId(fileId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 데이터 값을 가지고 와서 null 인 부분에 값을 넣고 데이터
		BoardDto boardDTO = boardService.getPost(boardDto.getBoardId());
		boardDto.setUseYn(boardDTO.getUseYn());
		boardDto.setCreateSeq(boardDTO.getCreateSeq());
		boardDto.setModifySeq(boardDTO.getModifySeq());
		boardService.savePost(boardDto);
//파일에 보드아이디 넣어야됨			boardDto.setFileId(fileId);
			
		return "redirect:/boardList";
	}

	// 게시글 삭제
	@DeleteMapping("/post/{boardId}")
	public String delete(@PathVariable("boardId") int boardId) {
		boardService.deltePost(boardId);
		return "redirect:/boardList";
	}

	// 파일 다운로드 부분
	@GetMapping("/download/{fileId}")
	public ResponseEntity<InputStreamResource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
		FileDto fileDto = fileService.getFile(fileId);
		System.out.println("================>" + fileDto);
		Path path = Paths.get(fileDto.getFilePath());
		InputStreamResource resource = new InputStreamResource(Files.newInputStream(path));

		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename())
				.body(resource);
	}
}
