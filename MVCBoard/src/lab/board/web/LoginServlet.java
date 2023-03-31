package lab.board.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab.web.model.MemberDAO;
import lab.web.model.MemberVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MemberDAO dao;
   
    public LoginServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String action = request.getParameter("action");
	HttpSession session = request.getSession();
	if(action.equals("logout")){
		session.invalidate();
		response.sendRedirect("/MVC/index.jsp");
	}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	dao = new MemberDAO();
	String userid = request.getParameter("userid");
	String password = request.getParameter("password");
	HttpSession session = request.getSession();
	String url = "";
	try {
		String dbpw= dao.getPassword(userid);
		if(dbpw.equals(password)) {
			MemberVO mem = dao.selectMember(userid);
			session.setAttribute("name", mem.getName());
			session.setAttribute("userid", userid);
			url="/MVC/Board.do?action=list";
			response.sendRedirect(url);
			return;
		}else {
			session.invalidate();
			url="/login.jsp";
			if(dbpw.equals("")) {
				request.setAttribute("message", "아이디가 없습니다.");
				
			}else {
				request.setAttribute("message", "비밀번호가 다릅니다.");
				
			}
		}
		
	}catch(RuntimeException e){
		session.invalidate();
		request.setAttribute("message", e.getMessage());
		url="/login.jsp";
	}
	RequestDispatcher disp = request.getRequestDispatcher(url);
	disp.forward(request,response);
	}

}
