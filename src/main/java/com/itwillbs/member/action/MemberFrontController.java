package com.itwillbs.member.action;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doProcess(request, response);
	} //doGet()

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	} //doPost()
	

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController doProcess()");
		
		String sPath=request.getServletPath();
		
		System.out.println("가상주소"+sPath);
		
		// 이동정보를 저장하는 자바파일 선언
		ActionForward forward=null;
		//부모 인터페이스 틀 선언
		Action action=null;
		
		
		if(sPath.equals("/MemberInsertForm.me")) {
			forward=new ActionForward();
			forward.setPath("member/insertForm.jsp");
			forward.setRedirect(false);			
		}
		
		
		else if(sPath.equals("/MemberInsertPro.me")) {		
			action=new MemberInsertPro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		else if(sPath.equals("/MemberLoginForm.me")) {
			forward=new ActionForward();
			forward.setPath("member/loginForm.jsp");
			forward.setRedirect(false);
		}
		
		
		else if(sPath.equals("/MemberLoginPro.me")) {
			action = new MemberLoginPro();
			
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}	
		}
		else if (sPath.equals("/MemberIdCheck.me")) {
			System.out.println("==========");
			action=new MemberIdCheck();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
//		else if(sPath.equals("/main.do")) {
//			
//			forward=new ActionForward();
//			forward.setPath("main/main.jsp");
//			forward.setRedirect(false);
//		}
		
		
		else if(sPath.equals("/MemberLogout.me")) {
			action = new MemberLogout();
			
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	
//		String path ="주소정보";
//		boolean isRedirect = true;
		// 이동정보 자바파일 있는지 확인
		if(forward != null) {
			//이동방식비교
			if(forward.isRedirect()==true) {
				// excute함수가 실행되며 forward에 저장된 다른 페이지 주소로 이동함
				response.sendRedirect(forward.getPath());
			}else {
				// 주소가 유지되며 request 값과 가상주소를 들고감
				RequestDispatcher dispatcher=
				request.getRequestDispatcher(forward.getPath());
		        dispatcher.forward(request, response);
			
		
			}
		}
	}//doProcess()
	
}//클래스
