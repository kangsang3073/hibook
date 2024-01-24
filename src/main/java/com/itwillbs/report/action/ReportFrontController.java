package com.itwillbs.report.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReportFrontController extends HttpServlet {
	// 자동으로 doGet() doPost() 호출

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("reportFrontController doGet()");
		doProcess(request, response);
	}// doGet

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("reportFrontController doPost()");
		doProcess(request, response);
	}
	// doPost

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("reportFrontController doProcess()");
		
		// 가상주소 뽑아오기
		System.out.println("뽑은 가상주소 : " + request.getServletPath());
		String sPath = request.getServletPath();

		// 가상주소 비교=> 실제주소 맵핑
		Action action = null;
		ActionForward forward = null;

		// 로그인 상태가 아니면 로그인 화면으로 이동
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		if (sPath.equals("/ReportForm.re")) {
			// New/ReportList.jsp 이동
			System.out.println("가상주소 /ReportWriteForm.re => 실제페이지 report/ReportWriteForm.jsp");
			// 이동정보를 저장하는 자바파일 객체생성
			forward = new ActionForward();
			forward.setPath("report/ReportForm.jsp");
			forward.setRedirect(false);
			
		} else if (sPath.equals("/ReportWritePro.re")) {
			action = new ReportWritePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (sPath.equals("/ReportList.re")) {
			action = new ReportList();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (sPath.equals("/ReportContent.re")) {
			action = new ReportContent();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (sPath.equals("/ReportUpdateForm.re")) {
			action = new ReportUpdateForm();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (sPath.equals("/ReportUpdatePro.re")) {
			action = new ReportUpdatePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (sPath.equals("/ReportDeletePro.re")) {
			action = new ReportDeletePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (sPath.equals("/List.re")) {
			forward = new ActionForward();
			forward.setPath("report/ListForm.jsp");
			forward.setRedirect(false);
		}

		// 이동
		if (forward != null) {
			if (forward.isRedirect() == true) {
				response.sendRedirect(forward.getPath());
			} else {
				// forward.isRedirect()==false
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		}
	
}// BoardFrontController
