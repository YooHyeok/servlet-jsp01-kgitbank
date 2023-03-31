package lab.web.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import lab.web.model.MemberVO;

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
		}catch(SQLException e) {
			if(e.getMessage().contains("unique")) {
				throw new RuntimeException("아이디가 중복됩니다.");
			}else {
				e.printStackTrace();
				throw new RuntimeException("MemberDAO.insert()예외발생-콘솔확인");

			}
		}finally {
			closeConnection(con);
		}
	}
	public MemberVO selectMember(String userid) {
		Connection con = null;
		MemberVO member = new MemberVO();
		try {
			con = getConnection();
			String sql = "select * from member where userid=?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1, userid);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				member.setUserid(userid);
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setAddress(rs.getString("address"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("MemberDAO.insert()예외발생-콘솔확인");
		}finally {
			closeConnection(con);
		}
		return member;
	}
	public void updateMember(MemberVO member) {
		Connection con = null;
		try {
			con = getConnection();
			String sql = "update member set email=?, address=?, name=?,"
					+" password=? where userid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getAddress());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPassword());
			pstmt.setString(5, member.getUserid());
			pstmt.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("MemberDAO.insert()예외발생-콘솔확인");
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
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, userid);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				pw=rs.getString(1);

			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("MemberDAO.insert()예외발생-콘솔확인");


		}finally {
			closeConnection(con);
		}
		return pw;
	}
	public void deleteMember(String userid, String password) {
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			if(password.equals(getPassword(userid))) {
				String sql = "delete from board masterid in (select masterid from board where userid=?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, userid);
				stmt.executeUpdate();
				String sql2 = "delete from member where userid=?";
				stmt = con.prepareStatement(sql2);
				stmt.setString(1, userid);
				stmt.executeUpdate();
				con.commit();
			}else {
				throw new RuntimeException("비밀번호가 다릅니다.");
			}
		}catch(SQLException e) {
			try {
				con.rollback();
			}catch(SQLException e1) {
				
			}
			e.printStackTrace();
			throw new RuntimeException("MemberDAO.insert()예외발생-콘솔확인");
		}finally {
			closeConnection(con);
		}
	}
}

		
		
		
		
		
		
		
		
		