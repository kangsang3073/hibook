package com.itwillbs.market.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MarketFrontController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MarketFrontController doProcess()");
		
		String sPath=request.getServletPath();
		
		System.out.println("가상주소"+sPath);
		
		ActionForward forward=null;
		Action action=null;

		if(sPath.equals("/MarketList.ma")) {
			action = new MarketList();
			
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		else if(sPath.equals("/MarketSortList.ma")) {
			action = new MarketSortList();
			
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		else if(sPath.equals("/MarketWriteForm.ma")) {
			forward=new ActionForward();
			forward.setPath("market/MarketWriteForm.jsp");
			forward.setRedirect(false);
		}
		
		
		else if(sPath.equals("/MarketWritePro.ma")) {
			action = new MarketWritePro();
			
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		
		else if(sPath.equals("/MarketContent.ma")) {
			action = new MarketContent();
			
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		else if(sPath.equals("/MarketDeletePro.ma")) {
			action = new MarketDeletePro();
			
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		else if(sPath.equals("/MarketUpdateForm.ma")) {
			action = new MarketUpdateForm();
			
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		else if(sPath.equals("/MarketUpdatePro.ma")) {
			action = new MarketUpdatePro();
			
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		else if(sPath.equals("/MarketWishPro.ma")) {
			action = new MarketWishPro();
			
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
		
		if(forward != null) {
			//이동방식비교
			if(forward.isRedirect()==true) {
				// excute함수가 실행되며 forward에 저장된 다른 페이지 주소로 이동함
				response.sendRedirect(forward.getPath());
			}else {
				// 주소가 유지되며 request 값과 가상주소를 Object class형으로 들고감
				RequestDispatcher dispatcher=
				request.getRequestDispatcher(forward.getPath());
		        dispatcher.forward(request, response);
			}
		}
		
	}
	
}//class
