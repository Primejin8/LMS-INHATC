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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.inhatc.spring.dto.SubjectDto;
import kr.inhatc.spring.dto.WeekDto;
import kr.inhatc.spring.model.week.Week;
import kr.inhatc.spring.repository.WeekRepository;
import kr.inhatc.spring.service.SubjectService;
import kr.inhatc.spring.service.WeekService;

@Controller
public class WeekController {
	
	private Logger logger = LoggerFactory.getLogger(WeekController.class);
	
	@Autowired
	private WeekRepository weekRepository;
	
	@Autowired
	private WeekService weekService;
	
	@Autowired
	private SubjectService subjectService;

	public WeekController() {
		logger.info("############### Create WeekController ###############");
	}
	
	// 주차정보 리스트
	@GetMapping("/weekList")
	public String weekList(Model model, 
			@PageableDefault(size=10, sort="weekNumber", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Week> weekDtoList = null;
		weekDtoList = weekRepository.findAll(pageable);
		
		int startPage = Math.max(1, weekDtoList.getPageable().getPageNumber() - 4);
		int endPage = Math.min(weekDtoList.getTotalPages(), weekDtoList.getPageable().getPageNumber() + 4);
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("weekList", weekDtoList);
		
		return "week/weekList";
	}
	
	// 주차 상세보기
	@GetMapping("/weekPost/{weekId}")
	public String detail(@PathVariable("weekId") int weekId, Model model) {
		WeekDto weekDto = weekService.getWeek(weekId);
		
		
		weekService.saveWeek(weekDto);
		model.addAttribute("weekPost", weekDto);
		return "week/weekDetail";
	}
	
	// 주차 추가하기
	@GetMapping("/weekPost")
	public String weekPost() {
		return "week/weekPost";
	}
	
	@PostMapping("/weekPost")
	public String write(WeekDto weekDto) {
		weekService.saveWeek(weekDto);
		return "redirect:/weekList";
	}
	
	// 주차 수정
	@GetMapping("/weekPost/weekEdit/{weekId}")
	public String edit(@PathVariable("weekId") int weekId, Model model) {
		WeekDto weekDto = weekService.getWeek(weekId);
		model.addAttribute("weekPost", weekDto);
		return "week/weekEdit";
	}
	@PostMapping("/weekPost/weekEdit/{weekId}")
	public String update(@PathVariable("weekId") int weekId, WeekDto weekDto) {
		weekService.saveWeek(weekDto);
		return "redirect:/weekList";
	}
}
