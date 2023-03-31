package Lab.web.domain;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ErrorServlet")
public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ErrorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int a = (int)(Math.random()*2);
		String b = null;
		if(a==0) {
			b = "123";
		}System.out.println(b.charAt(0));
		 System.out.println(10/a);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
