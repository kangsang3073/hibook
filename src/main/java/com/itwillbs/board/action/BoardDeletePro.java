package com.itwillbs.board.action;


import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardDeletePro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// request 태그이름에 해당하는 값을 가져오기 → 변수에 저장
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String boardType = request.getParameter("boardType");

		// BoardDAO 객체생성 -> 기억장소 할당
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.getBoard(boardId); //파일정보 가져오기위해 조회.
		
		ActionForward forward = null;
		if (dto != null) {
			
	        File[] files = { new File (request.getRealPath("/upload") +"/"+ dto.getContentImg1()),
			        		 new File (request.getRealPath("/upload") +"/"+ dto.getContentImg2()),
			        		 new File (request.getRealPath("/upload") +"/"+ dto.getContentImg3())};
	        
	        for(int i=0; i<files.length; i++) {
	        	if(files[i].exists() && files[i].isFile()) files[i].delete(); //파일삭제
	        }
			
			// board_id 일치. 리턴값없음 deleteMember(String id) 메서드 정의
			// dao.deleteMember(id)메서드 호출
			dao.deleteBoard(boardId);

			// main.jsp 이동
			// response.sendRedirect("main.jsp");
			forward = new ActionForward();
			forward.setPath("BoardList.bo?boardType="+boardType);
			System.out.println(forward.getPath());
			forward.setRedirect(true); // 주소가 MemberMain.me로 바뀌어야해서 true값 입력.

		}
		return forward;
	}

} // 클래스
