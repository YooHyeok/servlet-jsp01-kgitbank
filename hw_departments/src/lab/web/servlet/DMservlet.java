package lab.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.dao.DmDAO;
import lab.web.vo.DmVO;


@WebServlet("/DM.do")
public class DMservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DmDAO dao;
	
    @Override
	public void init(ServletConfig config) throws ServletException {
    dao = new DmDAO();
    }

	public DMservlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String action = request.getParameter("action");
	String url = "";
	if("list".equals(action)) {
		ArrayList<DmVO> list = dao.selectDepartmentList();
		request.setAttribute("list",list);
		url="/list.jsp";
		
	}else if("search".equals(action)) {
		String dmnoStr = request.getParameter("dmno");
		int dmno = 100;
		if(dmnoStr!=null) {
			dmno = Integer.parseInt(dmnoStr);
			if(dmno>270||dmno<100) {
				dmno = 100;
			}
		}
		dmno dm = dao.SelectDepartment(dmno);
		request.setAttribute("dm", dm);
		url = "";
		
	}else if("")
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
