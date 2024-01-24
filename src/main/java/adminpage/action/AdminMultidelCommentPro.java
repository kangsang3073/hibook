package adminpage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.comment.CommentDAO;

public class AdminMultidelCommentPro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 댓글목록에서 여러개 체크박스로 삭제
		// 체크박스 name값 chk를 받아와 배열 cmmt_id 에 저장 
		String[] cmmt_id = request.getParameterValues("chk");

		// 체크박스 삭제 메서드 호출 
		CommentDAO dao = new CommentDAO();
		dao.multiDelete(cmmt_id); 

		ActionForward forward=new ActionForward();
		forward.setPath("AdminCommentList.adminpage");
		forward.setRedirect(true);
		return forward;			
	}

}
