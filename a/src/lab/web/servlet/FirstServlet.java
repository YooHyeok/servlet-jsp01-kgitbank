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

//Annotation
//서블릿의 주소 - 만들고 변경 가능
@WebServlet("/First.do")

public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FirstServlet() {
    	
    }
    // get방식 요청이 들어올 때 실행 - http 요청 메세지의 기본 방식은 get 방식
    // request, response - 현재 들어온 요청 메세지랑 지금 나갈 응답 메세지를
    // 웹 컨테이너(서블릿 라이브러리)가 파싱해서 만들어놓은 객체. (파싱 : 메세지를 자바의형태로 바꿨다는 뜻)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답 기능
		//응답을 어떤 형식으로 내보낼 지 결정
		response.setContentType("text/html;charset=UTF-8"); //텍스트 html 형식으로 응답하겠다.
		
		//PrintWriter out = 브라우저에 출력할 수 있는 출력 스트림 - 응답시에 사용하기 때문에 응답에서 호출 가능
		//(sytem.out = 콘솔)
		PrintWriter out = response.getWriter();
		
		//응답 페이지 출력 내용
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<title>첫 번째 페이지</title>");
		out.println("</HEAD>");
		out.println("<body>");
		int a = (int)(Math.random()*10)+1;
		if(a>5) {
			out.println("오늘은 운수 좋은 날!");
		}
		else {
			out.println("오늘은 가만히 계세요. ");
		}
		out.println("</body>");
		out.println("</HTML >");
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//request안에있는 파라미터를 변환
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String sdate = request.getParameter("date");
		
		SimpleDateFormat tool = new SimpleDateFormat("yyyy-mm-dd");
		java.util.Date date = null;
		try {
		date = tool.parse(sdate);
		}catch(ParseException e) {
			
		}
		
		String[] hobbys = request.getParameterValues("hobby");
		String gender = request.getParameter("gender");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		int grade = Integer.parseInt(request.getParameter("grade")); //문자를 숫자로 변환
		String introduce = request.getParameter("introduce");
		String area = request.getParameter("area");
		//응답을 내보내고자 하는 형식.
		response.setContentType("text/html;charset=UTF-8");
	
		PrintWriter out = response.getWriter();
		
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<title>입력 폼 결과 페이지</title>");
		out.println("</HEAD>");
		out.println("<body>");
		
		out.printf("아이디 : %s<br>", id);
		out.printf("비밀번호 : %s<br>", pw);

		
		out.printf("이름 : %s<br>", name);
		//a>b ? a : b;  참이면a 거짓이면b
		out.printf("성별  : %s<br>", gender.equals("M")?"남":"여");
		out.println("생년월일 : " + date);
		out.printf("학년 : %d<br>", grade);
		out.printf("연락처 : %s<br>", tel);
		out.printf("이메일 : %s<br>", email);

		HashMap<String,String> hobbyMap = new HashMap<>();
		hobbyMap.put("1", "배틀그라운드");hobbyMap.put("2", "리그오브레전드");hobbyMap.put("3", "재즈피아노");
		hobbyMap.put("4", "음악감상");hobbyMap.put("5", "음악서칭");hobbyMap.put("6", "하드웨어 정보 수집");
		out.println("취미 <br>");
		if(hobbys==null) {
			out.println("선택없음.<br>");
		}
		else {
			for(String key:hobbys) {
				out.printf(" %s", hobbyMap.get(key));
			}
			out.println("<br>");
		}
		//a>b ? a : b;  참이면a 거짓이면b
		

		out.println("자기소개");
		out.println("<br>-----------------------<br>");
		out.println("<pre>"+introduce+"</pre>");
		out.println("<br>-----------------------<br>");
		
		HashMap<String,String> areaMap = new HashMap<>();
		areaMap.put("1", "서울");areaMap.put("2", "경기");areaMap.put("3", "강원");areaMap.put("4", "충남");areaMap.put("5", "충북");
		areaMap.put("6", "전남");areaMap.put("7", "전북");areaMap.put("8", "경남");areaMap.put("9", "경북");areaMap.put("10", "인천");
		areaMap.put("11", "광주");areaMap.put("12", "세종");areaMap.put("13", "부산");
		out.printf("지역 : %s", area);
		out.println("</body>");
		out.println("</HTML >");
	}

}
