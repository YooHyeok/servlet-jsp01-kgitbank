package lab.web.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class HrDAO {
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
	public ArrayList<DeptVO> selectDeptList(){
		ArrayList<DeptVO> list = new ArrayList<>();
		Connection con = null;
		try {
			con = getConnection();
			String sql = "select * from departments";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs =  stmt.executeQuery();
			while(rs.next()) {
				DeptVO dept = new DeptVO();
				dept.setDeptId(rs.getInt("department_id"));
				dept.setDeptName(rs.getString("department_name"));
				dept.setManagerId(rs.getInt("Manager_id"));
				dept.setLocationId(rs.getInt("Location_Id"));
				list.add(dept);

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return list;
	}
	
	public ArrayList<EmpVO> selectEmployeeByDeptId(int deptId){
		ArrayList<EmpVO> list = new ArrayList<>();
		Connection con = null;
		try {
			con = getConnection();
			String sql = "select * from employees where department_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, deptId);
			ResultSet rs = stmt.executeQuery();
			//부서이기 때문에 while.
			while(rs.next()) {
				EmpVO emp = new EmpVO();
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
				list.add(emp);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return list;
	}
	
	
	
	
}











