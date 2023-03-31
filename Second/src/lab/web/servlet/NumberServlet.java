package lab.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Number.do")
public class NumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NumberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int number = Integer.parseInt(request.getParameter("number"));
		ArrayList<Integer> list = new ArrayList<>();// <> generic
		for(int i=0; i<number; i++) {
			if((i+1)%3==0||(i+1)%5==0) {
				list.add(i+1);
			}
		}
		request.setAttribute("list", list);
		//list배열을 "list"라는 이름으로 request에 저장
		
		RequestDispatcher disp = request.getRequestDispatcher("/NumberResult.jsp");
		//스크립트는 파일명이 주소.
		//disp라는 이름에 파일명 주소를 심어준다
		disp.forward(request, response);//주로 데이터를 전송할때 씀
		//주소가심어진 disp에 request와 response를 전송.
		//NumberResult.jsp파일에 request와 response가 심어짐.
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
