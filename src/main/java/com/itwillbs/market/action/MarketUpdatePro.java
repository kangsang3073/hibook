package com.itwillbs.market.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.market.db.MarketDAO;
import com.itwillbs.market.db.MarketDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MarketUpdatePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MarketUpdatePro execute()");
		
		request.setCharacterEncoding("utf-8");
		
		int market_id = Integer.parseInt(request.getParameter("market_id"));
		
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
		
		String[] oldImgUrls = request.getParameterValues("oldImgUrls");
		int oldImgNum = Integer.parseInt(request.getParameter("oldImgNum"));
		int preImgNum = Integer.parseInt(request.getParameter("preImgNum"));
		int urlNum = MarketDTO.getImgLengthMax()-oldImgNum-preImgNum;
		
		System.out.println("oldimg개수 : "+oldImgNum);
		for(String str : oldImgUrls) {
			System.out.println("oldimgUrls : "+str);
		}
		
		System.out.println("preimg개수 : "+preImgNum);
		for(String str : imgUrls) {
			System.out.println("preimgUrls : "+str);
		}
		
		if(oldImgNum!=0) {
			String[] copyOldImgUrls = Arrays.copyOfRange(oldImgUrls, 0, oldImgNum);
			String[] copyPreImgUrls = Arrays.copyOfRange(imgUrls, 0, preImgNum);
			ArrayList<String> arr = new ArrayList<>();
			arr.addAll(Arrays.asList(copyOldImgUrls));
			arr.addAll(Arrays.asList(copyPreImgUrls));
			if(urlNum != 0) {
				for(int i = 0; i < urlNum; i++) {
					arr.add("url");
				}
			}
			imgUrls = arr.toArray(new String[arr.size()]);
		}
		
		for(String str : imgUrls) {
			System.out.println("new imgUrls : "+str);
		}
		
		MarketDTO dto = new MarketDTO();
		dto.setMarket_id(market_id);
		dto.setInsert_id(insert_id);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setImgUrls(imgUrls);
		dto.setBook_price(book_price);
		dto.setBook_st(book_st);
		dto.setBook_type(book_type);
		dto.setTrade_inperson(trade_inperson);
		dto.setTrade_type(trade_type);
		dto.setTrade_st(trade_st);
		
		MarketDAO dao = new MarketDAO();
		dao.updateBoard(dto);
		
		System.out.println("글수정 -> 상세정보");
	
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");
			
		dto = dao.getBoard(market_id);
			
		request.setAttribute("dto", dto);
			
		ActionForward forward = new ActionForward();
		forward.setPath("market/MarketContent.jsp");
		forward.setRedirect(false);
			
		return forward;
		
	}
	
}
