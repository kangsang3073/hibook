package com.itwillbs.report.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class ReportDAO {
	public Connection getConnection() throws Exception{
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
	
	public void insertreport(ReportDTO dto) {
		System.out.println("ReportDAO insertreport()");
		
		Connection con=null;
		PreparedStatement pstmt=null;
//		ResultSet rs=null;
		try {
			// 예외가 발생할 가능성이 높은 명령(1~4단계)
			// 1~2 단계
			con=getConnection();
		
			
			//3  최대 mem_id + 1 
//			String sql="select max(mem_id) from mem_report";
//			pstmt=con.prepareStatement(sql);
			//4
//			rs=pstmt.executeQuery();
			//5
			
			// 3단계 SQL구문 만들어서 실행할 준비(insert)
			String sql="insert into mem_report (title,content,content_img1,insert_id,insert_date) values(?,?,?,?,now())";
			pstmt=con.prepareStatement(sql);
			// ? 채워넣기		
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getContent_img1());
			pstmt.setString(4, dto.getInsert_id());
			
			// 4단계 SQL구문을 실행(insert,update,delete)
			pstmt.executeUpdate();

		} catch (Exception e) {
			// 예외가 발생하면 처리하는 곳
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if (pstmt != null)
				try {pstmt.close();} catch (Exception e2) {}{}
			if (con != null)
				try {con.close();} catch (Exception e2) {}{}
		}
	}//insertreport() 메서드
	
	// 리턴할형 ArrayList<reportDTO>  getreportList(int startRow,int pageSize) 메서드 정의 
	public ArrayList<ReportDTO> getAdminReportList(int startRow,int pageSize){
		System.out.println("reportDAO getreportList()");
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ReportDTO> ReportList=new ArrayList<>();
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			// 기본 num기준 오름차순 => 최근글 위로 올라오게 정렬 (num 내림차순)
//			String sql="select * from report order by num desc";
//			String sql="select * from report order by num desc limit 시작행-1, 몇개";
			String sql="select * from mem_report order by report_id desc limit ?, ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			//4
			rs=pstmt.executeQuery();
			//5
			while(rs.next()) {
				// 하나의 글의 바구니에 저장
				ReportDTO dto=new ReportDTO();
				dto.setReport_id(rs.getInt("report_id"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setContent_img1(rs.getString("content_img1"));
				dto.setInsert_id(rs.getString("insert_id"));
				dto.setInsert_date(rs.getTimestamp("insert_date"));
				// 바구니의 주소값을 배열 한칸에 저장
				ReportList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return ReportList;
	}//
	
	public ArrayList<ReportDTO> getUserReportList(String id, int startRow,int pageSize){
		System.out.println("reportDAO getreportList()");
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ReportDTO> ReportList=new ArrayList<>();
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			// 기본 num기준 오름차순 => 최근글 위로 올라오게 정렬 (num 내림차순)
//			String sql="select * from report order by num desc";
//			String sql="select * from report order by num desc limit 시작행-1, 몇개";
			String sql="select * from mem_report where insert_id = ? order by report_id desc limit ?, ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow-1);
			pstmt.setInt(3, pageSize);
			//4
			rs=pstmt.executeQuery();
			//5
			while(rs.next()) {
				// 하나의 글의 바구니에 저장
				ReportDTO dto=new ReportDTO();
				dto.setReport_id(rs.getInt("report_id"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setContent_img1(rs.getString("content_img1"));
				dto.setInsert_id(rs.getString("insert_id"));
				dto.setInsert_date(rs.getTimestamp("insert_date"));
				// 바구니의 주소값을 배열 한칸에 저장
				ReportList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return ReportList;
	}//
	
	// 리턴할형 reportDTO  getreport(int num) 메서드 정의 
	public ReportDTO getreport(int report_id) {
		System.out.println("ReportDAO getReport()");
		ReportDTO dto=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="select * from mem_report where report_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, report_id);
			//4
			rs=pstmt.executeQuery();
			//5
			while(rs.next()) {
				// 하나의 글의 바구니에 저장
				dto=new ReportDTO();
				dto.setReport_id(rs.getInt("report_id"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setContent_img1(rs.getString("content_img1"));
				dto.setInsert_id(rs.getString("insert_id"));
				dto.setInsert_date(rs.getTimestamp("insert_date"));
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
	}//
	
	// 리턴할형 없음 updatereport(reportDTO dto) 메서드 정의
	public void updatereport(ReportDTO dto) {
		System.out.println("reportDAO updatereport()");
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="update mem_report set title=?, content=? ,content_img1=? where report_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getContent_img1());
			pstmt.setInt(4, dto.getReport_id());
			;
			
			// 4단계 SQL구문을 실행(insert,update,delete)
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
	}//
	
	// 리턴할형 없음 deletereport(int num) 메서드 정의
	public void deletereport(int report_id) {
		System.out.println("reportDAO deletereport()");
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="delete from mem_report where report_Id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,report_id);
			// 4단계 SQL구문을 실행(insert,update,delete)
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
	}//
	
	// int 리턴할형 getreportCount() 메서드 정의 
	public int getAdminReportCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="select count(*) from mem_report";
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
	}//
	
	public int getUserReportCount(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="select count(*) from mem_report where insert_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
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
	}//
	
}//클래스
