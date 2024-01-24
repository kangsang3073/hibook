package com.itwillbs.review.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.review.db.StarReviewDAO;
import com.itwillbs.review.db.StarReviewDTO;

public class ReviewWritePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewWritePro execute()");
		request.setCharacterEncoding("utf-8");
		
		String tgt_id = request.getParameter("tgt_id");
		double score = Double.parseDouble(request.getParameter("score"));
		String insert_id = request.getParameter("insert_id");
		String review_content = request.getParameter("review_content");
		Timestamp review_date = new Timestamp(System.currentTimeMillis());
	
		StarReviewDTO dto = new StarReviewDTO();
		StarReviewDAO dao = new StarReviewDAO();
		
		dto.setTgt_id(tgt_id);
		dto.setScore(score);
		dto.setInsert_id(insert_id);
		dto.setReview_content(review_content);
		dto.setReview_date(review_date);
		
		dao.insertReview(dto);
		
		ActionForward forward=new ActionForward();
		forward.setPath("ReviewBox.pr");
		forward.setRedirect(true);
		return forward;
	}
	
}
