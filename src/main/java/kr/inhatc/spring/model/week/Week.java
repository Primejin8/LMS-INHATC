package kr.inhatc.spring.model.week;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Week {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int weekId;
	
	// 주차수
	private int weekNumber;
	
	// 시작일
	private Date startDate;
	
	// 종료일
	private Date endDate;
	
	// 강의ID (주차 정보 넘겨줄 것)
	private int subId; 
	
	@Builder
	public Week(int weekId, int weekNumber, Date startDate, Date endDate, int subId) {
		this.weekId = weekId;
		this.weekNumber = weekNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.subId = subId;
	}

}
