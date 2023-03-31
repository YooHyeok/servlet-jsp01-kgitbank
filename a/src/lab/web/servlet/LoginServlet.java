package lab.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
				
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<title>로그인 결과 페이지</title>");
		out.println("</HEAD>");
		out.println("<body>");
		if(id.equals("yjk7454")&&pw.equals("123456")) {//참조형 데이터이기 때문에  equals사용
			out.println("로그인에 성공하셨습니다");
			out.printf("아이디: %s,비밀번호: %s",id,pw);		
			out.println("<a href=\"/First.do\">오늘의 운수</a>");
			// -> \"/Frist.do\" 문자열 안에 주소입력법
		}
		else {
			out.println("로그인에 실패하셨습니다. 로그인으로 돌아가시려면 ");
			out.println("로그인에 실패하셨습니다. 로그인으로 돌아가시려면 "
					+ "<a href=\"/Login.html\">로그인</a>을 눌러주세요."); //login.do의 웹주소 : login.html
			out.println("</body>");
			out.println("</HTML >");
		}
		
	}

}
