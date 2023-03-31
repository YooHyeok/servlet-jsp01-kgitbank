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

import lab.web.vo.DmVO;


public class DmDAO {
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
	public DmVO SelectDepartment(int dmId) {
		Connection con = null;
		DmVO dm = new DmVO();
		try {
			con = getConnection();
			String sql = "select * from departments where department_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, dmId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				dm.setDepartmentId(rs.getInt("department_Id"));
				dm.setDepartmentName(rs.getString("department_name"));
				dm.setManagerId(rs.getInt("manager_id"));
				dm.setLocationId(rs.getInt("location_id"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeConnection(con);
		}
		return dm;
		
	}
	public ArrayList<DmVO> selectDepartmentList(){
		Connection con = null;
		ArrayList<DmVO> list = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select department_id, department_name, manager_id, location_id";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				DmVO dm = new DmVO();
				dm.setDepartmentId(rs.getInt("department_Id"));
				dm.setDepartmentName(rs.getString("department_name"));
				dm.setManagerId(rs.getInt("manager_id"));
				dm.setLocationId(rs.getInt("location_id"));
				list.add(dm);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return list;
	}
	
	
}
