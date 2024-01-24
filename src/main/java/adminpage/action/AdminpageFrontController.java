package adminpage.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminpageFrontController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String sPath=request.getServletPath();
		
		//------------------------------------------------
		//가상 주소 매핑
		Action action=null;
		ActionForward forward=null;
		
		// 관리자페이지 - 회원목록 보기 
		if(sPath.equals("/AdminMemberList.adminpage")) {
			action=new AdminMemberList(); 
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		// 게시글 관리 - 중고거래 
		}else if(sPath.equals("/AdminMarketList.adminpage")) {
			action=new AdminMarketList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		// 중고거래 글목록 체크박스 여러개 삭제 	
		}else if(sPath.equals("/AdminMultidelMarketPro.adminpage")) {
			action=new AdminMultidelMarketPro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		// 게시글 관리 - 커뮤니티
		}else if(sPath.equals("/AdminBoardList.adminpage")) {
			action=new AdminBoardList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		// 커뮤니티 글목록 체크박스 여러개 삭제	
		}else if(sPath.equals("/AdminMultidelBoardPro.adminpage")) {
			action=new AdminMultidelBoardPro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		// 댓글 관리 
		}else if(sPath.equals("/AdminCommentList.adminpage")) {
			action=new AdminCommentList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		// 댓글목록 체크박스 여러개 삭제
		}else if(sPath.equals("/AdminMultidelCommentPro.adminpage")) {
			action=new AdminMultidelCommentPro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		// 1:1 신고 접수
		}else if(sPath.equals("/AdminReportList.adminpage")) {
			action=new AdminReportList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		// 1:1 신고 체크박스 여러개 삭제
		}else if(sPath.equals("/AdminMultidelReportPro.adminpage")) {
			action=new AdminMultidelReportPro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 		
		// 1:1 신고 회원 정지처리 
		}else if(sPath.equals("/AdminReportPro.adminpage")) {
			forward=new ActionForward();
			forward.setPath("adminpage/adminReportPro.jsp");
			forward.setRedirect(false); 
		}
		
		
		
		//----------------------------------------------
		//주소 이동
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
		
		
	}//doProcess()

}//MypageFrontController
