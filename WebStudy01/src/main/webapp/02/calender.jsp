<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.DayOfWeek"%>
<%@page import="java.time.temporal.WeekFields"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.YearMonth"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	ZoneId zone=ZoneId.systemDefault();
	Locale locale= request.getLocale();
	LocalDate today = LocalDate.now(zone);
	YearMonth ym= YearMonth.from(today);
	
	WeekFields wfs= WeekFields.of(locale);
	
	LocalDate firstDOM= ym.atDay(1);
	int offset = firstDOM.get(wfs.dayOfWeek()) -1;
	LocalDate firstDisplay = firstDOM.minusDays(offset); //9월 29일
	
%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
	.sunday{
		color: red;	
	}
	.saturday{
		color: aqua;	
	
	}
	
	.before,.after{
		color: silver;
	}
	
</style>
</head>
<body>
<h1><%=String.format(locale,"%1$tY %1$tB", ym) %> </h1>

<table>
	<thead>
		<tr>
		<%
		DayOfWeek firstDOW = wfs.getFirstDayOfWeek();
			for(int i=0;i<7;i++){
				DayOfWeek thisTurn= firstDOW.plus(i);
				%>
				<th class="<%=thisTurn.name().toLowerCase()%>">
				<%=thisTurn.getDisplayName(TextStyle.FULL, locale)%>
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
				%>
				<td class="<%=classAttr%>"><%=thisTurn.getDayOfMonth() %></td>
				<%
				}
				%>
				</tr>
				<%
			}	
		%>
	</tbody>


</table>
</body>
</html>