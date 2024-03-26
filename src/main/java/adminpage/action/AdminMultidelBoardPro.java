package adminpage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.board.BoardDAO;

public class AdminMultidelBoardPro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 글목록에서 여러개 체크박스로 삭제
		// 체크박스 name값 chk를 받아와 배열 board_id 에 저장
		String[] board_id = request.getParameterValues("chk");

		// 커뮤니티 게시판 체크박스 삭제 메서드 호출
		BoardDAO dao = new BoardDAO();
		dao.multiDelete(board_id);

		ActionForward forward = new ActionForward();
		forward.setPath("AdminBoardList.adminpage");
		forward.setRedirect(true);
		return forward;
	}

}
