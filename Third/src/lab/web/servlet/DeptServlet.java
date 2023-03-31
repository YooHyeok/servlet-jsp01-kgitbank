package lab.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.model.DeptVO;
import lab.web.model.EmpVO;
import lab.web.model.HrDAO;


@WebServlet("/Dept.do")
public class DeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HrDAO dao;
 
    public DeptServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		dao = new HrDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String url = "";
		if("dept".equals(action)) {
			ArrayList<DeptVO> list = dao.selectDeptList();
			request.setAttribute("list", list);
			url = "/deptList.jsp";
		}else if("deptEmp".equals(action)){
			int deptId = Integer.parseInt(request.getParameter("deptId"));
			ArrayList<EmpVO> list = dao.selectEmployeeByDeptId(deptId);
			request.setAttribute("list", list);
			url = "/list.jsp";
		}
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
