package Homepage_module;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.sql.*;



import Homepage_module.MemberBean;


public class MemberMng {

	private DBConnectionMgr pool;
	public MemberMng() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ID 중복확인
	public boolean checkId(String id) {
		
		Connection con = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로드 성공!");
			con = pool.getConnection();
            System.out.println("데이터베이스 접속 성공!");
            String Sql="select * from tablemember where id=?";
            //테이블에서 아이디를 찾아서 중복 되는 게 있는 지 확인
			stmt = con.prepareStatement(Sql);
            stmt.setString(1,id);
            flag=stmt.executeQuery().next();
          
        
			
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con!=null) try {con.close();} catch(SQLException e) {} 
			pool.freeConnection(con,stmt,rs);
		}
		return flag;
	}

	

	// 회원가입
	public boolean insertMember(MemberBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			
			con = pool.getConnection();
           
			sql = "insert tablemember(id,pwd,name,gender,birthday,email"
					+",zipcode,address,detailaddress)values(?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPwd());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getGender());
			pstmt.setString(5, bean.getBirthday());
			pstmt.setString(6, bean.getEmail());
			pstmt.setString(7, bean.getZipcode());
			pstmt.setString(8, bean.getAddress());
			pstmt.setString(9, bean.getDetailaddress());
			

			if (pstmt.executeUpdate() == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

	// 로그인
	public boolean loginMember(String id, String pwd) {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql =null;
		boolean flag =false;
		try {
			con=pool.getConnection();
			sql="select id from tablemember where id= ? and pwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,pwd);
			rs=pstmt.executeQuery();
			flag =rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return flag;
	}


	

	
}