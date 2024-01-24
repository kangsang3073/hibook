package com.itwillbs.boardComment.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("*.cmmt")
public class BoardCmmtFrontController extends HttpServlet {
	// alt shift s-> v : 메서드 재정의
	// 자동으로 doGet() doPost() 호출

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("BoardFrontConTroller doGet()");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("BoardFrontConTroller doPost()");
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("BoardFrontConTroller doProcess()");

		// 가상주소 뽑아오기
		System.out.println("뽑은 가상주소 : " + request.getServletPath());
		String spath = request.getServletPath();

		// 가상주소 비교
		Action action = null;
		ActionForward forward = null;
		
		if (spath.equals("/BoardCmmtWritePro.cmmt")) {
			action = new BoardCmmtWritePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(spath.equals("/BoardCmmtUpdatePro.cmmt")) {
			action = new BoardCmmtUpdatePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(spath.equals("/BoardCmmtDeletePro.cmmt")) {
			action = new BoardCmmtDeletePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(spath.equals("/BoardCmmtContent.cmmt")) {
			action = new BoardCmmtContent();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 이동
		if(forward != null) {
			
			if(forward.isRedirect() == true) {
				response.sendRedirect(forward.getPath());
			
			} else{
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}// if
	} // doProcess()
} // 클래스
