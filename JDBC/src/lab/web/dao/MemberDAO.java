package lab.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import lab.web.vo.MemberVO;

public class MemberDAO {
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
	public void insertMember(MemberVO mem) {
		Connection con = null;
		try {
			con = getConnection();
			String sql = "insert into member values(?,?,?,?,?)" ;
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mem.getUserid());
			stmt.setString(2, mem.getName());
			stmt.setString(3, mem.getPassword());
			stmt.setString(4, mem.getEmail());
			stmt.setString(5, mem.getAddress());
			int result = stmt.executeUpdate();
			if(result<=0) {
				throw new RuntimeException("입력이 되지 않았습니다.");
			}
			}catch(SQLException e) {
			e.printStackTrace();
			}finally {
				closeConnection(con);
		}
	}
	public String getPassword(String userid) {
		Connection con = null;
		String pw = null;
		try {
			con = getConnection();
			String sql = "select password from member where userid=?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1, userid);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				pw=rs.getString(1);
				
			}else {
				throw new RuntimeException("아이디가 없습니다.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			closeConnection(con);
		}
		return pw;
		
	}
}
