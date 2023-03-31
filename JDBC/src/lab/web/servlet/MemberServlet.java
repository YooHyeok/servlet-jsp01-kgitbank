package lab.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab.web.dao.MemberDAO;
import lab.web.vo.MemberVO;


@WebServlet("/Member.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MemberDAO dao;
    public MemberServlet() {
    	super();
    	dao = new MemberDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String url = "";
		if("insert".equals(action)) {
			request.setAttribute("action", action);
			url = "/MemberForm.jsp";
		}
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if("insert".equals (action)) {
			MemberVO mem = new MemberVO();
			mem.setUserid(request.getParameter("userid"));
			mem.setName(request.getParameter("name"));
			mem.setPassword(request.getParameter("password"));
			mem.setEmail(request.getParameter("email"));
			mem.setAddress(request.getParameter("address"));
			dao.insertMember(mem);
			response.sendRedirect("/JDBC/Login.jsp");
		}
		else if("login".equals(action)) {
			String userid = request.getParameter("userid");
			String password = request.getParameter("password");
			if(password.equals(dao.getPassword(userid))) {
				session.setAttribute("userid", userid);
				response.sendRedirect("/JDBC/hr/Index.jsp");
			}else {
				request.setAttribute("message", "비밀번호가 다릅니다.");
				RequestDispatcher disp=request.getRequestDispatcher("/Login.jsp");
				disp.forward(request, response);
			}
		}
		else if("logout".equals(action)) {
			session.invalidate();
			response.sendRedirect("/JDBC/Login.jsp");
		}
		
		
	}

}
