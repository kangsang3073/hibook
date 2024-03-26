package com.itwillbs.board.action;
// BoardListPro
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.ComCdDTO;
import com.itwillbs.util.ComCdUtil;

public class BoardList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardList execute()");
		
		String boardType = request.getParameter("boardType");
		String keyword = request.getParameter("keyword");
		
		// 공통코드로 게시판 구분 
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
		// 디비 데이터 가져오기
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		
		dto.setBoardType(boardType);
		dto.setKeyword(keyword);
		
		int count = 0;  // 전체 글 개수 
		int pageSize = 10; // 화면에 보여줄 글 개수
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
	      
	      // 글 리스트 (시작글번호 ~ 10개까지)
	      ArrayList<BoardDTO> BoardList = dao.getBoardList(startRow, pageSize,dto); 
	      ArrayList<BoardDTO> NoticeList = dao.getBoardNoticeList(boardType); //공지글리스트
	      
	      // 전체 글 개수
	      count = dao.getBoardCount(dto);
	
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
		request.setAttribute("noticeList", NoticeList);
		request.setAttribute("boardType", boardType);
		request.setAttribute("keyword", keyword);
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		
		request.setAttribute("boardTypeCd", boardTypeCd.getCd());
		request.setAttribute("boardTypeCdNm", boardTypeCd.getCdNm());
		// 이동
		ActionForward forward = new ActionForward();
		forward.setPath("board/boardList.jsp");
		forward.setRedirect(false); // 주소값이 안바뀌면서 이동하겠다.
 		
		return forward;
	}

}
