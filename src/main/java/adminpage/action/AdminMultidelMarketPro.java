package adminpage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.market.MarketDAO;

public class AdminMultidelMarketPro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 글목록에서 여러개 체크박스로 삭제
		// 체크박스 name값 chk를 받아와 배열 market_id 에 저장 
		String[] market_id = request.getParameterValues("chk");

		// 먼저 foreign key가 있는 채팅, 찜 테이블에서 삭제 
		// 이유: 부모(중고거래 게시판)는 자식(채팅, 찜)이 있으면 삭제할 수 없음 
		// 이후 중고거래 게시판 체크박스 삭제 메서드 호출 
		MarketDAO dao = new MarketDAO();
//		dao.multiDelete2(market_id); 
		dao.multiDelete3(market_id); 
		dao.multiDelete(market_id); 

		ActionForward forward=new ActionForward();
		forward.setPath("AdminMarketList.adminpage");
		forward.setRedirect(true);
		return forward;
	}

}
