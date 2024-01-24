package mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypage.wish.WishDAO;

public class MypageMultidelWishListPro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 찜목록에서 여러개 체크박스로 삭제
		// 체크박스 name값 chk를 받아와 배열 market_id 에 저장 
		String[] market_id = request.getParameterValues("chk");

		//로그인 id 세션 받아오기 
		HttpSession session=request.getSession();
		String id = (String)session.getAttribute("id");

		// 체크박스 삭제 메서드 호출 
		WishDAO dao = new WishDAO();
		dao.multiDelete(market_id, id); 

		ActionForward forward=new ActionForward();
		forward.setPath("MypageWishList.mypage");
		forward.setRedirect(true);
		return forward;
	}

}
