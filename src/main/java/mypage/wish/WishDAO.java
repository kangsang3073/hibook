package mypage.wish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mypage.market.MarketDTO;

public class WishDAO {
	// DB연결 메서드
		public Connection getConnection() throws Exception{	
			// 서버에서 미리 1,2단계 => 디비연결 => 이름을 불러 연결정보를 가져오기 
			// META-INF context.xml (디비연결정보) 
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
			Connection con = ds.getConnection();
			return con;
		}
		
		// 찜버튼 누르면 db에 insert하는 메서드
		public int insertWish(WishDTO dto) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				// 1~2단계
				con = getConnection();
			
				// 3단계 SQL구문 만들어서 실행할 준비 
				String sql = "insert into attention(market_id, insert_id) values(?,?)";
				pstmt = con.prepareStatement(sql);
				// ? 채워넣기
					pstmt.setInt(1, dto.getMarket_id()); 
					pstmt.setString(2, dto.getInsert_id()); 
					
				// 4단계 SQL구문을 실행(insert, update, delete)
				pstmt.executeUpdate();
			} catch (Exception e) {
				// 예외가 발생하면 처리하는 곳 
				e.printStackTrace();
			} finally {
				if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
				if(con!=null) try { con.close();} catch (Exception e2) {}
			}
			return -1;
		}
		
		// 찜취소 버튼 누르면 db에서 delete하는 메서드
		public int deleteWish(WishDTO dto) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				// 1~2단계
				con = getConnection();
			
				// 3단계 SQL구문 만들어서 실행할 준비 
				String sql = "delete from attention where market_id=? and insert_id=?";
				pstmt = con.prepareStatement(sql);
				// ? 채워넣기
					pstmt.setInt(1, dto.getMarket_id()); 
					pstmt.setString(2, dto.getInsert_id()); 
					
				// 4단계 SQL구문을 실행(insert, update, delete)
				pstmt.executeUpdate();
			} catch (Exception e) {
				// 예외가 발생하면 처리하는 곳 
				e.printStackTrace();
			} finally {
				if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
				if(con!=null) try { con.close();} catch (Exception e2) {}
			}
			return -1;
		}
		
		// 유저가 해당 게시글에서 찜을 눌렀는지 확인하는 메서드
		// null을 반환하지 않으면 찜을 한 것임
		public ArrayList<WishDTO> wishCheck(int market_id, String id){
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			ArrayList<WishDTO> wishCheck=new ArrayList<>();
			try {
				// 1~2 단계
				con=getConnection();
				// 3단계 sql
				String sql="select * from attention where market_id=? and insert_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, market_id);
				pstmt.setString(2, id);
				//4
				rs=pstmt.executeQuery();
				//5
				while(rs.next()) {
					// 하나의 글의 바구니에 저장
					WishDTO dto=new WishDTO(); 
					dto.setMarket_id(rs.getInt("market_id"));
					dto.setInsert_id(rs.getString("insert_id"));
					// 바구니의 주소값을 배열 한칸에 저장
					wishCheck.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
				if(rs!=null) try { rs.close();} catch (Exception e2) {}
				if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
				if(con!=null) try { con.close();} catch (Exception e2) {}
			}
			return wishCheck;
		}//
		
		// 해당 게시글의 찜 개수 구하는 메서드 
		public int getWishCount(int market_id) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int count=0;
			try {
				// 1~2 단계
				con=getConnection();
				// 3단계 sql
				String sql="select count(*) from attention where market_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, market_id);
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
		
		// 마이페이지- 찜목록 메서드 (사진 포함)
		public ArrayList<MarketDTO> getMyWishList(String id, int startRow, int pageSize) {
			ArrayList<MarketDTO> getwishlist=new ArrayList<>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {	
				con = getConnection();
				
				String sql="select m.market_id, m.trade_type, m.title, m.content, m.view_cnt, m.trade_st, m.book_price, "
						+ "m.insert_id, m.insert_date, i.url "
						+ "from (SELECT image_id, market_id, url "
						+ "FROM market_image where mod(image_id,5)=1) i right join market m "
						+ "on i.market_id = m.market_id where m.market_id in (select market_id from attention where insert_id=?) order by market_id desc limit ?, ?";
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
					
					getwishlist.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
					if(rs!=null) try { rs.close();} catch (Exception e2) {}
					if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
					if(con!=null) try { con.close();} catch (Exception e2) {}
			}
			return getwishlist;
		}
		
		// 본인이 찜한 전체글 개수 구하는 메서드 (페이징 처리를 위해)
		public int getWishCount(String id) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int count=0;
			try {
				// 1~2 단계
				con=getConnection();
				// 3단계 sql
				String sql="select count(*) from attention where insert_id=?";
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
		
		// 찜목록에서 여러개 체크박스로 삭제하는 메서드 
		public void multiDelete(String[] market_id, String id) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				// 1~2단계
				con = getConnection();
			
				// 3단계 SQL구문 만들어서 실행할 준비 
				String sql = "delete from attention where market_id=? and insert_id=?";			
				pstmt = con.prepareStatement(sql);

				for(int i=0; i<market_id.length; i++) {
					pstmt.setString(1, market_id[i]);
					pstmt.setString(2, id);
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
		
		// 마이페이지- 찜목록 메서드 (현재 미사용)
		public ArrayList<MarketDTO> getWishList(String id, int startRow, int pageSize){
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			ArrayList<MarketDTO> getwishlist=new ArrayList<>();
			try {
				// 1~2 단계
				con=getConnection();
				// 3단계 sql
				// 최근 판매글이 위로 올라오게, 페이징 처리 
				String sql="select * from market where market_id in (select market_id from attention where insert_id=?) order by market_id desc limit ?, ?";
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
//					dto.setContent_img1(rs.getString("content_img1"));
					dto.setTitle(rs.getString("title"));
					dto.setInsert_id(rs.getString("insert_id"));
					dto.setBook_price(rs.getString("book_price"));
					dto.setInsert_date(rs.getTimestamp("insert_date"));
					// 바구니의 주소값을 배열 한칸에 저장
					getwishlist.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
				if(rs!=null) try { rs.close();} catch (Exception e2) {}
				if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
				if(con!=null) try { con.close();} catch (Exception e2) {}
			}
			return getwishlist;
		}//
		
}
