package lab.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Info.do")
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ArrayList<String> list = new ArrayList<>();

    public InfoServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("list", list);
		RequestDispatcher disp = request.getRequestDispatcher("/InfoResult.jsp");
		disp.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		list.add(request.getParameter("teamName"));
		response.sendRedirect("/Quiz/Info.do");
	}

}
