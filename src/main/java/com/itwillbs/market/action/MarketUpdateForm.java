package com.itwillbs.market.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.market.db.MarketDAO;
import com.itwillbs.market.db.MarketDTO;

public class MarketUpdateForm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MarketUpdateForm execut()");
		
		int market_id = Integer.parseInt(request.getParameter("market_id"));
		
		MarketDAO dao = new MarketDAO();
		MarketDTO dto = dao.getBoard(market_id);
		
		request.setAttribute("dto", dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("market/MarketUpdateForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
}
