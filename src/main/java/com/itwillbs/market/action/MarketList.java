package com.itwillbs.market.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.market.db.MarketDAO;
import com.itwillbs.market.db.MarketDTO;

public class MarketList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MarketList excute()");
		
		//중고책 거래 종류
		String trade_type = "";
		if(request.getParameter("trade_type")!=null) {
			trade_type = request.getParameter("trade_type");
		}
		
		MarketDAO dao = new MarketDAO();
		//한 페이지에 불러올 게시글 개수
		int pageSize = 16;

		//받아온 페이지 번호가 없다면 1, 아니면 해당 페이지 번호 저장
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		
		/* DB에서 가져올 게시글 */
		//현재 지정된 페이지 번호
		int currentPage=Integer.parseInt(pageNum);
		//시작 게시글 번호
		int startRow = pageSize*(currentPage-1)+1;
		//끝 게시글 번호
		int endRow = startRow+pageSize-1;
		//mysql limit 함수 변수
		int start = startRow-1; //시작행(mySQL의 limit는 시작행으로 받을 매개변수에서 +1부터 검색한다)
		int num = pageSize; //시작행으로부터 가져올 갯수
		
		//DAO에서 데이터 받기
		ArrayList<MarketDTO> dtolist = new ArrayList<>();
		if(!(trade_type.equals(""))) {
			dtolist = dao.getMenuMarketList(trade_type, start, num);
		} else {
			dtolist = dao.getMarketList(start, num);
		}
		
		/* 현재 페이지 번호에 따른 시작페이지, 끝페이지 계산 */
		int pageBlock = 5;
		//시작 페이지 번호
		int startPage = (currentPage-1)/pageBlock*pageBlock+1;
		//끝 페이지 번호
		int endPage = startPage+pageBlock-1;
		//총 게시글 수
		int writeCount = 0;
		if(!(trade_type.equals(""))) {
			writeCount = dao.getMenuWriteCount(trade_type);
		} else {
			writeCount = dao.getBoardWriteCount();
		}
		//나타낼 총 페이지 수
		int pageCount = writeCount/pageSize+(writeCount%pageSize==0?0:1);
		//endPage 재설정
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		/* request에 담아서 list.jsp로 가져가기 */
		//게시글 정보
		request.setAttribute("dtolist", dtolist);
		request.setAttribute("trade_type", trade_type);
		//페이징 정보
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);

		//이동
		ActionForward forward = new ActionForward();
		forward.setPath("market/MarketList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
}// 클래스
