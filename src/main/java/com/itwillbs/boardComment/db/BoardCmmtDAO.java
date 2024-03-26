package com.itwillbs.boardComment.db;

// 패키지 board 파일이름 BoardDAO
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.board.db.BoardDTO;




public class BoardCmmtDAO {
	private Connection getConnection() throws Exception {
		// 서버에서 1,2단계 ->디비연결 -> 이름을 불러서연결정보를 가져오기
		// Database Connection Pool(DBCP) -> 디비 연결정보 서버 저장
		// 1. META-INF context.xml(디비연결정보)
		// 2. MemberDAO 디비연결정보 불러서 사용
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MysqlDB");
		Connection connection = ds.getConnection();
		return connection;

	}// getConnection()

	
		// 댓글입력
		public int insertBoardCmmt(BoardCmmtDTO cmmtDto) {
			System.out.println("insertBoardCmmt()");

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			try {
				
				con = getConnection();
				
				// 4단계 SQL구문을 실행(insert,update,delete)
				// 3단계 SQL구문 만들어서 실행할 준비(insert)
				String sql = "insert into board_cmmt("
							+ "board_id,"
							+ "content,"
							+ "parent_id,"
							+ "secret_yn,"
							+ "insert_id,"
							+ "insert_date"
							+ ") values (?,?,?,?,?,now())";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, cmmtDto.getBoardId());
				pstmt.setString(2, cmmtDto.getContent());
				pstmt.setInt(3, cmmtDto.getParentId());
				pstmt.setString(4, cmmtDto.getSecretYn());
				pstmt.setString(5,cmmtDto.getInsertId());
			
				result = pstmt.executeUpdate();

			} catch (Exception e) {
				// 예외가 발생하면 처리하는 곳
				e.printStackTrace();

			} finally {
				// 예외 상관없이 마무리작업 -> 객체생성한 기억장소 해제
				if (pstmt != null) {
					try {pstmt.close();} catch (Exception e2) {}{}
					
				if (con != null) {
					try {con.close();} catch (Exception e2) {}{}
					
					}// if
				}// if
			}// finally
			return result;
		}// insertBoard
		
		// 게시글 조회 
		public BoardCmmtDTO getBoardCmmt(int cmmtId) {
			System.out.println("BoardDAO.getBoard");
			
			BoardCmmtDTO cmmtDto1 = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				// 1~2단계 디비연결 메서드
				con = getConnection();
				// 3단계 sql 구문 실행준비
				String sql = "select * from board_cmmt where cmmt_id = ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cmmtId);
				
				// 4단계 sql 구문 실행 - 결과저장
				rs = pstmt.executeQuery();
				
				// 5단계 결과를 출력, 데이터 담기(select)
				// next()다음행 -> 리턴값 데이터 있으면 True/ 데이터 없으면 false
				// 조건이 true 실행문 -> 다음데이터 있으면 true -> 디비 열접근 출력
				
				while (rs.next()) {
					
				// 하나의 글의 바구니에 저장
					cmmtDto1 = new BoardCmmtDTO();
					
				// set메서드호출 - 디비에서 가져온 값 저장
					
					cmmtDto1.setCmmtId(rs.getInt("cmmt_id"));
					cmmtDto1.setBoardId(rs.getInt("board_id"));
					cmmtDto1.setContent(rs.getString("content"));
					cmmtDto1.setParentId(rs.getInt("parent_id"));
					cmmtDto1.setSecretYn(rs.getString("secret_yn"));
					cmmtDto1.setInsertId(rs.getString("insert_id"));
					cmmtDto1.setInsertDate(rs.getTimestamp("insert_date"));
				}	
				
			} catch (Exception e) {
				// 예외처리
				e.printStackTrace();
				
			} finally {
				// 마무리
				if(con != null) try {con.close();} catch (Exception e2) {}
				if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
				if(rs != null) try {rs.close();} catch (Exception e2) {}
			}
			return cmmtDto1;
		} // getBoard
		
		// 댓글목록
		public ArrayList<BoardCmmtDTO> getBoardCmmtList(BoardCmmtDTO cmmtDto, int startRow,int pageSize) {
			System.out.println("BoardCmmtDAO getBoardCmmtList()");
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			ArrayList<BoardCmmtDTO> boardCmmtList = new ArrayList<BoardCmmtDTO>();
			
			try {
				// 1~2단계
				con = getConnection();
				
				// 3단계 sql - 기본 : num 기준 오름차순 → 수정 : 최근글 위로 올라오게 정렬
//				String select = "select * from board order by num desc limit 시작행-1, 몇개";
				String sql = "select T.*, "
						+ "(select nickname from members where mem_id = T.insert_id) as nickname " 
						+ "from board_cmmt as T "
						+ "where T.board_id = ? "
						+ "order by T.cmmt_id limit ?,? ";
				pstmt = con.prepareStatement(sql);
		        pstmt.setInt(1, cmmtDto.getBoardId());
		        pstmt.setInt(2, startRow);
		        pstmt.setInt(3, pageSize);
				
				// 4단계
				rs = pstmt.executeQuery();
				// 5단계 
				while (rs.next()) {
					//BoardDTO 객체생성
					BoardCmmtDTO cmmtDto1 = new BoardCmmtDTO();
					
					// 하나의 글을 바구니에 저장
					cmmtDto1.setCmmtId(rs.getInt("cmmt_id"));
					cmmtDto1.setBoardId(rs.getInt("board_id"));
					cmmtDto1.setContent(rs.getString("content"));
					cmmtDto1.setParentId(rs.getInt("parent_id"));
					cmmtDto1.setSecretYn(rs.getString("secret_yn"));
					cmmtDto1.setInsertId(rs.getString("insert_id"));
					cmmtDto1.setInsertDate(rs.getTimestamp("insert_date"));
					cmmtDto1.setNickname(rs.getString("nickname"));
					
				
					// 바구니의 주소값을 배열 한칸에 저장
					boardCmmtList.add(cmmtDto1);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				if(con != null) try {con.close();} catch (Exception e2) {}
				if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
				if(rs != null) try {rs.close();} catch (Exception e2) {}
				
			}
			return boardCmmtList;
		}// getBoardCmmtList()
		
		
		// 댓글수정
		public int updateBoardCmmt(BoardCmmtDTO dto) {
			System.out.println("updateBoardCmmt(BoardCmmtDTO dto)");
			
			Connection con = null;
			PreparedStatement pstmt = null;
			int result = 0;
				try {
					// 1~2단계 디비연결메서드 호출
					con = getConnection();
					
					// 3단계 SQL구문 만들어서 실행할 준비
					String update = "update board_cmmt set content=?,secret_yn=? where cmmt_id=?";
					
					pstmt = con.prepareStatement(update);
					
					pstmt.setString(1, dto.getContent());
					pstmt.setString(2, dto.getSecretYn());
					pstmt.setInt(3, dto.getCmmtId());
					
					// 4단계 실행 - insert,update,delete
					result = pstmt.executeUpdate();
					
				} catch (Exception e) {
					// 예외처리
					e.printStackTrace();
					
				} finally {
					// 마무리 
					if(con != null) try {con.close();} catch (Exception e2) {}
					if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
				}
				return result;
		} // updateBoardCmmt
		
		// 댓글삭제
		public int deleteBoardCmmt(int cmmtId) {
			System.out.println("deleteBoardCmmt(int cmmtId)");
			
			Connection con = null;
			PreparedStatement pstmt = null;
			int result = 0; // executeUpdate() ajax의 결과 담기 위해 선언.(필수X)
			
			try {
				// 1,2 단계 db연결메서드 호출
				con = getConnection();
				// 3단계 sql구문 만들어서 실행 준비
				String sql = "delete from board_cmmt where cmmt_id=? ";
				// 4단계 sql 구문 실행
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cmmtId);
				
				result = pstmt.executeUpdate();
				
			} catch (Exception e) {
				// 예외처리 
				e.printStackTrace();
				
			} finally {
				// 마무리 
				if(con != null) try {con.close();} catch (Exception e2) {}
				if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
			}
			return result;
		} // deleteBoardCmmt
		
		// 댓글전체개수(페이징처리)
		public int getBoardCmmtCount(BoardCmmtDTO cmmtDto) {
			System.out.println("getBoardCmmtCount()");
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int count = 0;
			
			try {
				//1-2
				con = getConnection();
				
				//3
				String sql = "select count(*) from board_cmmt where board_id = ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cmmtDto.getBoardId());
				//4
				rs = pstmt.executeQuery();
				
				//5
				if(rs.next()) {
					count = rs.getInt("count(*)");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				if(con != null) try {con.close();} catch (Exception e2) {}
				if(pstmt != null) try {pstmt.close();} catch (Exception e2) {}
				if(rs != null) try {rs.close();} catch (Exception e2) {}
			}
			return count;
		} // getBoardCmmtCount()
	

} // BoardCmmtDAO
