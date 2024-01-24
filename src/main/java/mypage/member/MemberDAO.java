package mypage.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mypage.board.BoardDTO;

public class MemberDAO {
	// DB연결 메서드
	public Connection getConnection() throws Exception{	
		// 서버에서 미리 1,2단계 => 디비연결 => 이름을 불러 연결정보를 가져오기 
		// META-INF context.xml (디비연결정보) 
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
		Connection con = ds.getConnection();
		return con;
	}
	
	// 회원정보-프로필사진, 닉네임(Members 테이블) 가져오는 메서드 
	public MemberDTO getMemberImg(String id) {
		// 바구니 주소가 저장되는 변수에 null 초기화
		MemberDTO dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1,2단계 디비연결 메서드 호출 
			con = getConnection();
			
			//3단계 SQL구문 만들어서 실행할 준비 (select where id=?)
			String sql = "select * from members where mem_id=?";
			pstmt = con.prepareStatement(sql);
			//? 채워넣기
				pstmt.setString(1, id); 

			//4단계 SQL구문을 실행(select) => 결과 저장 
			rs = pstmt.executeQuery();

			//5단계 결과를 출력, 데이터 담기 (select)
			if(rs.next()) {
			// id, pass 일치하면 MemberDTO 바구니에 데이터 담아서 가져오기 
			//	바구니 객체생성 dto => 기억장소 할당 
				dto = new MemberDTO();
				dto.setMem_img(rs.getString("mem_img"));
				dto.setNickname(rs.getString("nickname"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
		}
		return dto;		
	}
	
	// 회원정보(Members 테이블) 가져오는 메서드 
	public MemberDTO getMember(String id) {
		// 바구니 주소가 저장되는 변수에 null 초기화
		MemberDTO dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1,2단계 디비연결 메서드 호출 
			con = getConnection();
			
			//3단계 SQL구문 만들어서 실행할 준비 (select where id=?)
			String sql = "select * from members where mem_id=?";
			pstmt = con.prepareStatement(sql);
			//? 채워넣기
				pstmt.setString(1, id); 

			//4단계 SQL구문을 실행(select) => 결과 저장 
			rs = pstmt.executeQuery();

			//5단계 결과를 출력, 데이터 담기 (select)
			if(rs.next()) {
			// id, pass 일치하면 MemberDTO 바구니에 데이터 담아서 가져오기 
			//	바구니 객체생성 dto => 기억장소 할당 
				dto = new MemberDTO();
				dto.setMem_id(rs.getString("mem_id"));
				dto.setMem_nm(rs.getString("mem_nm"));
				dto.setPhone(rs.getString("phone"));
				dto.setBirth(rs.getString("birth"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddr(rs.getString("addr"));
				dto.setAddr_dtl(rs.getString("addr_dtl"));
				dto.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
		}
		return dto;		
	}
	
	// 회원정보-프로필사진, 닉네임(Members 테이블) 업데이트하는 메서드  
	public void updateMemberImg(MemberDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {			
			// 1,2단계 디비연결 메서드 호출 
			con = getConnection();
			
			//3단계 SQL구문 만들어서 실행할 준비 
			String sql = "update members set mem_img=?, nickname=? where mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMem_img()); 
			pstmt.setString(2, dto.getNickname()); 
			pstmt.setString(3, dto.getMem_id()); 

			//4단계 SQL구문을 실행
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}	
	}
	
	// 회원정보 (Members 테이블) 업데이트하는 메서드  
	public void updateMember(MemberDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {			
			// 1,2단계 디비연결 메서드 호출 
			con = getConnection();
			
			//3단계 SQL구문 만들어서 실행할 준비 
			String sql = "update members set mem_pass=?, phone=?, zipcode=?, addr=?, addr_dtl=?, email=? where mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMem_pass()); 
			pstmt.setString(2, dto.getPhone()); 
			pstmt.setString(3, dto.getZipcode()); 
			pstmt.setString(4, dto.getAddr()); 
			pstmt.setString(5, dto.getAddr_dtl()); 
			pstmt.setString(6, dto.getEmail()); 
			pstmt.setString(7, dto.getMem_id()); 

			//4단계 SQL구문을 실행
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}	
	}
	
	// 회원탈퇴시 비밀번호 맞는지 체크하는 메서드 
	public MemberDTO userCheck(String id, String pass) {
		// 바구니 주소가 저장되는 변수에 null 초기화
		MemberDTO dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1,2단계 디비연결 메서드 호출 
			con = getConnection();
			// 3단계 SQL구문 만들어서 실행할 준비(select where id=? and pass=?)
			String sql = "select * from members where mem_id=? and mem_pass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); 
			pstmt.setString(2, pass); 

			// 4단계 SQL구문을 실행(select) => 결과 저장
			rs = pstmt.executeQuery();

			//5단계 결과를 출력, 데이터 담기 (select)
			// if next() 다음행 => 리턴값 데이터 있으면 true => 아이디 비밀번호 일치
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setMem_id(rs.getString("id"));
				dto.setMem_pass(rs.getString("pass"));
			}else {
				// id, pass 틀리면 MemberDTO 빈(null) 바구니 가져오기 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
		}
		return dto;
	}
	
	// 회원탈퇴처리 : 1.회원테이블 회원상태=탈퇴, 회원탈퇴일자=now() update 메서드
	public void updateMember(String id) {
		System.out.println("updateMember");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {			
			// 1,2단계 디비연결 메서드 호출 
			con = getConnection();
			
			//3단계 SQL구문 만들어서 실행할 준비 
			String sql = "update members set mem_st='탈퇴', delete_date=now() where mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			//4단계 SQL구문을 실행
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}	
	}
	
//	// 회원탈퇴처리 : 2.회원테이블 회원id 삭제
//	public void deleteMember(String id) {
//		System.out.println("deleteMember");
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {			
//			// 1,2단계 디비연결 메서드 호출 
//			con = getConnection();
//			
//			//3단계 SQL구문 만들어서 실행할 준비 
//			String sql = "delete from members where mem_id=?";			
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, id); 
//
//			//4단계 SQL구문을 실행
//			pstmt.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
//			if(con!=null) try { con.close();} catch (Exception e2) {}
//		}	
//	}
	
	// 관리자페이지- 전체 회원목록 불러오는 메서드 
	public ArrayList<MemberDTO> getMemberList(int startRow,int pageSize){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<MemberDTO> memberList=new ArrayList<>();
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="select * from members limit ?, ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			//4
			rs=pstmt.executeQuery();
			//5
			while(rs.next()) {
				// 하나의 글의 바구니에 저장
				MemberDTO dto=new MemberDTO();
				dto.setMem_id(rs.getString("mem_id"));
				dto.setMem_nm(rs.getString("mem_nm"));
				dto.setNickname(rs.getString("nickname"));
				dto.setBirth(rs.getString("birth"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setMem_st(rs.getString("mem_st"));
				dto.setJoin_date(rs.getTimestamp("join_date"));
				dto.setDelete_date(rs.getTimestamp("delete_date"));
				// 바구니의 주소값을 배열 한칸에 저장
				memberList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return memberList;
	}
	
	// 전체 회원수 구하는 메서드 (페이징 처리를 위해)
	public int getMemberCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="select count(*) from members";
			pstmt=con.prepareStatement(sql);
			//4
			rs=pstmt.executeQuery();
			//5
			if(rs.next()) {
				count=rs.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
				if(rs!=null) try { rs.close();} catch (Exception e2) {}
				if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
				if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return count;
	}
	
	// 1:1신고 회원 정지시 id 체크하는 메서드 
	public MemberDTO userCheck(String id) {
		// 바구니 주소가 저장되는 변수에 null 초기화
		MemberDTO dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1,2단계 디비연결 메서드 호출 
			con = getConnection();
			// 3단계 SQL구문 만들어서 실행할 준비(select where id=?)
			String sql = "select * from members where mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); 

			// 4단계 SQL구문을 실행(select) => 결과 저장
			rs = pstmt.executeQuery();

			//5단계 결과를 출력, 데이터 담기 (select)
			if(rs.next()) {
			// id, pass 일치하면 MemberDTO 바구니에 데이터 담아서 가져오기 
				dto = new MemberDTO();
				dto.setMem_id(rs.getString("mem_id"));
			}else {
				// id, pass 틀리면 MemberDTO 빈(null) 바구니 가져오기 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
		}
		return dto;
	}
	
	// 1:1신고 접수받은 회원상태 '정지'로 업데이트하는 메서드 
	public void updateMemberSt(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {			
			// 1,2단계 디비연결 메서드 호출 
			con = getConnection();
			
			//3단계 SQL구문 만들어서 실행할 준비 
			String sql = "update members set mem_st='정지' where mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); 

			//4단계 SQL구문을 실행
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}	
	}
}