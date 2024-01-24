package mypage.market;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mypage.board.BoardDTO;
import mypage.wish.WishDTO;

public class MarketDAO {
	// DB연결 메서드
	public Connection getConnection() throws Exception{	
		// 서버에서 미리 1,2단계 => 디비연결 => 이름을 불러 연결정보를 가져오기 
		// META-INF context.xml (디비연결정보) 
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
		Connection con = ds.getConnection();
		return con;
	}
	
	// 본인이 쓴 중고거래(Market테이블) 글목록 불러오는 메서드 (사진 포함)
	// 상품 상세페이지 글쓴이의 전체 글목록 불러오는 메서드로도 사용 
	public ArrayList<MarketDTO> getMyMarketList(String id, int startRow, int pageSize) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MarketDTO> marketList = new ArrayList<>();
		try {	
			con = getConnection();
			
			String sql="select m.market_id, m.trade_type, m.title, m.content, m.view_cnt, m.trade_st, m.book_price, "
					+ "m.insert_id, m.insert_date, i.url "
					+ "from (SELECT image_id, market_id, url "
					+ "FROM market_image where mod(image_id,5)=1) i right join market m "
					+ "on i.market_id = m.market_id where insert_id=? order by market_id desc limit ?, ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow-1);
			pstmt.setInt(3, pageSize);
			 
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				MarketDTO dto = new MarketDTO();
				dto.setMarket_id(rs.getInt("market_id"));
				dto.setTrade_type(rs.getString("trade_type"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setView_cnt(rs.getInt("view_cnt"));
				dto.setTrade_st(rs.getString("trade_st"));
				dto.setBook_price(rs.getString("book_price"));
				dto.setInsert_id(rs.getString("insert_id"));
				dto.setInsert_date(rs.getTimestamp("insert_date"));
				dto.setUrl(rs.getString("url"));
				
				marketList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
				if(rs!=null) try { rs.close();} catch (Exception e2) {}
				if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
				if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return marketList;
	}
	
	// 본인이 쓴 전체글 개수 구하는 메서드 (페이징 처리를 위해)
	// 상품 상세페이지 글쓴이의 전체글 개수 구하는 용도로도 사용 
	public int getMarketCount(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="select count(*) from market where insert_id=?";
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
	
	// 관리자 - 중고거래 게시판 전체 글목록 불러오는 메서드 (사진 포함) 
	public ArrayList<MarketDTO> getAdminMarketList(int startRow, int pageSize) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MarketDTO> marketList = new ArrayList<>();
		try {	
			con = getConnection();
			
			String sql="select m.market_id, m.trade_type, m.title, m.content, m.view_cnt, m.trade_st, m.book_price, "
					+ "m.insert_id, m.insert_date, i.url "
					+ "from (SELECT image_id, market_id, url "
					+ "FROM market_image where mod(image_id,5)=1) i right join market m "
					+ "on i.market_id = m.market_id order by market_id desc limit ?, ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			 
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				MarketDTO dto = new MarketDTO();
				dto.setMarket_id(rs.getInt("market_id"));
				dto.setTrade_type(rs.getString("trade_type"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setView_cnt(rs.getInt("view_cnt"));
				dto.setTrade_st(rs.getString("trade_st"));
				dto.setBook_price(rs.getString("book_price"));
				dto.setInsert_id(rs.getString("insert_id"));
				dto.setInsert_date(rs.getTimestamp("insert_date"));
				dto.setUrl(rs.getString("url"));
				
				marketList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
				if(rs!=null) try { rs.close();} catch (Exception e2) {}
				if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
				if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return marketList;
	}
	
	// 관리자 - 중고거래 게시판 전체글 개수 구하는 메서드 (페이징 처리를 위해)
	public int getAllMarketCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			String sql="select count(*) from market";
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
	
	// 1. 글목록에서 여러개 체크박스로 삭제하는 메서드 
	public void multiDelete(String[] market_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 1~2단계
			con = getConnection();
		
			// 3단계 SQL구문 만들어서 실행할 준비 
			String sql = "delete from market where market_id=?";			
			pstmt = con.prepareStatement(sql);

			for(int i=0; i<market_id.length; i++) {
				pstmt.setString(1, market_id[i]);
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

//	// 2. 글목록에서 여러개 체크박스로 삭제하는 메서드 - foreign key 삭제(채팅)
//	public void multiDelete2(String[] market_id) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			// 1~2단계
//			con = getConnection();
//		
//			// 3단계 SQL구문 만들어서 실행할 준비 
//			String sql = "delete from chat where market_id=?";			
//			pstmt = con.prepareStatement(sql);
//
//			for(int i=0; i<market_id.length; i++) {
//				pstmt.setString(1, market_id[i]);
//				// 4단계 SQL구문을 실행(insert, update, delete)
//				pstmt.executeUpdate();	
//			}
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
//			if(con!=null) try { con.close();} catch (Exception e2) {}
//		}
//	}
	
	// 3. 글목록에서 여러개 체크박스로 삭제하는 메서드 - foreign key 삭제(찜)
	public void multiDelete3(String[] market_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 1~2단계
			con = getConnection();
		
			// 3단계 SQL구문 만들어서 실행할 준비 
			String sql = "delete from attention where market_id=?";			
			pstmt = con.prepareStatement(sql);

			for(int i=0; i<market_id.length; i++) {
				pstmt.setString(1, market_id[i]);
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
	
	// 본인이 쓴 중고거래(Market테이블) 글목록 불러오는 메서드 (현재 미사용)
	public ArrayList<MarketDTO> getMarketList(String id,int startRow,int pageSize){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<MarketDTO> marketList=new ArrayList<>();
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			// 기본 num기준 오름차순 => 최근글 위로 올라오게 정렬 (num 내림차순)
//			String sql="select * from board order by num desc limit 시작행-1, 몇개";
			String sql="select * from market where insert_id=? order by market_id desc limit ?, ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow-1);
			pstmt.setInt(3, pageSize);
			//4
			rs=pstmt.executeQuery();
			//5
			while(rs.next()) {
				// 하나의 글의 바구니에 저장
				MarketDTO dto=new MarketDTO();
				dto.setMarket_id(rs.getInt("market_id"));
				dto.setTrade_type(rs.getString("trade_type"));
//				dto.setContent_img1(rs.getString("content_img1"));
				dto.setTitle(rs.getString("title"));
				dto.setBook_price(rs.getString("book_price"));
				dto.setInsert_date(rs.getTimestamp("insert_date"));
				dto.setView_cnt(rs.getInt("view_cnt"));
				// 바구니의 주소값을 배열 한칸에 저장
				marketList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return marketList;
	}//
	
	// 관리자 - 중고거래 게시판 전체 글목록 불러오는 메서드 (현재 미사용) 
	public ArrayList<MarketDTO> getAllMarketList(int startRow,int pageSize){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<MarketDTO> marketList=new ArrayList<>();
		try {
			// 1~2 단계
			con=getConnection();
			// 3단계 sql
			// 기본 num기준 오름차순 => 최근글 위로 올라오게 정렬 (num 내림차순)
//			String sql="select * from market order by num desc limit 시작행-1, 몇개";
			String sql="select * from market order by market_id desc limit ?, ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			//4
			rs=pstmt.executeQuery();
			//5
			while(rs.next()) {
				// 하나의 글의 바구니에 저장
				MarketDTO dto=new MarketDTO();
				dto.setMarket_id(rs.getInt("market_id"));
				dto.setTrade_type(rs.getString("trade_type"));
//				dto.setContent_img1(rs.getString("content_img1"));
				dto.setTitle(rs.getString("title"));
				dto.setBook_price(rs.getString("book_price"));
				dto.setInsert_id(rs.getString("insert_id"));
				dto.setInsert_date(rs.getTimestamp("insert_date"));
				dto.setView_cnt(rs.getInt("view_cnt"));
				// 바구니의 주소값을 배열 한칸에 저장
				marketList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
		return marketList;
	}//
	
	// 닉네임 받아오는 메서드
	   public String getNickname(int market_id) {
		      String nickname = null;
		      Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      try {   
		         con = getConnection();
		         
		         String sql="select mem_id, nickname from members m join market mr where m.mem_id = mr.insert_id and mr.market_id=?";
		         pstmt=con.prepareStatement(sql);
		         pstmt.setInt(1, market_id);  
		         
		         rs=pstmt.executeQuery();
		         
		         if(rs.next()) {
		            nickname = rs.getString("nickname");
		         }
		      } catch (Exception e) {
		         e.printStackTrace();
		      } finally {
		         if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
		         if(con!=null) try { con.close();} catch (Exception e2) {}
		         if(rs!=null) try { rs.close();} catch (Exception e2) {}
		      }
		      return nickname;
		   }
	
}