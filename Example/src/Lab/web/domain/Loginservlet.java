package Lab.web.domain;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login.do")
public class Loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HashMap<String,String> map = new HashMap<>();
   
    public Loginservlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("/Ex/Mypage.jsp");

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		if("join".equals(action)) {
			map.put(id,pw);
			response.sendRedirect("/Ex/Login.jsp");
		}else if("login".equals(action)) {
			if(pw.equals(map.get(id))) {
				HttpSession session = request.getSession();
				//session.setMaxInactiveInterval(arg0);
				session.setAttribute("userid", id);
				Cookie c1 = new Cookie("id",id);
				Cookie c2 = new Cookie("pw",pw);
				response.addCookie(c1);
				response.addCookie(c2);
				response.sendRedirect("/Ex/Mypage.jsp");
			}else {
				request.setAttribute("message", "아이디 또는 비밀번호가 다릅니다.");
				RequestDispatcher disp = request.getRequestDispatcher("/alert.jsp");
				disp.forward(request,response);
			}
		}
	}

}











