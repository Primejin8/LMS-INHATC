package kr.inhatc.spring.controller;

import java.util.HashMap;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.inhatc.spring.dto.SubjectDto;
import kr.inhatc.spring.dto.SubjectStudentDto;
import kr.inhatc.spring.model.Subject;
import kr.inhatc.spring.repository.SubjectRepository;
import kr.inhatc.spring.repository.SubjectStudentRepository;
import kr.inhatc.spring.service.SubjectService;
import kr.inhatc.spring.service.SubjectStudentService;

@Controller
public class SubjectController {

	private Logger logger = LoggerFactory.getLogger(SubjectController.class);

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private SubjectStudentRepository subjectStudentRepository;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private SubjectStudentService subjectStudentService;

	public SubjectController() {
		logger.info("############### Create SubjectController ###############");
	}

	// Model 객체를 이용하여 데이터를 가져오고 View에 데이터를 넘겨줌
	@GetMapping("/subjectList")
	public String subjectList(Model model,
			@PageableDefault(size = 10, sort = "seq", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(required = false, defaultValue = "all") String searchType,
			@RequestParam(required = false, defaultValue = "") String searchText) {
		Page<Subject> subjectDtoList = null;
		
		if (searchType.equals("all")) {
			if (!searchText.equals("")) {
				subjectDtoList = subjectRepository.findAllBysubNameContaining(searchText, pageable);
			} else {
				subjectDtoList = subjectRepository.findAll(pageable);
			}
		} else if (searchType.equals("subname")) {
			subjectDtoList = subjectRepository.findAllBysubNameContaining(searchText, pageable);
		} else if (searchType.equals("empname")) {
			subjectDtoList = subjectRepository.findAllByempNameContaining(searchText, pageable);
		} else {
			subjectDtoList = subjectRepository.findAll(pageable);
		}
		
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
	public String write(SubjectDto subjectDto, Model model) {
		logger.info("subjectDto :: " + subjectDto);
		subjectService.saveSubject(subjectDto);
		model.addAttribute("subject", subjectDto);
		return "redirect:/subjectList";
	}

	
	@RequestMapping(value = "/btnSubjectAdd", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> btnSubjectAdd(@RequestBody HashMap<String, Object> request) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		logger.info("request :: " + request);
	
		int seq = Integer.parseInt(request.get("seq").toString());
		String empSeq = request.get("empSeq").toString();
		String empName = request.get("empName").toString();
		String subSeq = request.get("subSeq").toString();
		String subName = request.get("subName").toString();
		boolean bool = true;
		
		// 해당 강의 정보 가져오기
		SubjectDto subjectDto = subjectService.getSubject(seq);
		
		// 학생의 수강중인 강의 정보 저장
		SubjectStudentDto subjectStudentDto = new SubjectStudentDto();
		subjectStudentDto.setBizSeq(subjectDto.getBizSeq());
		subjectStudentDto.setCompSeq(subjectDto.getCompSeq());
		subjectStudentDto.setGroupSeq(subjectDto.getGroupSeq());
		subjectStudentDto.setEmpSeq(empSeq);
		subjectStudentDto.setEmpName(empName);
		subjectStudentDto.setSubSeq(subSeq);
		subjectStudentDto.setSubName(subName);

		subjectStudentDto.setCreateSeq(subjectDto.getCreateSeq());
		subjectStudentDto.setModifySeq(subjectDto.getCreateSeq());
		
		subjectStudentService.saveSubjectStudent(subjectStudentDto);
		
		result.put("resultType", bool);
		return result;
	}

}