package adminpage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.market.MarketDAO;

public class AdminMultidelMarketPro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 글목록에서 여러개 체크박스로 삭제
		// 체크박스 name값 chk를 받아와 배열 market_id 에 저장
		String[] market_id = request.getParameterValues("chk");

		// 중고거래 게시판 체크박스 삭제 메서드 호출
		MarketDAO dao = new MarketDAO();
		dao.multiDelete(market_id);

		ActionForward forward = new ActionForward();
		forward.setPath("AdminMarketList.adminpage");
		forward.setRedirect(true);
		return forward;
	}

}
