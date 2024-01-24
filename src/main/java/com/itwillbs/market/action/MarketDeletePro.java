package com.itwillbs.market.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.market.db.MarketDAO;

public class MarketDeletePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MarketDeletePro execute()");
		
		int market_id = Integer.parseInt(request.getParameter("market_id"));
		
		MarketDAO dao = new MarketDAO();
		dao.deleteBoard(market_id);
		
		ActionForward forward = new ActionForward();
		forward.setPath("MarketList.ma");
		forward.setRedirect(true);
		return forward;
	}
	
}
