package com.itwillbs.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ComCdDAO {
	// 1,2 단계 디비연결 메서드
	// 예외처리를 메서드 호출한곳으로 뒤로 미루겠다
	public Connection getConnection() throws Exception{
//		//1단계 JDBC안에 있는 Driver 프로그램 불러오기
//		Class.forName("com.mysql.cj.jdbc.Driver") ;
//		//2단계 Driver 프로그램 이용해서 디비연결
//		String dbUrl="jdbc:mysql://localhost:3306/jspdb1";
//		String dbUser="root";
//		String dbPass="1234";
//		Connection con=DriverManager.getConnection(dbUrl, dbUser, dbPass);
//		return con;
		
		//서버에서 미리 1, 2 단계 => 디비연결 => 이름을 불러 연결정보를 가져오기
		// => 속도 향상, 디비연결 정보 수정 최소화
		// DataBase Connection Pool (DBCP)=> 디비 연결정보 서버 저장
		// 1. META-INF context.xml (디비연결정보)
		// 2. MemberDAO 디비연결정보 불러서 사용
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
		Connection con=ds.getConnection();
		return con;
	}
	
	
	// MemberDTO 리턴할형 getMember(String id) 메서드 정의
	public ComCdDTO getComCd(String cd) {
		ComCdDTO dto=null;
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			//1,2 디비연결 메서드
			con=getConnection();
			
			//3단계 SQL구문 만들어서 실행할 준비(select 조건 where id=?)
			String sql="select cd_grp, cd_grp_nm, cd, cd_nm from com_code where cd=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, cd);

			//4단계 SQL구문을 실행(select) => 결과 저장
			rs=pstmt.executeQuery();
			//5단계 결과를 출력, 데이터 담기 (select)
			// next() 다음행 => 리턴값 데이터 있으면 true/ 데이터 없으면 false
			//조건이 true 실행문=> 다음행 데이터 있으면 true =>  열접근 출력
			if(rs.next()){
				//next() 다음행 => 리턴값 데이터 있으면 true/ 아이디 일치
				// 바구니 객체생성 => 기억장소 할당
				dto=new ComCdDTO();
				// set메서드호출 바구니에 디비에서 가져온 값 저장
				dto.setCdGrp(rs.getString("cd_grp"));
				dto.setCdGrpNm(rs.getString("cd_grp_nm"));
				dto.setCd(rs.getString("cd"));
				dto.setCdNm(rs.getString("cd_nm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return dto;
	}//getMember()
	
	
	   // 리턴할형 ArrayList<MemberDTO>  getMemberList() 메서드 정의 -- 공통코드.
	   public ArrayList<ComCdDTO> getComCdList(String cdGrpnm){
	      ArrayList<ComCdDTO> memberList=new ArrayList<ComCdDTO>();
	      Connection con =null;
	      PreparedStatement pstmt=null;
	      ResultSet rs=null;
	      try {
	         //1,2 디비연결 메서드
	         con=getConnection();
	         //3단계 SQL구문 만들어서 실행할 준비(select)
	         String sql ="select * from com_code where cd_grp_nm = ?";
	         pstmt=con.prepareStatement(sql);
	         pstmt.setString(1, cdGrpnm);
	         
	         //4단계 SQL구문을 실행(select) => 결과 저장
	         rs=pstmt.executeQuery();   
	         //5단계   //조건이 true 실행문=> 다음행 데이터 있으면 true 
	         //     =>  열접근 => 한 명 정보 MemberDTO 저장 => 배열한칸 저장 
	         while(rs.next()) {
	            // MemberDTO 객체생성
	            ComCdDTO dto=new ComCdDTO();
	            // set메서드 호출 <= 열접근
	            dto.setCdGrp(rs.getString("cd_grp"));
	            dto.setCdGrpNm(rs.getString("cd_grp_nm"));
	            dto.setCd(rs.getString("cd"));
	            dto.setCdNm(rs.getString("cd_nm"));
	            // 배열 한칸에 회원정보주소 저장
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
	   }//getMemberList()
	
	// 리턴할형 ArrayList<MemberDTO>  getMemberList() 메서드 정의 -- 공통코드.
	public ArrayList<ComCdDTO> getComCdListByCdGrp(String cdGrp){
		ArrayList<ComCdDTO> memberList=new ArrayList<ComCdDTO>();
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			//1,2 디비연결 메서드
			con=getConnection();
			//3단계 SQL구문 만들어서 실행할 준비(select)
			String sql ="select cd_grp, cd_grp_nm, cd, cd_nm from com_code where cd_grp = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, cdGrp);
			
			//4단계 SQL구문을 실행(select) => 결과 저장
			rs=pstmt.executeQuery();	
			//5단계	//조건이 true 실행문=> 다음행 데이터 있으면 true 
			//     =>  열접근 => 한 명 정보 MemberDTO 저장 => 배열한칸 저장 
			while(rs.next()) {
				// MemberDTO 객체생성
				ComCdDTO dto=new ComCdDTO();
				System.out.println("회원정보저장 주소 : "+dto);
				// set메서드 호출 <= 열접근
				dto.setCdGrp(rs.getString("cd_grp"));
				dto.setCdGrpNm(rs.getString("cd_grp_nm"));
				dto.setCd(rs.getString("cd"));
				dto.setCdNm(rs.getString("cd_nm"));
				// 배열 한칸에 회원정보주소 저장
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
	}//getComCdListByCdGrp()
	
}//클래스

