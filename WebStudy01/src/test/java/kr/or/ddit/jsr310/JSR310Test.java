package kr.or.ddit.jsr310;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class JSR310Test {

	@Test
	void testJsr310(){
		//GMT 표준 시간대를 사용 immutable (불변 不變) 객체
		LocalDateTime ldt = LocalDateTime.now();
		LocalDateTime ldty = LocalDateTime.now(ZoneId.of("America/New_York"));
		System.out.println(ldt);
		
//		LocalDate ld = LocalDate.now();
		LocalDate ld = LocalDate.from(ldt);		
		
		System.out.println(ld);
		System.out.println(ld.getMonthValue());
		
		YearMonth ym = YearMonth.now();
		System.out.println(ym);
		
//		Year year = Year.now();
		Year year = Year.from(ldt);
		System.out.println(year);//2024
		System.out.println(year.minusYears(1)); //2023
		System.out.println(year);//2024
		System.out.println(year.plusYears(1)); //2025
		System.out.println(year);//2024
		
	}

	
	@Disabled
	@Test
	void testDate(){
		Date today = new Date();	
		System.out.printf("month : %d\n",today.getMonth());	
		today.setYear(2022);		
//		unix time, epoch time :기준 시점을 정해두고(1970년 1월 1일 0시 0분 0초) 결과된 밀리세컨드로 시간을 계산		
	}
	@Disabled
	@Test //테스트 케이스 하나
	void test() {
		System.out.println("test 진행");
	}
	
	
}
