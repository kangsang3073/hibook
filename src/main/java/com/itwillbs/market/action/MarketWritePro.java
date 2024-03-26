package com.itwillbs.market.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.market.db.MarketDAO;
import com.itwillbs.market.db.MarketDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MarketWritePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MarketWritePro excute()");	
		
		request.setCharacterEncoding("utf-8");
		
		String insert_id = request.getParameter("insert_id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String[] imgUrls = request.getParameterValues("imgUrls");
		String book_price = request.getParameter("book_price");
		String book_st = request.getParameter("book_st");
		String book_type = request.getParameter("book_type");
		String trade_type = request.getParameter("trade_type");
		String trade_st = request.getParameter("trade_st");
		String trade_inperson = request.getParameter("trade_inperson");
		
		MarketDTO dto = new MarketDTO();
		dto.setInsert_id(insert_id);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setInsert_date(new Timestamp(System.currentTimeMillis()));
		dto.setImgUrls(imgUrls);
		dto.setBook_price(book_price);
		dto.setBook_st(book_st);
		dto.setBook_type(book_type);
		dto.setTrade_inperson(trade_inperson);
		dto.setTrade_type(trade_type);
		dto.setTrade_st(trade_st);
		
		MarketDAO dao = new MarketDAO();
		dao.insertBoard(dto);
		
		System.out.println("글작성 -> 상세정보");
		
		dto = dao.getBoard(dao.getMarketId(insert_id));
			
		request.setAttribute("dto", dto);
			
		ActionForward forward = new ActionForward();
		forward.setPath("market/MarketContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
}// 클래스
