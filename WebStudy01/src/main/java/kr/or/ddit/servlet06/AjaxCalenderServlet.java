package kr.or.ddit.servlet06;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import static java.time.format.TextStyle.FULL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AjaxCalenderLoading.do")
public class AjaxCalenderServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("da");
		
		ZoneId zone = Optional.ofNullable(req.getParameter("zone"))
							  .filter(zp->!zp.isEmpty())
							  .map(zp->ZoneId.of(zp))
							  .orElse(ZoneId.systemDefault());//기본적으로 클라이언트의 언어로 출력

		LocalDate today = LocalDate.now(zone);
		
		int year = Optional.ofNullable(req.getParameter("year"))
						   .filter(yp->yp.matches("\\d{4}"))
						   .map(Integer::parseInt)
						   .orElse(today.getYear());
		
		int month = Optional.ofNullable(req.getParameter("month"))
							.filter(yp->yp.matches("[1-9]|1[0-2]"))
							.map(Integer::parseInt)
							.orElse(today.getMonthValue());
		
		Locale locale = Optional.ofNullable(req.getParameter("locale"))
								.filter(lp->!lp.isEmpty())
								.map(lp->Locale.forLanguageTag(lp))
								.orElse(req.getLocale());//기본적으로 클라이언트의 언어로 출력
				//태그를 선택하면 변환
		
		
        String pat = "[{\"zone\":\"%s\",\"today\":\"%s\",\"year\":%s,\"month\":%s,\"locale\":\"%s\"}]";

		
		String mix = String.format(pat,zone,today,year,month,locale);
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter	out=resp.getWriter();
		out.println(mix);

		System.out.println(mix);
		
		YearMonth ym= YearMonth.of(year, month);
		YearMonth beforeYM= ym.minusMonths(1);
		YearMonth nextYM= ym.plusMonths(1);
		
		WeekFields wfs= WeekFields.of(locale);
		LocalDate firstDOM= ym.atDay(1);
		int offset = firstDOM.get(wfs.dayOfWeek())-1;
		LocalDate firstDisplay = firstDOM.minusDays(offset); //9월 29일

		
		
	}
}
