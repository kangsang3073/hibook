package mypage.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MypageFrontController extends HttpServlet{

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
		
		// 마이페이지 메인 
		if(sPath.equals("/MypageMain.mypage")) {
			forward=new ActionForward();
			forward.setPath("mypage/mypageMain.jsp");
			forward.setRedirect(false); 
			
		// 프로필사진 및 닉네임 수정 
		}else if(sPath.equals("/MypageImgUpdateForm.mypage")) {
			action=new MypageImgUpdateForm();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}else if(sPath.equals("/MypageImgUpdatePro.mypage")) {
			action=new MypageImgUpdatePro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		// 회원정보 수정 
		}else if(sPath.equals("/MypageUpdateForm.mypage")) {
			action=new MypageUpdateForm();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}else if(sPath.equals("/MypageUpdatePro.mypage")) {
			action=new MypageUpdatePro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			
		// 내가 쓴 글 (중고거래) 
		}else if(sPath.equals("/MypageMarketList.mypage")) {
			action=new MypageMarketList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		// 중고거래 체크박스 여러개 삭제 
		}else if(sPath.equals("/MypageMultidelMarketPro.mypage")) {
			action=new MypageMultidelMarketPro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		// 내가 쓴 글 (커뮤니티) 
		}else if(sPath.equals("/MypageBoardList.mypage")) {
			action=new MypageBoardList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		// 커뮤니티 체크박스 여러개 삭제 
		}else if(sPath.equals("/MypageMultidelBoardPro.mypage")) {
			action=new MypageMultidelBoardPro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		// 내가 쓴 글 (1:1문의) 	
		}else if(sPath.equals("/MypageReportList.mypage")) {
			action=new MypageReportList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		// 1:1문의 체크박스 여러개 삭제 
		}else if(sPath.equals("/MypageMultidelReportPro.mypage")) {
			action=new MypageMultidelReportPro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		// 내가 쓴 댓글
		}else if(sPath.equals("/MypageCommentList.mypage")) {
			action=new MypageCommentList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		// 내가 쓴 댓글 체크박스 여러개 삭제
		}else if(sPath.equals("/MypageMultidelCommentPro.mypage")) {
			action=new MypageMultidelCommentPro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		// 찜목록 
		}else if(sPath.equals("/MypageWishList.mypage")) {
			action=new MypageWishList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		// 찜목록 체크박스 여러개 삭제
		}else if(sPath.equals("/MypageMultidelWishListPro.mypage")) {
			action=new MypageMultidelWishListPro();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		// 회원탈퇴
		}else if(sPath.equals("/MypageDelete.mypage")) {
			forward=new ActionForward();
			forward.setPath("mypage/mypageDelete.jsp");
			forward.setRedirect(false); 
		}else if(sPath.equals("/MypageDeleteUserCheckPro.mypage")) {
			forward=new ActionForward();
			forward.setPath("mypage/mypageUserCheckPro.jsp");
			forward.setRedirect(false);	
		}else if(sPath.equals("/MypageDeletePro.mypage")) {
			forward=new ActionForward();
			forward.setPath("mypage/mypageDeletePro.jsp");
			forward.setRedirect(false);	
			
		// 중고거래 게시글 본문에서 해당 유저의 판매글목록 조회 
		}else if(sPath.equals("/MypageUserMarketList.mypage")) {
			action=new MypageUserMarketList();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
