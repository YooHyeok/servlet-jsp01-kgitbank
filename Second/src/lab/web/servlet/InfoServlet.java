package lab.web.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Info.do")
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HashMap<String,String> map = new HashMap<>();
	
    public InfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("map", map);//리퀘스트에 맵을 저장
		RequestDispatcher disp = request.getRequestDispatcher("/InfoResult.jsp");
		disp.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		map.put(request.getParameter("name"),request.getParameter("tel"));
		response.sendRedirect("/2/Info.do");//목록을 보여달라고 겟방식으로 요청 
		//응답의 결과가 또다른 요청(새로운 요청)
	}

}
