package com.itwillbs.market.action;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.market.db.MarketDAO;
import com.itwillbs.market.db.MarketDTO;

public class MarketContent implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MarketContent execute()");
		
		System.out.println("리스트 -> 상세정보");
		
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");

		int market_id = Integer.parseInt(request.getParameter("market_id")); 
			
		MarketDAO dao = new MarketDAO();
		MarketDTO dto = dao.getBoard(market_id);
			
		if (id!=null) { 
			System.out.println("조회수증가");
			dao.updateReadCount(dto); 
			dto = dao.getBoard(market_id);
		}
		
		request.setAttribute("dto", dto);
			
		ActionForward forward = new ActionForward();
		forward.setPath("market/MarketContent.jsp");
		forward.setRedirect(false);
			
		return forward;
			
		
	}

}
	
