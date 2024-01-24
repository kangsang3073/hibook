package adminpage.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.board.BoardDAO;
import mypage.board.BoardDTO;
import mypage.comment.CommentDAO;
import mypage.comment.CommentDTO;

public class AdminCommentList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 객체생성 
		CommentDAO dao = new CommentDAO();
		// 페이징 처리 
		// 한페이지에 보여줄 글개수 설정  
		int pageSize = 20;
		// 현 페이지 번호 가져오기 => 페이지번호가 없으면 1페이지 설정 
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize + 1;
		int endRow = startRow + pageSize - 1;

		// 메서드 호출 
		ArrayList<CommentDTO> commentList = dao.getAllCommentList(startRow, pageSize); 
		
		//한 화면에 보여줄 페이지 개수 설정
		int pageBlock = 10;
		int startPage = (currentPage-1)/pageBlock*pageBlock + 1;
		int endPage = startPage + pageBlock - 1;

		//전체글 개수 select count(*) from board_cmmt
		//int 리턴할형 getCommentCount() 메서드 정의
		int count = dao.getAllCommentCount(); 
		int pageCount = count/pageSize + (count%pageSize == 0?0:1);
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		// request 가져온 데이터 담기
		request.setAttribute("commentList", commentList);
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		
		//이동 
		ActionForward forward=new ActionForward();
		forward.setPath("adminpage/adminComment.jsp");
		forward.setRedirect(false);
		return forward;				
	}

}
