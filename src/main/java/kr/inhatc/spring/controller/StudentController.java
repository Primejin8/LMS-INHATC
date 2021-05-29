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

import kr.inhatc.spring.model.SubjectStudent;
import kr.inhatc.spring.repository.SubjectStudentRepository;
import kr.inhatc.spring.service.SubjectStudentService;

@Controller
public class StudentController {

	private Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private SubjectStudentRepository SubjectStudentRepository;
	
	@Autowired
	private SubjectStudentService subjectStudentService;

	public StudentController() {
		logger.info("############### Create SubjectStudentController ###############");
	}

	// Model 객체를 이용하여 데이터를 가져오고 View에 데이터를 넘겨줌
	@GetMapping("/studentList")
	public String subjectList(Model model,
			@PageableDefault(size = 10, sort = "seq", 
			direction = Sort.Direction.DESC) Pageable pageable) {
		Page<SubjectStudent> subjectStudentDtoList = null;
		subjectStudentDtoList = SubjectStudentRepository.findAll(pageable);
		int startPage = Math.max(1, subjectStudentDtoList.getPageable().getPageNumber() - 4);
		int endPage = Math.min(subjectStudentDtoList.getTotalPages(), subjectStudentDtoList.getPageable().getPageNumber() + 4);
		model.addAttribute("startPage", startPage); // (key, value)형태로 view에 전달
		model.addAttribute("endPage", endPage); // (key, value)형태로 view에 전달
		model.addAttribute("studentList", subjectStudentDtoList); // (key, value)형태로 view에 전달
		return "student/studentList";
	}
	
}