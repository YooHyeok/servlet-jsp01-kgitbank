package lab.web.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginInfo.do")
public class LoginInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HashMap<String,String> map = new HashMap<>();
  
    public LoginInfo() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("map", map);
		RequestDispatcher disp = request.getRequestDispatcher("/InfoSearch.jsp");
		disp.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		map.put(request.getParameter("id"), request.getParameter("pw"));
		response.sendRedirect("/2/LoginInfo.do");
	}

}
