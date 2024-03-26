package com.itwillbs.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.util.ComCdDTO;
import com.itwillbs.util.ComCdUtil;

public class BoardWriteForm implements Action {

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
		
		request.setAttribute("boardTypeCd", boardTypeCd.getCd());
		request.setAttribute("boardTypeCdNm", boardTypeCd.getCdNm());

		
		//member/updateForm.jsp 주소 변경없이(false) 이동
		ActionForward forward = new ActionForward();
		forward.setPath("board/boardWriteForm.jsp"); // 이동경로
		forward.setRedirect(false); // 주소 변경없이 이동해야해서 false
		return forward; // 이동정보 리턴 : forward 가 이동경로 를 나타내서 return forward
		
		
	}

}
