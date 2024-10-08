package kr.or.ddit.servlet06;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet("/calendar.do")
public class CalenderServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	   Calendar cal = Calendar.getInstance();
    	   
    	   System.out.println("22");
    	   
    	   String year=req.getParameter("year");
    	   String month=req.getParameter("month");
    	   
    	   System.out.println(year+" "+month);
    	   
    	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd E");
    	   Integer Iy = Integer.parseInt(year);
    	   Integer Im = Integer.parseInt(month);
    	   
    	   df.format(cal.getTime());
    	   
    	   if(Iy!=null||Im!=null) {
    		   cal.set(Iy, Im, 01); // 년, 월(0~11, 1월:0), 일 바꾸기
    	   }
    	   
    	   String pat1="<h2>%s 년</h2>";
           String map1=String.format(pat1, year);
           String pat2="<h2>%s 월</h2>";
           String map2=String.format(pat2, month);
    	   
//           System.out.println(df.format(cal.getTime()));
           
//           cal.set(Calendar.YEAR, 2023); // 년도 바꾸기
//           cal.set(Calendar.MONTH, Calendar.JANUARY); // 월 바꾸기
//           cal.set(Calendar.DATE, 1); // 일 바꾸기
           
    	   StringBuffer content = new StringBuffer();
  			System.out.println("SU MO TU WE TH FR SA");
  			content.append("<html>                  ");
  			content.append("<body>                  ");
  			content.append(map1);
  			content.append(map2);

  			
  			content.append("su mo tu we th fr sa <br>");
  			
          
          int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

          cal.set(Calendar.DATE, 1);
          int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
          
          for (int i = 1; i < dayOfWeek; i++) {   
       	   System.out.print("   ");
       	   content.append(" □ ");
      
          }
          
          String pat = "%02d ";
          
          for (int i = 1; i <= lastDay; i++) {
              System.out.printf("%02d ", i);
              String app = String.format(pat, i);
              content.append(app);
              if (dayOfWeek % 7 == 0) {
                  System.out.println();
                  content.append("<br>");
              }
              dayOfWeek++;
          }
      
          content.append("<form method='get' action='./calendar.do'>                  ");        
          content.append("<input placeholder='년도' type='text' name='year'>                  ");        
          content.append("<input placeholder='월' type='text' name='month'>                  ");        
          content.append("<button type='submit' >찾기</button> ");
          content.append("</form>                 ");
          content.append("<input type='button' name='pre' value='이전 달'>                 ");
          content.append("<input type='button' name='next' value='다음 달'>                 ");
          
          
  		content.append("</body>                 ");
  		content.append("</html>                 ");
  		resp.setContentType("text/html;charset=utf-8");
		
		try(
				PrintWriter out = resp.getWriter();
				){
			out.print(content.toString());
		}
}}
	
	
