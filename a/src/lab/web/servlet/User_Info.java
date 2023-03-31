package lab.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UserInfo.do")
public class User_Info extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public User_Info() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String gender = request.getParameter("gender");
		String tel = request.getParameter("tel");
		String [] agree = request.getParameterValues("agree");
		String sdate = request.getParameter("date");
//		SimpleDateFormat tool = new SimpleDateFormat("yyyy-mm-dd");
//		
//		java.util.Date date = null;
//		try {
//			date = tool.parse(sdate);
//		}catch(ParseException e) {
//			
//		}
		String job = request.getParameter("job");
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<title>개인 정보 입력 화면</title>");
		out.println("</HEAD>");
		out.println("<body>");
		
		out.printf("이름 : %s<br>",name);
		out.printf("아이디 : %s<br>",id);
		out.printf("패스워드 : %s<br>",pw);
		out.printf("연락처 : %s<br>",tel);
		out.printf("성별 : %s<br>",gender.equals("M")?"남":"여");
		HashMap<String,String> agreeMap = new HashMap<>();
		agreeMap.put("1", "공지");
		agreeMap.put("2", "광고");
		agreeMap.put("3", "배송확인");
		
		if(agree==null) {
			out.println("선택없음.<br>");
		}else {
			for(String key:agree) {
					out.printf("메일 수신 여부: %s<br>", agreeMap.get(key));
			}
			out.print("<br>");
		}
		out.printf("날짜 : %s<br>", sdate);
		
		HashMap<String,String> jobMap = new HashMap<>();
		jobMap.put("1", "회사원");
		jobMap.put("2", "주부");
		jobMap.put("3", "무직");
		jobMap.put("4", "학생");
		out.printf("직업 : %s<br>", jobMap.get(job));

		
		out.println("</body>");
		out.println("</HTML >");
	}
	
}
