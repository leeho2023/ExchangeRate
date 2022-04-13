package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExchangeRateDB {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public void dbconnection() {
		
		String url = "jdbc:mysql://localhost:3306/ExchangeRateDB?useSSL=false";
		String id = "root";
		String pw = "rootroot";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void dbclose() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insert(String mID, String mPW) {

		dbconnection();
		String sql = "insert into memberTBL(mID, mPW) values (?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mID);
			pstmt.setString(2, mPW);
			pstmt.executeUpdate();
			System.out.println("MemberDTO insert() 메서드 실행 완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbclose();
		}

	}
	
	public int checkUserID(String mID) {

		dbconnection();
		String sql = "select count(*) from memberTBL where mID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String test = rs.getString(1);
				if(test.equals("0")) {
					System.out.println("아이디가 없습니다.");
					return 0;
				}else {
					System.out.println("로그인 성공");
					return 1;
					
				}
			}
			System.out.println("MemberDTO updateID() 메서드 실행 완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbclose();
		}
		return 0;

	}
	
	public int checkUser(String mID, String mPW) {

		dbconnection();
		String sql = "select count(*) from memberTBL where mID = ? and mPW = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mID);
			pstmt.setString(2, mPW);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String test = rs.getString(1);
				if(test.equals("0")) {
					System.out.println("아이디가 없습니다.");
					return 0;
				}else {
					System.out.println("로그인 성공");
					return 1;
					
				}
			}
			System.out.println("MemberDTO updateID() 메서드 실행 완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbclose();
		}
		return 0;

	}
	
}
