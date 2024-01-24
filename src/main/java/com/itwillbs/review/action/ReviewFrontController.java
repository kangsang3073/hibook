package com.itwillbs.review.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReviewFrontController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ReviewFrontController doGet 실행");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ReviewFrontController doPost 실행");
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("ReviewFrontController doProcess 실행");
		System.out.println("MemberFrontController doProcess()");
		//주소매핑
		// 가상주소 http://localhost:8080/Model2/MemberInsertForm.me
		//   /MemberInsertForm.me  가상주소 뽑아오기
		System.out.println("뽑은 가상주소 : "+request.getServletPath());
		String sPath=request.getServletPath();
		
		// 이동정보를 저장하는 자바파일 선언
		ActionForward forward=null;
		//부모 인터페이스 틀 선언
		Action action=null;	
		if(sPath.equals("/profile.pr")) {
			action=new ReviewList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (sPath.equals("/ReviewWrite.pr")) {
			forward=new ActionForward();
			forward.setPath("profile/ReviewWrite.jsp");
			forward.setRedirect(false);
		} else if(sPath.equals("/ReviewWritePro.pr")) {
			action=new ReviewWritePro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (sPath.equals("/ReviewBox.pr")) {
			forward=new ActionForward();
			forward.setPath("profile/ReviewWriteClose.jsp");
			forward.setRedirect(false);
		}
			
			if(forward!=null) {
				if(forward.isRedirect()==true) {
					response.sendRedirect(forward.getPath());
				}else {
					//forward.isRedirect()==false
					RequestDispatcher dispatcher=
					request.getRequestDispatcher(forward.getPath());
			        dispatcher.forward(request, response);
				}
			}
		
	} // doprocess
} //board Front Controller
