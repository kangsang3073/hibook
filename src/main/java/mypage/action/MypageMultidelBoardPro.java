package mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.board.BoardDAO;

public class MypageMultidelBoardPro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 글목록에서 여러개 체크박스로 삭제
		// 체크박스 name값 chk를 받아와 배열 board_id 에 저장 
		String[] board_id = request.getParameterValues("chk");

		// 먼저 foreign key가 있는 댓글 테이블에서 삭제 
		// 이유: 부모(커뮤니티 게시판)는 자식(댓글)이 있으면 삭제할 수 없음 
		// 이후 커뮤니티 게시판 체크박스 삭제 메서드 호출 
		BoardDAO dao = new BoardDAO(); 
		dao.multiDelete2(board_id); 
		dao.multiDelete(board_id); 

		ActionForward forward=new ActionForward();
		forward.setPath("MypageBoardList.mypage");
		forward.setRedirect(true);
		return forward;
	}

}
