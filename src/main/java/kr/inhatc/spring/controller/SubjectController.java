package kr.inhatc.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.inhatc.spring.dto.SubjectDto;
import kr.inhatc.spring.model.Subject;
import kr.inhatc.spring.repository.SubjectRepository;
import kr.inhatc.spring.service.SubjectService;

@Controller
public class SubjectController {

	private Logger logger = LoggerFactory.getLogger(SubjectController.class);

	@Autowired
	private SubjectRepository subjectRepository;
	
	//@Autowired
	//private SubjectMultiRepository subjectMultiRepository;
	
	@Autowired
	private SubjectService subjectService;

	//@Autowired
	//private SubjectMultiService subjectMultiService;

	public SubjectController() {
		logger.info("############### Create SubjectController ###############");
	}

	// Model 객체를 이용하여 데이터를 가져오고 View에 데이터를 넘겨줌
	@GetMapping("/subjectList")
	public String subjectList(Model model,
			@PageableDefault(size = 10, sort = "seq", 
			direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Subject> subjectDtoList = null;
		subjectDtoList = subjectRepository.findAll(pageable);
		int startPage = Math.max(1, subjectDtoList.getPageable().getPageNumber() - 4);
		int endPage = Math.min(subjectDtoList.getTotalPages(), subjectDtoList.getPageable().getPageNumber() + 4);
		model.addAttribute("startPage", startPage); // (key, value)형태로 view에 전달
		model.addAttribute("endPage", endPage); // (key, value)형태로 view에 전달
		model.addAttribute("subjectList", subjectDtoList); // (key, value)형태로 view에 전달
		return "subject/subjectList";
	}
	
	@GetMapping("/subjectPost")
	public String post() {
		return "subject/subjectPost";
	}

	@PostMapping("/subjectPost")
	public String write(SubjectDto subjectDto/* , SubjectMultiDto subjectMultiDto */) {
		logger.info("subjectDto :: " + subjectDto);
		//logger.info("subjectMultiDto :: " + subjectMultiDto);
		subjectService.saveSubject(subjectDto);
		//subjectMultiService.saveSubjectMulti(subjectMultiDto);
		return "redirect:/subjectList";
	}

//	@GetMapping("/subjectDetail/{seq}")
//	public String detail(@PathVariable("seq") int seq, Model model) {
//		SubjectDto subjectDto = subjectService.getSubject(seq);
//		logger.info("detail subjectDto :: " + subjectDto);
//		//subjectService.updateView(subjectDto.getSeq());
//		model.addAttribute("post", subjectDto);
//		return "subject/subjectDetail";
//	}
	
}