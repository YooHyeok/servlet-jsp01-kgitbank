package lab.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import lab.web.vo.DeptVO;
import lab.web.vo.EmpVO;
import lab.web.vo.JobVO;

public class EmpDAO {
	//한번만 실행되는 생성자.
	static {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		}catch(SQLException e) {
			System.out.println("드라이버 로드 실패");
		}
	}

	private Connection getConnection() {
		DataSource ds = null;
		Connection con = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			con = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	private void closeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close();
			}catch(SQLException e) {

			}
		}

	}
	public EmpVO selectEmloyee(int empId) {
		Connection con = null;
		EmpVO emp = new EmpVO();
		try {
			con = getConnection();
			String sql = "select * from employees where employee_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, empId);
			ResultSet rs = stmt.executeQuery();
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
			closeConnection(con);
		}
		return emp;
	}
	public ArrayList<EmpVO> selectEmployeeList(){
		Connection con = null;
		ArrayList<EmpVO> list = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select employee_id, first_name||''||last_name as name, salary, phone_number, email from employees";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("name"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setPhoneNumber(rs.getString(4));
				emp.setEmail(rs.getString("email"));
				list.add(emp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return list;
	}
	public ArrayList<DeptVO> selectAllDepts(){
		ArrayList<DeptVO> list = new ArrayList<>();
		Connection con = null;
		try {
			con = getConnection();
			String sql = "select department_id, department_name from departments";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				DeptVO dept = new DeptVO();
				dept.setDepartmentId(rs.getInt(1));
				dept.setDepartmentName(rs.getString(2));
				list.add(dept);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return list;

	}
	public ArrayList<JobVO> selectAllJobs(){
		ArrayList<JobVO> list = new ArrayList<>();
		Connection con = null;
		try {
			con = getConnection();
			String sql = "select job_id, job_title from jobs";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				JobVO job = new JobVO();
				job.setJobId(rs.getString(1));
				job.setJobTitle(rs.getString(2));
				list.add(job);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return list;

	}
	public ArrayList<EmpVO> selectAllManagers(){
		ArrayList<EmpVO> list = new ArrayList<>();
		Connection con = null;
		try {
			con = getConnection();
			String sql = "select m.manager_id, first_name||' '||last_name as name " + 
					"from employees e " + 
					"join (select distinct manager_id from employees) m " + 
					"on e.employee_id = m.manager_id";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				EmpVO man = new EmpVO();
				//매니저아이디를 임플로이아이디에 넣음
				//이름을 넣음
				man.setEmployeeId(rs.getInt("manager_id"));
				man.setFirstName(rs.getString("name"));
				list.add(man);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}finally {

		}
		return list;
	}
	public void insertEmployee(EmpVO emp) {
		Connection con = null;
		try {
			con = getConnection();
			String sql = "insert into employees values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, emp.getEmployeeId());
			stmt.setString(2, emp.getFirstName());
			stmt.setString(3, emp.getLastName());
			stmt.setString(4, emp.getEmail());
			stmt.setString(5, emp.getPhoneNumber());
			stmt.setDate(6, emp.getHireDate());
			stmt.setString(7, emp.getJobId());
			stmt.setDouble(8, emp.getSalary());
			stmt.setDouble(9, emp.getCommissionPct());
			stmt.setInt(10, emp.getManagerId());
			stmt.setInt(11, emp.getDepartmentId());
			int result = stmt.executeUpdate();
			if(result<=0) {
				throw new RuntimeException("입력되지 않았습니다.");

			}
		}catch(SQLException e) {
			if(e.getMessage().contains("unique")) {
				throw new RuntimeException("중복되는 값이 있습니다.");
			}
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
	}
	public void deleteEmloyee(int empId, String email) {
		Connection con = null;
		try {
			con = getConnection();
			String sql = "delete from employees where employee_id=? and email=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, empId);
			stmt.setString(2, email);
			int result = stmt.executeUpdate();
			if(result<=0) {
				throw new RuntimeException("삭제되지 않았습니다.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
		closeConnection(con);
	}
}


}

















