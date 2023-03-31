package lab.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Lotto.do")
public class LottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LottoServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=1; i<=45; i++) {
			list.add(i); //리스트에 1~45까지 넣는다. (순서가 정렬되있음)
		}
		Collections.shuffle(list);//순서 섞기
		//list.remove(new Integer(1));
		request.setAttribute("list", list);
		RequestDispatcher disp = request.getRequestDispatcher("/LottoResult.jsp");
		disp.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=1; i<=45; i++) {
			list.add(i); //리스트에 1~45까지 넣는다. (순서가 정렬되있음)
		}
		Collections.shuffle(list);//순서 섞기
		//list.remove(new Integer(1));
		request.setAttribute("list", list);
		response.sendRedirect("/2/LottpoResult.jsp");
	}

}
