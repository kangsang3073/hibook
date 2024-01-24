package mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypage.member.MemberDAO;
import mypage.member.MemberDTO;

public class MypageUpdateForm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션에서 id 값 가져오기
		 HttpSession session=request.getSession();
		 String id=(String)session.getAttribute("id");
		 
		// db의 회원정보 뿌려줘야함 
		// MemberDAO 객체생성
		 MemberDAO dao=new MemberDAO();
		 MemberDTO dto=dao.getMember(id);
		 
		// request dto 저장
		request.setAttribute("dto", dto);
		
		// 뷰페이지로 이동
		ActionForward forward=new ActionForward();
		forward.setPath("mypage/mypageUpdate.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
