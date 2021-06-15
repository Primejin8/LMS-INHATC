package kr.inhatc.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.dto.WeekDto;
import kr.inhatc.spring.model.week.Week;
import kr.inhatc.spring.repository.WeekRepository;

@Service
public class WeekService {
	@Autowired
	private WeekRepository weekRepository;
	
	public WeekService(WeekRepository weekRepository) {
		this.weekRepository = weekRepository;
	}
	
	@Transactional
	public int saveWeek(WeekDto weekDto) {
		return weekRepository.save(weekDto.toEntity()).getWeekId();
	}
	
	@Transactional
	public List<WeekDto> getWeekList(){
		List<Week> weekList	= weekRepository.findAll();
		List<WeekDto> weekDtoList = new ArrayList<>();
		
		for(Week week : weekList) {
			WeekDto weekDto = WeekDto.builder()
					.weekId(week.getWeekId())
					.weekNumber(week.getWeekNumber())
					.startDate(week.getStartDate())
					.endDate(week.getEndDate())
					.subId(week.getSubId())
					.build();
			weekDtoList.add(weekDto);
		}
		return weekDtoList;
	}
	
	@Transactional
	public WeekDto getWeek(int weekId) {
		Week week = weekRepository.findById(weekId).get();
		
		WeekDto weekDto	= WeekDto.builder()
				.weekId(week.getWeekId())
				.weekNumber(week.getWeekNumber())
				.startDate(week.getStartDate())
				.endDate(week.getEndDate())
				.subId(week.getSubId())
				.build();
		return weekDto;
	}
	
}
