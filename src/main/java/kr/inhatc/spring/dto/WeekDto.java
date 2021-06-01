package kr.inhatc.spring.dto;

import java.sql.Date;

import kr.inhatc.spring.model.week.Week;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeekDto {
	private int weekId;
	private int weekNumber;
	private Date startDate;
	private Date endDate;
	private int subId;
	
	public Week toEntity() {
		Week build = Week.builder()
				.weekId(weekId)
				.weekNumber(weekNumber)
				.startDate(startDate)
				.endDate(endDate)
				.subId(subId)
				.build();
		return build;
	}
	
	@Builder
	public WeekDto(int weekId, int weekNumber, Date startDate, Date endDate, int subId) {
		this.weekId = weekId;
		this.weekNumber = weekNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.subId = subId;
	}
}
