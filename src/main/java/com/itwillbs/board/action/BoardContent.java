package com.itwillbs.board.action;
// 게시판 상세화면 + 댓글처리
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.boardComment.db.BoardCmmtDAO;
import com.itwillbs.boardComment.db.BoardCmmtDTO;
import com.itwillbs.util.ComCdDTO;
import com.itwillbs.util.ComCdUtil;


public class BoardContent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardContent execute()");
		String boardType = request.getParameter("boardType");
		
		ComCdDTO boardTypeCd = ComCdUtil.getComCd(boardType);
		
		if(boardTypeCd == null) {
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out =response.getWriter(); 
			
			out.println("<script type=\'text/javascript\'>"); 
			out.println("alert(\'유효하지 않은 게시판입니다.\');"); 
			out.println("history.back();"); 
			out.println("</script>"); 
			out.close(); 
			return null;
		}
		// 세션에서 "id"값을 가져오기 -> 변수저장( 다운캐스팅 이루어짐.)
		HttpSession session = request.getSession(); // - 세션 객체생성
		String boardId = request.getParameter("boardId");
		
		// BoardDAO 객체생성 -> 기억장소 할당
		BoardDAO dao = new BoardDAO();
		
		// BoardCmmtDAO 객체생성 -> 기억장소 할당
		BoardCmmtDAO cmmtDao = new BoardCmmtDAO();

		// BoardDTO dto = dao.getMember(id)메서드 호출
		dao.updateViewCnt(Integer.parseInt(boardId));
		BoardDTO dto = dao.getBoard(Integer.parseInt(boardId)); 
		
		// cmmtDao = dao.getBoard(
		
		// dto 정보를 들고 가기(request 정보를 담아가기)
		request.setAttribute("dto", dto); // "id" ="dto"
		
		
		// 게시판 페이징
		int count = 0;  // 전체 글 개수 
		int pageSize = 5; // 화면에 보여줄 글 개수
		int pageBlock = 5; // 화면에 보여줄 페이지 수
		int pageCount = 0; // 전체 페이지 수
		int currentPage = 1; // 현재 페이지
		int startRow = 0; // 시작 글번호
		int startPage = 1; // 시작 페이지 번호
		int endPage = 0; // 끝 페이지 번호
		
		// 현재 페이지
		if(request.getParameter("pageNum") != null ){
			currentPage = Integer.parseInt(request.getParameter("pageNum"));
		}
	      
		// 시작 글번호
		startRow = (currentPage-1)*pageSize; // 1 페이지면 (1-1)*10 = 0번 (0~9)
											 // 2 페이지면 (2-1)*10 = 10번 (10~19)
	      
		// BoardCmmtDTO 객체생성 
		BoardCmmtDTO cmmtDto = new BoardCmmtDTO();
			
		// BoardCmmtDTO 세팅 
		cmmtDto.setBoardId(Integer.parseInt(boardId));
			
		// 글 리스트 (시작글번호 ~ 10개까지)
		//  BoardCmmtDTO 배열로 댓글,시작글,마지막글 정의.
		ArrayList<BoardCmmtDTO> BoardList = cmmtDao.getBoardCmmtList(cmmtDto, startRow, pageSize); 
	      
		// 전체 글 개수
		count = cmmtDao.getBoardCmmtCount(cmmtDto);
	
	      
		if(count>0){ // 글이 있으면 
	         
			// 전체 페이지 수
			// 전체글 / 한페이지에 보여질 글수 + (전체글수%한페이지에 보여질 글수 == 0? 0:1)
			pageCount = count / pageSize + (count % pageSize == 0? 0:1);
			// 글  20개면?
			//  2     =  20   /    10    + (  20  %   10 == 0? 0:1)
			//  2     =  20   /    10    + 0
			// 글  67개면?
			//   7     =  67   /    10    + (  67  %   10 == 0? 0:1)
			//   7     =  67   /    10    + 1
	         
	         
	         
			// 시작 페이지 번호
			// 1 ~ 5 -> 1
			// 6 ~ 10 -> 6
			// 11 ~ 15 -> 11
			// (현재페이지 / 한 화면에 보여질 페이지수) - ( 현재페이지 % 한화면에 보여질 페이지수 == 0? 1:0) * 한 화면에 보여질 페이지수 + 1
			startPage = ((currentPage/pageBlock)-(currentPage%pageBlock== 0? 1:0)) * pageBlock + 1;
			// 현재 3페이지면
			// (( 3 / 5 ) - ( 3 % 5 == 0? 1:0)) * 5 + 1
			// (0 - 0) * 5 + 1   --> [1]
			// 현재 7페이지면
			// (( 7 / 5 ) - ( 7 % 5 == 0? 1:0)) * 5 + 1
			// (1 - 0) * 5 + 1   --> [6]
			// 현재 10페이지면
			// (( 10 / 5 ) - ( 10 % 5 == 0? 1:0)) * 5 + 1
			// (2 - 1) * 5 + 1   --> [6]
	         
	         
	         
			// 끝 페이지 번호 
			// 1 ~ 5 -> 5
			// 6 ~ 10 -> 10
			// 11 ~ 15 -> 15
			endPage = startPage + pageBlock - 1;
	         
	
			// 전체페이지 수 보다 끝페이지가 더 크면
			// 전체페이지를 끝페이지로 
			if (endPage > pageCount) {
				endPage = pageCount;
			}
	
		}
		
		// request 가져온 데이터 담기
		request.setAttribute("boardList", BoardList);
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		
		request.setAttribute("boardTypeCd", boardTypeCd.getCd());
		request.setAttribute("boardTypeCdNm", boardTypeCd.getCdNm());

		// member/info.jsp 주소 변경없이(false) 이동
		ActionForward forward = new ActionForward();
		forward.setPath("board/boardContent.jsp"); // 이동경로
		forward.setRedirect(false); // 주소 변경없이 이동해야해서 false
		return forward; // 이동정보 리턴 : forward 가 이동경로 를 나타내서 return forward
	}

}
