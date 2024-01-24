package com.itwillbs.chat.action;

import java.io.IOException;
//import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChatController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController doGet()");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController doPost()");
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController doProcess()");
		
		//가상주소 뽑아오기
		System.out.println("뽑은 가상주소 : "+request.getServletPath());
		String sPath=request.getServletPath();
		
	
		//----------------------------------
		//가상주소 비교 => 실제주소 매핑
		Action action=null;
		ActionForward forward=null;
		HttpSession session = request.getSession();
	    String id = (String) session.getAttribute("id");
		
		if(sPath.equals("/ChatList.hi")) {
			forward=new ActionForward();
			forward.setPath("chat/box.jsp");
			forward.setRedirect(false);
		}else if(sPath.equals("/Chat.hi")) {
			forward=new ActionForward();
			forward.setPath("chat/chat.jsp");
			forward.setRedirect(false);
		}
		
			
			
			
			
		
		//--------------------------------
	
		if(forward!=null) {
			if(forward.isRedirect()==true) {
				response.sendRedirect(forward.getPath());
			}else {

				RequestDispatcher dispatcher=
				request.getRequestDispatcher(forward.getPath());
		        dispatcher.forward(request, response);
			}
		}
		
	}
	

	}

