package com.itwillbs.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.ComCdDTO;
import com.itwillbs.util.ComCdUtil;

public class BoardUpdateForm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
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
		
		String boardId = request.getParameter("boardId");
		// MemberDAO 객체생성 -> 기억장소 할당
		BoardDAO dao = new BoardDAO();
		
		// MemberDTO dto = dao.getMember(id)메서드 호출
		BoardDTO dto  = dao.getBoard(Integer.parseInt(boardId)); //- Object형
		
		// dto 정보를 들고 가기(request 정보를 담아가기)
		request.setAttribute("dto", dto); // "id" ="dto"
		request.setAttribute("boardTypeCd", boardTypeCd.getCd());
		request.setAttribute("boardTypeCdNm", boardTypeCd.getCdNm());

		//member/updateForm.jsp 주소 변경없이(false) 이동
		ActionForward forward = new ActionForward();
		forward.setPath("board/boardUpdateForm.jsp"); // 이동경로
		forward.setRedirect(false); // 주소 변경없이 이동해야해서 false
		return forward; // 이동정보 리턴 : forward 가 이동경로 를 나타내서 return forward
		
		
	}

}
