package co.min.board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.min.board.vo.MemberVO;

public class MemberDao extends DAO {
	
	private PreparedStatement psmt; // sql 명령문 실행
	private ResultSet rs; // select 후에 결과셋 받기 
	private MemberVO vo;
	
	// 변경 못하게 final 상수로 선언
	private final String SELECT_ALL = "SELECT * FROM MEMBER"; 
	private final String SELECT = "SELECT * FROM MEMBER WHERE ID = ? AND PASSWORD = ?";
	// private final String SELECT = "SELECT id, password FROM MEMBER "; 아이디, 비밀번호 따로 검사 하려면?
	private final String INSERT = "INSERT INTO MEMBER(ID, NAME, PASSWORD, ADDRESS, TEL, ENTERDATE) VALUES(? , ? , ? , ? , ? , ?)";
	private final String UPDATE = "UPDATE MEMBER SET NAME = ?, PASSWORD = ?, ADDRESS = ?, TEL = ? WHERE ID = ?";
	private final String DELETE = "DELETE FROM MEMBER WHERE ID = ?";
	
	
	public List<MemberVO> selectAll() { 
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			psmt = conn.prepareStatement(SELECT_ALL); // DAO를 상속받고 있어서 conn 정의 안해줘도 사용 가능
			rs = psmt.executeQuery(); //executeQuery는 반환값이 resultSet, executeUpdate는 반환값이 int타입
			
			while (rs.next()) {

				vo = new MemberVO();
				
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setEnterdate(rs.getDate("enterdate"));
				vo.setAuthor(rs.getString("author"));
				
				list.add(vo);
				
			}
		}catch( SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return list;
	}
	
	
	// 1개의 행 select
	public MemberVO select(MemberVO vo) {
		try {
			psmt = conn.prepareStatement(SELECT);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPassword());
			
			rs = psmt.executeQuery();
			
			// 한 행만 리턴하니까 while 사용 할 필요x
			if(rs.next()) {
				// loginCheck(); 여기서 로그인 체크 해도 됨
				vo.setName(rs.getString("name"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setEnterdate(rs.getDate("enterdate"));
				vo.setAuthor(rs.getString("author"));
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
	}
	
	
	// Member 테이블 insert 하기
	public int insert(MemberVO vo) {
		
		int n = 0;
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			psmt = conn.prepareStatement(INSERT);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getPassword());
			psmt.setString(4, vo.getAddress());
			psmt.setString(5, vo.getTel());
			psmt.setDate(6, vo.getEnterdate());
			
			n = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return n;
	}
	
	
	public int update(MemberVO vo) {
		int n = 0;
		
		try {
			psmt = conn.prepareStatement(UPDATE);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getPassword());
			psmt.setString(3, vo.getAddress());
			psmt.setString(4, vo.getTel());
			psmt.setString(5, vo.getId());
			
			n = psmt.executeUpdate();
		}catch( SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}
	
	public int delete(MemberVO vo) {
		int n = 0;
		try {
			psmt = conn.prepareStatement(DELETE);
			psmt.setString(1, vo.getId());
			
			n = psmt.executeUpdate();
			
		}catch( SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return n;
	}
	
	
	private void close() {
		// db 연결 끊어주기
		// 닫을 때는 열었을 때의 반대 순서
		
		// DBCP pull 쓰면? close() 안해도 됨
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
