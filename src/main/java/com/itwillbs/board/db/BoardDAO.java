package com.itwillbs.board.db;

// 패키지 board 파일이름 BoardDAO
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.cj.exceptions.RSAException;
import com.mysql.cj.jdbc.BlobFromLocator;

public class BoardDAO {
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

	// 게시글 작성
	public void insertBoard(BoardDTO dto) {
		System.out.println("BoardDAO.insertBoard");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			// 3단계 SQL구문 만들어서 실행할 준비(insert)
			String sql = "insert into board (" + "board_type," + "notice_yn," + "title," + "content," + "parent_id,"
					+ "content_img1," + "content_img2," + "content_img3," + "ref," + "insert_id," + "insert_date)"
					+ "values (?,?,?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getBoardType());
			pstmt.setString(2, dto.getNoticeyn());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getParentId());
			pstmt.setString(6, dto.getContentImg1());
			pstmt.setString(7, dto.getContentImg2());
			pstmt.setString(8, dto.getContentImg3());
			pstmt.setInt(9, dto.getRef());
			pstmt.setString(10, dto.getInsertId());

			pstmt.executeUpdate();

		} catch (Exception e) {
			// 예외가 발생하면 처리하는 곳
			e.printStackTrace();

		} finally {
			// 예외 상관없이 마무리작업 -> 객체생성한 기억장소 해제
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			{
			}
			if (con != null)
				try {
					con.close();
				} catch (Exception e2) {
				}
			{
			}

		}
	}// insertBoard

	// 게시글 목록(일반글)조회
	public ArrayList<BoardDTO> getBoardList(int startRow, int pageSize, BoardDTO boardDTO) {
		System.out.println("BoardDAO getBoardList()");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();

		try {
			// 1~2단계
			con = getConnection();

			// 3단계 sql - 기본 : num 기준 오름차순 → 수정 : 최근글 위로 올라오게 정렬
//			String select = "select * from board order by num desc limit 시작행-1, 몇개";

			String sql = "select c.* ,";
				   sql += "(select count(*) from board_cmmt where board_id=c.board_id) as cmmt_count "; // 댓글개수 게시판목록띄우기 
				   sql += "from board as c ";
				   sql += "where notice_yn='N' "; // 공지사항이 아니고
				   sql += "and board_type=? "; // 게시판 유형이 넘겨주는 값(board_type)이고
				   if (boardDTO.getKeyword() != null && !"".equals(boardDTO.getKeyword())) { // keword가 null이 아니고 공백이 아니면
					   sql += "and title like ? "; // 제목에 키워드가 포함된 값을 셀렉트
				   	}
				   sql += "order by ref desc, board_id asc limit ?,?"; // 글그룹으로 내림차순, 게시글아이디로 오름차순 , 시작행-1~몇개 들고옴
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getBoardType());
			if (boardDTO.getKeyword() != null && !"".equals(boardDTO.getKeyword())) {
				pstmt.setString(2, "%" + boardDTO.getKeyword() + "%");
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, pageSize);
			} else {
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, pageSize);
			}

			// 4단계
			rs = pstmt.executeQuery();
			// 5단계
			while (rs.next()) {
				// BoardDTO 객체생성
				BoardDTO dto = new BoardDTO();

				// 하나의 글을 바구니에 저장
				dto.setBoardId(rs.getInt("board_id"));
				dto.setBoardType(rs.getNString("board_type"));
				dto.setNoticeyn(rs.getString("notice_yn"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setParentId(rs.getInt("parent_id"));
				dto.setContent(rs.getString("content_img1"));
				dto.setContent(rs.getString("content_img2"));
				dto.setContent(rs.getString("content_img3"));
				dto.setViewCnt(rs.getInt("view_cnt"));
				dto.setRef(rs.getInt("ref"));
				dto.setInsertId(rs.getString("insert_id"));
				dto.setInsertDate(rs.getTimestamp("insert_date"));
				dto.setCmmtCount(rs.getInt("cmmt_count"));
				
				// 바구니의 주소값을 배열 한칸에 저장
				boardList.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (con != null)
				try {
					con.close();
				} catch (Exception e2) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e2) {
				}

		}
		return boardList;
	}

	// 게시글 목록(공지)조회
	public ArrayList<BoardDTO> getBoardNoticeList(String boardType) {
		System.out.println("BoardDAO.getBoardNoticeList(String noticeYn)");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<BoardDTO> boardnoticeList = new ArrayList<BoardDTO>();

		try {
			// 1~2단계
			con = getConnection();

			// 3단계 sql - 기본 : num 기준 오름차순 → 수정 : 최근글 위로 올라오게 정렬
//				String select = "select * from board order by num desc";
//				String select = "select * from board order by num desc limit 시작행-1, 몇개";
			String sql = "select * from board where notice_yn='Y' and board_type=? order by board_id desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardType);

			// 4단계
			rs = pstmt.executeQuery();
			// 5단계
			while (rs.next()) {
				// BoardDTO 객체생성
				BoardDTO dto = new BoardDTO();
				// 하나의 글을 바구니에 저장
				dto.setBoardId(rs.getInt("board_id"));
				dto.setBoardType(rs.getNString("board_type"));
				dto.setNoticeyn(rs.getString("notice_yn"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setParentId(rs.getInt("parent_id"));
				dto.setContent(rs.getString("content_img1"));
				dto.setContent(rs.getString("content_img2"));
				dto.setContent(rs.getString("content_img3"));
				dto.setViewCnt(rs.getInt("view_cnt"));
				dto.setRef(rs.getInt("ref"));
				dto.setInsertId(rs.getString("insert_id"));
				dto.setInsertDate(rs.getTimestamp("insert_date"));
				// 바구니의 주소값을 배열 한칸에 저장
				boardnoticeList.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (con != null)
				try {
					con.close();
				} catch (Exception e2) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e2) {
				}

		}
		return boardnoticeList;
	}

	// 게시글 조회
	public BoardDTO getBoard(int boardId) {
		System.out.println("BoardDAO.getBoard");

		BoardDTO dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1~2단계 디비연결 메서드
			con = getConnection();
			// 3단계 sql 구문 실행준비
			String sql = "select * from board where board_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardId);

			// 4단계 sql 구문 실행 - 결과저장
			rs = pstmt.executeQuery();

			// 5단계 결과를 출력, 데이터 담기(select)
			// next()다음행 -> 리턴값 데이터 있으면 True/ 데이터 없으면 false
			// 조건이 true 실행문 -> 다음데이터 있으면 true -> 디비 열접근 출력

			while (rs.next()) {

				// 하나의 글의 바구니에 저장
				dto = new BoardDTO();

				// set메서드호출 - 디비에서 가져온 값 저장

				dto.setBoardId(rs.getInt("board_id"));
				dto.setBoardType(rs.getNString("board_type"));
				dto.setNoticeyn(rs.getString("notice_yn"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setParentId(rs.getInt("parent_id"));
				dto.setContentImg1(rs.getString("content_img1"));
				dto.setContentImg2(rs.getString("content_img2"));
				dto.setContentImg3(rs.getString("content_img3"));
				dto.setViewCnt(rs.getInt("view_cnt"));
				dto.setRef(rs.getInt("ref"));
				dto.setInsertId(rs.getString("insert_id"));
				dto.setInsertDate(rs.getTimestamp("insert_date"));
			}

		} catch (Exception e) {
			// 예외처리
			e.printStackTrace();

		} finally {
			// 마무리
			if (con != null)
				try {
					con.close();
				} catch (Exception e2) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e2) {
				}
		}
		return dto;
	} // getBoard

	// 게시글 수정
	public void updateBoard(BoardDTO dto) {
		System.out.println("BoardDAO.updateBoard(BoardDTO dto)");

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 1~2단계 디비연결메서드 호출
			con = getConnection();

			// 3단계 SQL구문 만들어서 실행할 준비
			String update = "update board set title=?,content=?,content_img1=?," + "content_img2=?,content_img3=?"
					+ " where board_id=?";

			pstmt = con.prepareStatement(update);

			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getContentImg1());
			pstmt.setString(4, dto.getContentImg2());
			pstmt.setString(5, dto.getContentImg3());
			pstmt.setInt(6, dto.getBoardId());

			// 4단계 실행 - insert,update,delete
			pstmt.executeUpdate();

		} catch (Exception e) {
			// 예외처리
			e.printStackTrace();

		} finally {
			// 마무리
			if (con != null)
				try {
					con.close();
				} catch (Exception e2) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
		}
	} // updateBoard

	// 게시글 삭제
	public void deleteBoard(int boardId) {
		System.out.println("BoardDAO.deleteBoard(int boardId)");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 1,2 단계 db연결메서드 호출
			con = getConnection();
			// 3단계 sql구문 만들어서 실행 준비
			String delete = "delete from board where board_id=? or parent_id=?";
			// 4단계 sql 구문 실행
			pstmt = con.prepareStatement(delete);
			pstmt.setInt(1, boardId);
			pstmt.setInt(2, boardId);

			pstmt.executeUpdate();

		} catch (Exception e) {
			// 예외처리
			e.printStackTrace();

		} finally {
			// 마무리
			if (con != null)
				try {
					con.close();
				} catch (Exception e2) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
		}

	} // deleteBoard(int num)

	// 게시글 전체개수
	public int getBoardCount(BoardDTO boardDTO) {

		System.out.println("BoardDAO.getBoardCount()");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			// 1-2
			con = getConnection();

			// 3
			String sql = "select count(*) from board where notice_yn='N' ";
			sql += " and board_type=? ";
			if (boardDTO.getKeyword() != null && !"".equals(boardDTO.getKeyword())) {
				sql += "and title like ? ";
			}
			pstmt = con.prepareStatement(sql);

			if (boardDTO.getKeyword() != null && !"".equals(boardDTO.getKeyword())) {
				pstmt.setString(1, boardDTO.getBoardType());
				pstmt.setString(2, "%" + boardDTO.getKeyword() + "%");
			} else {
				pstmt.setString(1, boardDTO.getBoardType());
			}

			// 4
			rs = pstmt.executeQuery();

			// 5
			if (rs.next()) {
				count = rs.getInt("count(*)");
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (con != null)
				try {
					con.close();
				} catch (Exception e2) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e2) {
				}
		}
		return count;
	} // getBoardCount()

	// 게시글 조회수
	public void updateViewCnt(int boardId) {
		System.out.println("BoardDAO.updateViewCnt(int boardId)");

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 1~2단계 디비연결메서드 호출
			con = getConnection();

			// 3단계 SQL구문 만들어서 실행할 준비
			String update = "update board set view_cnt = view_cnt+1 where board_id=?";

			pstmt = con.prepareStatement(update);

			pstmt.setInt(1, boardId);

			// 4단계 실행 - insert,update,delete
			pstmt.executeUpdate();

		} catch (Exception e) {
			// 예외처리
			e.printStackTrace();

		} finally {
			// 마무리
			if (con != null)
				try {
					con.close();
				} catch (Exception e2) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
		}
	} // updateBoard

	// 게시글 파일업데이트
	public void fupdateBoard(BoardDTO dto) {
		System.out.println("BoardDAO.fupdateBoard()");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 1~2 단계
			con = getConnection();
			// 3단계 sql
			String sql = "update board set title=?, content=?, content_img=? where board_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			// file 추가
			pstmt.setString(3, dto.getContentImg1());

			pstmt.setInt(4, dto.getBoardId());
			// 4단계 SQL구문을 실행(insert,update,delete)
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 예외 상관없이 마무리작업 => 객체생성한 기억장소 해제
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e2) {
				}
		}
	}//

	// ref(글 그룹) 조회
	public int getRef() {
		System.out.println("BoardDAO.getRef()");

		int ref = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1~2단계 디비연결 메서드
			con = getConnection();
			// 3단계 sql 구문 실행준비
			String sql = "select ifnull(max(ref),'0')+1 as ref from board ";
			pstmt = con.prepareStatement(sql);

			// 4단계 sql 구문 실행 - 결과저장
			rs = pstmt.executeQuery();

			// 5단계 결과를 출력, 데이터 담기(select)

			while (rs.next()) {
				ref = rs.getInt("ref");
			}

		} catch (Exception e) {
			// 예외처리
			e.printStackTrace();

		} finally {
			// 마무리
			if (con != null)
				try {
					con.close();
				} catch (Exception e2) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e2) {
				}
		}
		return ref;
	} // getRef

	// ref(글 그룹) 조회2
	public int getRefRe(int boardId) {
		System.out.println("BoardDAO.int getRefre()");

		int ref = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1~2단계 디비연결 메서드
			con = getConnection();
			// 3단계 sql 구문 실행준비
			String sql = "select ref from board where board_id=? ";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, boardId);

			// 4단계 sql 구문 실행 - 결과저장
			rs = pstmt.executeQuery();

			// 5단계 결과를 출력, 데이터 담기(select)

			while (rs.next()) {
				ref = rs.getInt("ref");
			}

		} catch (Exception e) {
			// 예외처리
			e.printStackTrace();

		} finally {
			// 마무리
			if (con != null)
				try {
					con.close();
				} catch (Exception e2) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e2) {
				}
		}
		return ref;
	} // getRefRe

	// 메인에 보여줄 글목록
	public ArrayList<BoardDTO> getMainBoardList(String boardType) {
		System.out.println("BoardDAO getMainBoardList()");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();

		try {
			// 1~2단계
			con = getConnection();

			// 3단계 sql - 기본 : num 기준 오름차순 → 수정 : 최근글 위로 올라오게 정렬
//						String select = "select * from board order by num desc limit 시작행-1, 몇개";

			String sql = "select * ";
			sql += "from board ";
			sql += "where board_type=? "; // 게시판 유형이 넘겨주는 값(board_type)이고
			sql += "order by board_id desc limit 5 "; // 게시글아이디로 내림차순 , 5번까지 가지고옴.

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardType);

			// 4단계
			rs = pstmt.executeQuery();
			// 5단계
			while (rs.next()) {
				// BoardDTO 객체생성
				BoardDTO dto = new BoardDTO();

				// 하나의 글을 바구니에 저장
				dto.setBoardId(rs.getInt("board_id"));
				dto.setBoardType(rs.getNString("board_type"));
				dto.setNoticeyn(rs.getString("notice_yn"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setParentId(rs.getInt("parent_id"));
				dto.setContent(rs.getString("content_img1"));
				dto.setContent(rs.getString("content_img2"));
				dto.setContent(rs.getString("content_img3"));
				dto.setViewCnt(rs.getInt("view_cnt"));
				dto.setRef(rs.getInt("ref"));
				dto.setInsertId(rs.getString("insert_id"));
				dto.setInsertDate(rs.getTimestamp("insert_date"));
				// 바구니의 주소값을 배열 한칸에 저장
				boardList.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (con != null)
				try {
					con.close();
				} catch (Exception e2) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e2) {
				}

		}
		return boardList;
	}

	public String getNickname(int board_id) {
		String nickname = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();

			String sql = "select mem_id, nickname from members m join board b where m.mem_id = b.insert_id and b.board_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				nickname = rs.getString("nickname");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e2) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e2) {
				}
		}
		return nickname;
	}

} // BoardDAO
