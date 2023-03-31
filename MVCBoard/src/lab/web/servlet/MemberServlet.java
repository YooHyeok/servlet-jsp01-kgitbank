package lab.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab.web.model.MemberDAO;
import lab.web.model.MemberVO;


@WebServlet("/Member.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      MemberDAO dao;
   
    public MemberServlet() {
        super();
    }

	
	public void init(ServletConfig config) throws ServletException {
		dao = new MemberDAO();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String url = "";
		if("insert".equals(action)) {
			request.setAttribute("action", action);
			request.setAttribute("message", "회원가입");
			url="/memberform.jsp";
		}else if("update".equals(action)) {
			HttpSession session = request.getSession();
			String userid = (String)session.getAttribute("userid");
			MemberVO member = dao.selectMember(userid);
			request.setAttribute("action", action);
			request.setAttribute("member", member);
			request.setAttribute("message", "회원 정보 수정");
			url="/memberform.jsp";
		}else if("update".equals(action)) {
			request.setAttribute("action", action);
			url="/board/memberDelete.jsp";
			
		}
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String url = "";
		MemberVO member = new MemberVO();
		member.setUserid(userid);
		member.setPassword(password);
		member.setName(name);
		member.setEmail(email);
		member.setAddress(address);
		if("insert".equals(action)) {
			dao.insertMember(member);
			request.setAttribute("message", "회원 가입 성공");
			url="/login.jsp";
		}else if("update".equals(action)) {
			dao.updateMember(member);
			response.sendRedirect("/MVC/Board.do?action=member");
			return;
		}else if("delete".equals(action)) {
			dao.deleteMember(userid, password);
			request.getSession().invalidate();
			request.setAttribute("message", "회원 정보 삭제 완료");
			url="/login.jsp";
		}
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);
	}

}
























