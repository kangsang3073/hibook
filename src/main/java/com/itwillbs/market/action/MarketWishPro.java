package com.itwillbs.market.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.wish.WishDAO;
import com.itwillbs.wish.WishDTO;

public class MarketWishPro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MarketWishPro excute()");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		int market_id = Integer.parseInt(request.getParameter("market_id"));
		
		WishDTO dto = new WishDTO();
		dto.setMarket_id(market_id);
		dto.setInsert_id(id);
		
		WishDAO dao = new WishDAO();
		ArrayList<WishDTO> wishCheck = dao.wishCheck(market_id, id);
		int wishCheckNum = 0;
		
		if (wishCheck.isEmpty()){
			wishCheckNum = dao.insertWish(dto); 
		} else{
			wishCheckNum = dao.deleteWish(dto); 
		}
		
		int result = dao.getMarketWishCount(market_id);
		
		response.setContentType("text/html; charset=UTF-8"); 
		
		PrintWriter out = response.getWriter(); // PrintWrite 자바 출력클래스, response(HttServlet 클래스 내장객체)
		out.println(result);
		out.close();
	
		return null;
	}
	
}
