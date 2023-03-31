package lab.web.servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.dao.EmpDAO;
import lab.web.vo.DeptVO;
import lab.web.vo.EmpVO;
import lab.web.vo.JobVO;


@WebServlet("/JDBC.do")
public class JDBCsevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmpDAO dao;
	@Override
	public void init(ServletConfig config) throws ServletException {
		/*
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("드라이버 로드 성공!");
		}
		catch(ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
		}
		 */
		dao = new EmpDAO();
	}

	public JDBCsevlet() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//		Connection con = null;
		//		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		//		String id = "hr";
		//		String pw = "hr";
		//		EmpVO emp = new EmpVO();
		//		try {
		//			con = DriverManager.getConnection(url,id,pw);
		String action = request.getParameter("action");
		String url = "";
		if("list".equals(action)) {
			ArrayList<EmpVO> list = dao.selectEmployeeList();
			request.setAttribute("list",list);
			url="/hr/list.jsp";
		}else if("search".equals(action)) {
			String empnoStr = request.getParameter("empno");
			int empno = 100;
			if(empnoStr!=null) {
				empno = Integer.parseInt(empnoStr);
				if(empno>206 || empno<100) {
					empno = 100;
				}
			}
			EmpVO emp = dao.selectEmloyee(empno);
			request.setAttribute("emp", emp);
			url = "/hr/employee.jsp";
		}else if("view".equals(action)) {
			int empId = Integer.parseInt(request.getParameter("empId"));
			EmpVO emp = dao.selectEmloyee(empId);
			request.setAttribute("emp", emp);
			url = "/hr/view.jsp";
		}



		else if ("insert".equals(action)) {
			ArrayList<EmpVO> manList = dao.selectAllManagers();
			ArrayList<DeptVO> deptList = dao.selectAllDepts();
			ArrayList<JobVO> jobList = dao.selectAllJobs();
			request.setAttribute("manList", manList);
			request.setAttribute("deptList", deptList);
			request.setAttribute("jobList", jobList);
			request.setAttribute("message", "입력");
			request.setAttribute("action", action);
			url = "/hr/insert.jsp";
		}


		else if("update".equals(action)) {
			int empId = Integer.parseInt(request.getParameter("empId"));
			EmpVO emp = dao.selectEmloyee(empId);
			ArrayList<EmpVO> manList = dao.selectAllManagers();
			ArrayList<DeptVO> deptList = dao.selectAllDepts();
			ArrayList<JobVO> jobList = dao.selectAllJobs();
			request.setAttribute("manList", manList);
			request.setAttribute("deptList", deptList);
			request.setAttribute("jobList", jobList);
			request.setAttribute("emp", emp);
			request.setAttribute("message", "수정");
			request.setAttribute("action", action);
			url = "/hr/insert.jsp";
		}else if("delete".equals(action)) {
			String sempId = request.getParameter("empId");
			int empId = Integer.parseInt(sempId);
			request.setAttribute("empId", empId);
			url="/hr/delete.jsp";
		}

		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);

		/*
			//sql에 물음표 넣는 이유 : 검색어를 뭐라고할지 모르기때문에
			//사용자가 입력하는 변수 값을 ?로 지정
			String sql = "select * from employees where employee_id=?";
			//PreparedStatement = 쿼리 세팅 및 실행. 커넥션을 통해서 생성되어야 하기 때문에
			//커넥션의 PreparedStatement 메서드를 통해 실행. 매개변수로 sql문 필요
			PreparedStatement stmt = con.prepareStatement(sql);
			//stmt안의 set데이터 타입 메서드 = 쿼리문 의 ? 세팅.
			//몇 번째 물음표를, 해당 값으로
			stmt.setInt(1, empno);
			//실행은 executeQuery or executeUpdate - 쿼리의 종류에 따라 다름
			//executeUpdate - 트랜잭션=>insert update delete 쿼리에 사용 - 결과값이 숫자
			//executeQuery - select 쿼리에 사용 - 결과값이 ResultSet(테이블형)
			ResultSet rs = stmt.executeQuery();
			//커서를 옮기는 메서드 next() = 데이터가 있으면 true, 없으면 false 리턴
			if(rs.next()) {
				emp.setEmployeeId(rs.getInt("employee_Id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble(9));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));

			}
		}catch(SQLException e) {
			e.printStackTrace();

		}finally {
			try{if(con!=null) con.close();}catch(SQLException e) {}
		}
		 */
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if("insert".equals(action)) {
			int empId = Integer.parseInt(request.getParameter("empId"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			String sdate = request.getParameter("hireDate");
			SimpleDateFormat parsing = new SimpleDateFormat("yyyy-MM-dd");

			java.sql.Date hireDate = null;
			try {
				hireDate = new java.sql.Date(parsing.parse(sdate).getTime());
			}catch(ParseException e) {

			}
			String jobId = request.getParameter("jobId");
			double salary = Double.parseDouble(request.getParameter("salary"));
			double commissionPct = Double.parseDouble(request.getParameter("commissionPct"));
			int managerId = Integer.parseInt(request.getParameter("managerId"));
			int departmentId = Integer.parseInt(request.getParameter("departmentId"));
			EmpVO emp = new EmpVO();
			emp.setEmployeeId(empId); emp.setFirstName(firstName);
			emp.setLastName(lastName); emp.setEmail(email);
			emp.setPhoneNumber(phoneNumber); emp.setHireDate(hireDate);
			emp.setJobId(jobId); emp.setSalary(salary);
			emp.setCommissionPct(commissionPct); emp.setManagerId(managerId);
			emp.setDepartmentId(departmentId);
			dao.insertEmployee(emp);
			response.sendRedirect("/JDBC/JDBC.do?action=list");
		}else if("delete".equals(action)) {
		String sempId = request.getParameter("empId");
		int empId = Integer.parseInt(sempId);
		String email = request.getParameter("email");
		dao.deleteEmloyee(empId, email);
		response.sendRedirect("/JDBC/JDBC.do?action=list");
		}
	}
}
