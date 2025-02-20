<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Optional"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.time.Month"%>
<%@page import="static java.time.format.TextStyle.FULL"%>
<%@page import="java.time.DayOfWeek"%>
<%@page import="java.time.temporal.WeekFields"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.YearMonth"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

// 	ZoneId zone=ZoneId.of("Asia/Seoul");

	ZoneId zone = Optional.ofNullable(request.getParameter("zone"))
							.filter(zp->!zp.isEmpty())
			 				.map(zp->ZoneId.of(zp))
			 				.orElse(ZoneId.systemDefault());//기본적으로 클라이언트의 언어로 출력

	LocalDate today = LocalDate.now(zone);
	
	int year = Optional.ofNullable(request.getParameter("year"))
					   .filter(yp->yp.matches("\\d{4}"))
					   .map(Integer::parseInt)
					   .orElse(today.getYear());

	int month = Optional.ofNullable(request.getParameter("month"))
					   .filter(yp->yp.matches("[1-9]|1[0-2]"))
					   .map(Integer::parseInt)
					   .orElse(today.getMonthValue());

	Locale locale = Optional.ofNullable(request.getParameter("locale"))
							.filter(lp->!lp.isEmpty())
			 				.map(lp->Locale.forLanguageTag(lp))
			 				.orElse(request.getLocale());//기본적으로 클라이언트의 언어로 출력
			 				//태그를 선택하면 변환
		
// 	Locale locale= Locale.forLanguageTag("ko-kr");
	
	YearMonth ym= YearMonth.of(year, month);
	
	YearMonth beforeYM= ym.minusMonths(1);
	YearMonth nextYM= ym.plusMonths(1);
	
	WeekFields wfs= WeekFields.of(locale);
	LocalDate firstDOM= ym.atDay(1);
	int offset = firstDOM.get(wfs.dayOfWeek())-1;
	LocalDate firstDisplay = firstDOM.minusDays(offset); //9월 29일
	
	
	
%> 
    


<h1>현재 인증된 사용자 : <%=request.getUserPrincipal() %></h1>
<h1>현재 시간 <%=LocalDateTime.now(zone) %></h1>
<h1>
<a href="javascript:void(0);" class="link-a" data-year="<%=beforeYM.getYear() %>" data-month=<%=beforeYM.getMonthValue()%>> ◀◀◀ </a>
<%-- ?year=<%=beforeYM.getYear() %>&month=<%=beforeYM.getMonthValue()%>&locale=<%=locale.toLanguageTag()%> &zone=<%=zone.getId()%> --%>
<%=String.format(locale,"%1$tY %1$tB", ym) %>

<a href="javascript:void(0);" class="link-a" data-year="<%=nextYM.getYear() %>" data-month=<%=nextYM.getMonthValue()%>> ▷▷▷ </a>
</h1>

<table>
	<thead>
		<tr>
		<%
		DayOfWeek firstDOW = wfs.getFirstDayOfWeek();
			for(int i=0;i<7;i++){
				DayOfWeek thisTurn= firstDOW.plus(i);
				%>
				<th class="<%=thisTurn.name().toLowerCase()%>">
<%-- 				<%=thisTurn.getDisplayName(TextStyle.FULL, locale)%>위의 import 확인 스태틱 맴버를 쉽게 사용가능 --%>
				<%=thisTurn.getDisplayName(FULL, locale)%>
				</th>
		<%
		}
		%>
		</tr>
	</thead>
	
	<tbody>
		<%
			int count=0;
			for(int row=1;row<=6;row++){
				%>
				<tr>
				<%
				for(int col=1;col<=7;col++){
				 LocalDate thisTurn=	firstDisplay.plusDays(count++);
				 YearMonth thisTurnYM=YearMonth.from(thisTurn);
				 
				 String classAttr=thisTurnYM.isBefore(ym)?"before" : 
					 					thisTurnYM.isAfter(ym)?"after" : "this-month";
				 classAttr+=" "+ thisTurn.getDayOfWeek().name().toLowerCase();
				
				boolean isToday=thisTurn.isEqual(today);
				 
				%>
<%-- 				<td class="<%=classAttr%>" <%=today.getDayOfMonth()==thisTurn.getDayOfMonth()? "id='today'":"''" %> ><%=thisTurn.getDayOfMonth() %></td> --%>
				<td class="<%=classAttr%>" <%=isToday ? "id='today'":"" %> ><%=thisTurn.getDayOfMonth() %></td>
				<%
				}
				%>
				</tr>
				<%
			}	
		%>
	</tbody>


</table>
<script type="text/javascript">
	const calForm =	document.calForm;
	calForm.year.value=<%=ym.getYear()%>;
	calForm.month.value=<%=ym.getMonthValue()%>;
	calForm.locale.value="<%=locale.toLanguageTag()%>";
	calForm.locale.zone="<%=zone.getId()%>";
	
	//제일 중요한 부분
	//a태그의 이벤트의 흐름을 한곳으로 제한함 
	document.querySelectorAll(".link-a").forEach(a=>{
		a.addEventListener("click",e=>{
			calForm.year.value = a.dataset.year
			calForm.month.value= a.dataset['month']
			//이벤트 발생
			calForm.requestSubmit();
		})		
	});

</script>
