package lab.board.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab.web.model.BoardDAO;
import lab.web.model.BoardVO;
import lab.web.model.MemberDAO;


@WebServlet("/Board.do")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardDAO dao;
	MemberDAO mdao;

	public BoardServlet() {
		super();
	}


	public void init(ServletConfig config) throws ServletException {
		super.init();
		dao = new BoardDAO();
		mdao = new MemberDAO();
	}


	public void destroy() {
		dao = null;
		mdao = null;
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String url = "";
		if("write".equals(action)) {
			request.setAttribute("message", "새 글입력");
			url = url + "/board/write.jsp";
			request.setAttribute("action", "write_do");
		}else if("list".equals(action)) {
			String pageStr = request.getParameter("page");
			int page = 1;
			if(pageStr != null) {
				page = Integer.parseInt(pageStr);
			}
			Collection<BoardVO> lists = dao.selectArticleList(page);
			request.setAttribute("list", lists);
			url = url + "/board/list.jsp";

			int bbsCount = dao.selectTotalBbscount();
			double totalPage = 0;
			if(bbsCount > 0) {
				totalPage = bbsCount/10.0;
			}
			if((totalPage % 1) !=0) {
				totalPage = totalPage+1;
			}
			request.setAttribute("totalPageCount", (int)totalPage);
			request.setAttribute("page", page);
		}else if("view".equals(action)) {
			String bbsnoStr = request.getParameter("bbsno");
			int bbsno = Integer.parseInt(bbsnoStr);
			BoardVO board = dao.selectArticle(bbsno);
			dao.updateReadCount(bbsno);
			if(board.getContent() != null) {
				board.setContent(board.getContent().replaceAll("\n", "<br>"));
			}
			request.setAttribute("board", board);
			request.setAttribute("message", "글 상세 보기");
			url = url + "/board/view.jsp";
		}else if("reply".equals(action)) {
			String bbsno = request.getParameter("bbsno");
			BoardVO board = dao.selectArticle(Integer.parseInt(bbsno));
			board.setSubject("[re]"+board.getSubject());
			board.setContent(board.getContent()+"\n---------\n");
			request.setAttribute("board", board);
			request.setAttribute("message", "댓글입력");
			request.setAttribute("action", "reply_do");
			url = url + "/board/write.jsp";
		}else if("update".equals(action)) {
			String bbsnoStr = request.getParameter("bbsno");
			int bbsno = Integer.parseInt(bbsnoStr);
			BoardVO board = dao.selectArticle(bbsno);
			request.setAttribute("board", board);
			request.setAttribute("message", "글 수정 화면");
			request.setAttribute("action", "update_do");
			url = url + "/board/write.jsp";
		}else if("delete".equals(action)) {
			String bbsnoStr = request.getParameter("bbsno");
			String replynoStr = request.getParameter("bbsno");
			request.setAttribute("bbsno", bbsnoStr);
			request.setAttribute("replynumber", replynoStr);
			request.setAttribute("action", "delete_do");
			url = url + "/board/delete.jsp";
		}
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String url = "";
		HttpSession session = request.getSession();
		if("write_do".equals(action)) {
			String userId = (String)session.getAttribute("userid");
			String password = request.getParameter("password");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");

			BoardVO board = new BoardVO();
			board.setUsrId(userId);
			board.setPassword(password);
			board.setContent(content);
			board.setSubject(subject);

			dao.insertArticle(board);

			url = "/MVC/Board.do?action=list";
			response.sendRedirect(url);
			return;
		}else if("reply_do".equals(action)) {
			String userId = (String)session.getAttribute("userid");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String password = request.getParameter("password");
			int masterid = Integer.parseInt(request.getParameter("masterid"));
			int bbsno = Integer.parseInt(request.getParameter("bbsno"));
			int replynumber = Integer.parseInt(request.getParameter("replynumber"));
			int replystep = Integer.parseInt(request.getParameter("replystep"));
			BoardVO board = new BoardVO();
			board.setBbsno(bbsno);
			board.setUsrId(userId);
			board.setSubject(subject);
			board.setContent(content);
			board.setPassword(password);
			board.setMasterId(masterid);
			board.setReplyNumber(replynumber);
			board.setReplyStep(replystep);
			dao.replyArticle(board);
			response.sendRedirect("/MVC/Board.do?action=list");
			return;

		}else if("update_do".equals(action)) {
			String password = request.getParameter("password");
			String bbsnoStr = request.getParameter("bbsno");
			int bbsno = Integer.parseInt(bbsnoStr);
			String dbpw = dao.getPassword(bbsno);
			if(dbpw.equals(password)) {
				BoardVO board = new BoardVO();
				board.setBbsno(bbsno);
				board.setSubject(request.getParameter("subject"));
				board.setContent(request.getParameter("content"));
				dao.updateArticle(board);
				url = url + "/MVC/Board.do?action=view&bbsno="+bbsno;
				response.sendRedirect(url);
				return;
			}else {
				request.setAttribute("message", "비밀번호가 다릅니다. 수정되지 않았습니다.");
				url = url + "/error/error.jsp";

			}
		}else if("delete_do".equals(action)) {
			String password = request.getParameter("password");
			int bbsno = Integer.parseInt(request.getParameter("bbsno"));
			int replynumber = Integer.parseInt(request.getParameter("replynumber"));
			String dbpw = dao.getPassword(bbsno);
			if(dbpw.equals(password)) {
				dao.deleteArticle(bbsno, replynumber);
				url = "/MVC/Board.do?action=list";
				response.sendRedirect(url);
				return;

			}else {
				request.setAttribute("message", "비밀번호가 다릅니다. 수정되지 않았습니다.");
				url = url + "/error/error.jsp";
			}
			RequestDispatcher disp = request.getRequestDispatcher(url);
			disp.forward(request,response);
		}

	}
}




























