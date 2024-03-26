package com.itwillbs.review.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StarReviewDAO extends MemberDAO{

		public Connection getConnection() throws Exception{
			Context init=new InitialContext();
			DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
			Connection con=ds.getConnection();
			System.out.println("connection"+con);
			return con;
		}

		public StarReviewDTO ReviewStar(String tgt_id) {
			System.out.println("MemberDAO insertMember()");
			System.out.println("MemberDTO 바구니 전달받은 주소 : " + tgt_id);
			
			StarReviewDTO dto= null;
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				// 예외가 발생할 가능성이 높은 명령(1~4단계)
				// 1~2 단계
				con=getConnection();
				// 3단계 SQL구문 만들어서 실행할 준비(insert)
				String sql="select m.nickname, m.mem_img,t.tgt_id, avg(score) from manner t join members m on t.tgt_id = m.mem_id where t.tgt_id=?";
				pstmt=con.prepareStatement(sql);
				System.out.println(pstmt);
				System.out.println(tgt_id);
				// ? 채워넣기
				pstmt.setString(1, tgt_id);	
				System.out.println(pstmt);
				// 4단계 SQL구문을 실행(insert,update,delete)
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					//next() 다음행 => 리턴값 데이터 있으면 true/ 아이디 일치
					// 바구니 객체생성 => 기억장소 할당
					dto=new StarReviewDTO();
					System.out.println(dto);
					// set메서드호출 바구니에 디비에서 가져온 값 저장
					dto.setNickname(rs.getString("nickname"));
					dto.setTgt_id(rs.getString("tgt_id"));
					dto.setScore(rs.getDouble("avg(score)"));
					dto.setMemImg(rs.getString("mem_img"));
				}
			}
			 catch (Exception e) {
				// 예외가 발생하면 처리하는 곳
				e.printStackTrace();
			}finally {
				// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
				if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
				if(con!=null) try { con.close();} catch (Exception e2) {}
				if(rs!=null) try { rs.close();} catch (Exception e2) {}
			}
			return dto;
		}//insertMember() 메서드
		
		public ArrayList<StarReviewDTO> getReviewList(String tgt_id, int startRow,int pageSize){
			System.out.println("StarReviewDTO arry(getStarReview)List()");
			SimpleDateFormat sDate=new SimpleDateFormat("yyyy.MM.dd");
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			ArrayList<StarReviewDTO> reviewList=new ArrayList<>();
			try {
				// 1~2 단계
				con=getConnection();
				// 3단계 sql
				// 기본 num기준 오름차순 => 최근글 위로 올라오게 정렬 (num 내림차순)
//				String sql="select * from board order by num desc";
//				String sql="select * from board order by num desc limit 시작행-1, 몇개";
				//String sql="select * from manner where tgt_id=? order by review_date desc limit ?, ?";
				String sql="select m2.nickname, m1.score, m1.insert_id, m1.review_content, m1.review_date, m1.tgt_id from manner m1 join members m2 on m2.mem_id = m1.insert_id where tgt_id=? order by review_date desc limit ?, ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, tgt_id);
				pstmt.setInt(2, startRow-1);
				pstmt.setInt(3, pageSize);
				//4
				rs=pstmt.executeQuery();
				//5
				while(rs.next()) {
					// 하나의 글의 바구니에 저장
					StarReviewDTO dto=new StarReviewDTO();
					dto.setScore(rs.getInt("score"));
					dto.setInsert_id(rs.getString("insert_id"));
					dto.setReview_content(rs.getString("review_content"));
					dto.setReview_date(rs.getTimestamp("review_date"));
					dto.setTgt_id(rs.getString("tgt_id"));
					dto.setNickname(rs.getString("nickname"));
					// 바구니의 주소값을 배열 한칸에 저장
					reviewList.add(dto);
					System.out.println(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
				if(rs!=null) try { rs.close();} catch (Exception e2) {}
				if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
				if(con!=null) try { con.close();} catch (Exception e2) {}
			}
			return reviewList;
		}//
		
		public int getReviewCount(String id) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int count=0;
			String tgt_id = "";
			try {
				// 1~2 단계
				con=getConnection();
				// 3단계 sql
				String sql="select tgt_id, count(*) from manner where tgt_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				//4
				rs=pstmt.executeQuery();
				System.out.println(pstmt);
				
				//5
				if(rs.next()) {
					count=rs.getInt("count(*)");
					tgt_id=rs.getString("tgt_id");
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
		
		public void insertReview(StarReviewDTO dto) {
			System.out.println("StartReview.insertBoard");
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = getConnection();
				
				// 3단계 SQL구문 만들어서 실행할 준비(insert)
				String sql = "insert into manner (tgt_id, score, insert_id, review_content, review_date) values (?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getTgt_id());
				pstmt.setDouble(2, dto.getScore());
				pstmt.setString(3, dto.getInsert_id());
				pstmt.setString(4, dto.getReview_content());
				pstmt.setTimestamp(5, dto.getReview_date());		
				System.out.println(pstmt);
				pstmt.executeUpdate();

			} catch (Exception e) {
				// 예외가 발생하면 처리하는 곳
				e.printStackTrace();

			} finally {
				// 예외 상관없이 마무리작업 -> 객체생성한 기억장소 해제
				if (pstmt != null)
					try {pstmt.close();} catch (Exception e2) {}{}
				if (con != null)
					try {con.close();} catch (Exception e2) {}{}
					
			}
		}// insertReview
		
		public StarReviewDTO getMemberImg(String id) {
			// 바구니 주소가 저장되는 변수에 null 초기화
			StarReviewDTO dto = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				// 1,2단계 디비연결 메서드 호출 
				con = getConnection();
				
				//3단계 SQL구문 만들어서 실행할 준비 (select where id=?)
				String sql = "select mem_id,nickname,mem_img from members where mem_id=?";
				pstmt = con.prepareStatement(sql);
				//? 채워넣기
					pstmt.setString(1, id); 

				//4단계 SQL구문을 실행(select) => 결과 저장 
				rs = pstmt.executeQuery();

				//5단계 결과를 출력, 데이터 담기 (select)
				if(rs.next()) {
				// id, pass 일치하면 MemberDTO 바구니에 데이터 담아서 가져오기 
				//	바구니 객체생성 dto => 기억장소 할당 
					dto = new StarReviewDTO();
					dto.setMemId(rs.getString("mem_id"));
					dto.setMemImg(rs.getString("mem_img"));
					dto.setNickname(rs.getString("nickname"));
					System.out.println(id);
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
		
		public StarReviewDTO getMemberImg2(String tgt_id) {
			// 바구니 주소가 저장되는 변수에 null 초기화
			StarReviewDTO dto = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				// 1,2단계 디비연결 메서드 호출 
				con = getConnection();
				
				//3단계 SQL구문 만들어서 실행할 준비 (select where id=?)
				String sql = "select mem_id,nickname,mem_img from members where mem_id=?";
				pstmt = con.prepareStatement(sql);
				//? 채워넣기
					pstmt.setString(1, tgt_id); 

				//4단계 SQL구문을 실행(select) => 결과 저장 
				rs = pstmt.executeQuery();

				//5단계 결과를 출력, 데이터 담기 (select)
				if(rs.next()) {
				// id, pass 일치하면 MemberDTO 바구니에 데이터 담아서 가져오기 
				//	바구니 객체생성 dto => 기억장소 할당 
					dto = new StarReviewDTO();
					dto.setMemId(rs.getString("mem_id"));
					dto.setMemImg(rs.getString("mem_img"));
					dto.setNickname(rs.getString("nickname"));
					System.out.println(tgt_id);
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

}
