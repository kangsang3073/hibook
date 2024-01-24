package mypage.comment;

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

public class CommentDAO {
	// DB연결 메서드
	public Connection getConnection() throws Exception{			
		// 서버에서 미리 1,2단계 => 디비연결 => 이름을 불러 연결정보를 가져오기 
		// META-INF context.xml (디비연결정보) 
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
		Connection con = ds.getConnection();
		return con;
	}
	
	// 본인이 쓴 댓글(board_cmmt 테이블) 목록 불러오는 메서드 
	public ArrayList<CommentDTO> getCommentList(String id,int startRow,int pageSize){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<CommentDTO> commentList=new ArrayList<>();
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			// 기본 num기준 오름차순 => 최근댓글 위로 올라오게 정렬 (num 내림차순)
//			String sql="select * from board order by num desc limit 시작행-1, 몇개";
			String sql="select c.cmmt_id, c.secret_yn, c.content, c.insert_id, c.insert_date, "
					+ "b.title, b.board_id, b.board_type from board_cmmt c join board b on c.board_id = b.board_id "
					+ "where c.insert_id=? order by c.cmmt_id desc limit ?, ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow-1);
			pstmt.setInt(3, pageSize);
			//4
			rs=pstmt.executeQuery();
			//5
			while(rs.next()) {
				// 하나의 글의 바구니에 저장
				CommentDTO dto=new CommentDTO();
				dto.setCmmt_id(rs.getInt("cmmt_id"));
				dto.setSecret_yn(rs.getString("secret_yn"));
				dto.setContent(rs.getString("content"));
				dto.setInsert_date(rs.getTimestamp("insert_date"));
				dto.setTitle(rs.getString("title"));
				dto.setBoard_id(rs.getInt("board_id"));
				dto.setBoard_type(rs.getString("board_type"));
				// 바구니의 주소값을 배열 한칸에 저장
				commentList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return commentList;
	}//
	
	// 본인이 쓴 전체 댓글 개수 구하는 메서드 (페이징 처리를 위해)
	public int getCommentCount(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="select count(*) from board_cmmt where insert_id=?";
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
	
	// 전체 댓글(board_cmmt 테이블) 목록 불러오는 메서드 
	public ArrayList<CommentDTO> getAllCommentList(int startRow,int pageSize){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<CommentDTO> commentList=new ArrayList<>();
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			// 기본 num기준 오름차순 => 최근댓글 위로 올라오게 정렬 (num 내림차순)
//			String sql="select * from board_cmmt order by num desc limit 시작행-1, 몇개";
			String sql="select c.cmmt_id, c.secret_yn, c.content, c.insert_id, c.insert_date, "
					+ "b.title, b.board_id, b.board_type from board_cmmt c join board b "
					+ "on c.board_id = b.board_id order by cmmt_id desc limit ?, ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			//4
			rs=pstmt.executeQuery();
			//5
			while(rs.next()) {
				// 하나의 글의 바구니에 저장
				CommentDTO dto=new CommentDTO();
				dto.setCmmt_id(rs.getInt("cmmt_id"));
				dto.setSecret_yn(rs.getString("secret_yn"));
				dto.setContent(rs.getString("content"));
				dto.setInsert_id(rs.getString("insert_id"));
				dto.setInsert_date(rs.getTimestamp("insert_date"));
				dto.setTitle(rs.getString("title"));
				dto.setBoard_id(rs.getInt("board_id"));
				dto.setBoard_type(rs.getString("board_type"));
				// 바구니의 주소값을 배열 한칸에 저장
				commentList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return commentList;
	}//
	
	// 전체 댓글 개수 구하는 메서드 (페이징 처리를 위해)
	public int getAllCommentCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="select count(*) from board_cmmt";
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
	
	// 댓글목록에서 여러개 체크박스로 삭제하는 메서드 
	public void multiDelete(String[] cmmt_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 1~2단계
			con = getConnection();
		
			// 3단계 SQL구문 만들어서 실행할 준비 
			String sql = "delete from board_cmmt where cmmt_id=?";			
			pstmt = con.prepareStatement(sql);

			for(int i=0; i<cmmt_id.length; i++) {
				pstmt.setString(1, cmmt_id[i]);
				// 4단계 SQL구문을 실행(insert, update, delete)
				pstmt.executeUpdate();	
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
	}
	
	
}