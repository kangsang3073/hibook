package com.itwillbs.main.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDTO;
// 메인 주소를 main.do 로 로그인하기 위한 컨트롤러
@WebServlet("*.do")
public class MainFrontController extends HttpServlet {
	// alt shift s-> v : 메서드 재정의
	// 자동으로 doGet() doPost() 호출

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 가상주소 뽑아오기
		System.out.println("뽑은 가상주소 : " + request.getServletPath());
		String spath = request.getServletPath();

		// 가상주소 비교
		Action action = null;
		ActionForward forward = null;
		
		MainPro mainPro = new MainPro();
		
		if (spath.equals("/main.do")) {
			
			// 자유게시판 글 조회(가져오기)
			ArrayList<BoardDTO> freeList = mainPro.getMainBoardList(request, response, "free");
			
			// 도서리뷰게시판 글 조회(가져오기)
			ArrayList<BoardDTO> reviewList = mainPro.getMainBoardList(request, response, "review");
			
			// 자유,도서리뷰 값 담기
			request.setAttribute("freeList", freeList);
			request.setAttribute("reviewList", reviewList);

			forward = new ActionForward();
			forward.setPath("main/main.jsp");
			forward.setRedirect(false);
			
			
		} 
		// 이동
		if(forward != null) {
			
			if(forward.isRedirect() == true) {
				response.sendRedirect(forward.getPath());
			
			} else{
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	} // doProcess()
} // 클래스
