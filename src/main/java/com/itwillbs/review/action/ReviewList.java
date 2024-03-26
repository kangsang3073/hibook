package com.itwillbs.review.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.review.db.StarReviewDAO;
import com.itwillbs.review.db.StarReviewDTO;

public class ReviewList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewList execute()");
		String tgt_id = "";
		
		//로그인 ID받기
		HttpSession session=request.getSession();
		String id = (String)session.getAttribute("id");
		System.out.println(id);
		tgt_id = id;
		
		//작성자 ID받기
		String insert_id = "";
		if(request.getParameter("insert_id") != null) {
		insert_id = request.getParameter("insert_id");
		tgt_id = insert_id;
		}
		System.out.println(tgt_id);
		
		StarReviewDAO dao=new StarReviewDAO();
		
		int pageSize=3;
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";		
		}
		
		int currentPage=Integer.parseInt(pageNum);
		int startRow=(currentPage-1)*pageSize+1;
		int endRow = startRow+pageSize-1;
		
		//getReviewList 함수 호출
		ArrayList<StarReviewDTO> boardList = null;
		boardList=dao.getReviewList(tgt_id, startRow,pageSize);
		
		int pageBlock=3;
		int startPage=(currentPage-1)/pageBlock*pageBlock+1;
		int endPage=startPage+pageBlock-1;
		
		//getReviewCount 함수 호출
		int count = 0;
		count = dao.getReviewCount(tgt_id);

		
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		if(endPage > pageCount){
			endPage = pageCount;
		}
		
		// request 가져온 데이터 담기
		request.setAttribute("boardList", boardList);
		request.setAttribute("tgt_id", tgt_id);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		
		// 이동
		ActionForward forward=new ActionForward();
		forward.setPath("profile/profile.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
