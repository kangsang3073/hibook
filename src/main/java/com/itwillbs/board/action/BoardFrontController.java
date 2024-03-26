package com.itwillbs.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




public class BoardFrontController extends HttpServlet {
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
		//로그인 상태가 아니면 로그인 화면으로 이동
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		if (spath.equals("/BoardWriteForm.bo")) {
			loginCheck(response, id); //로그인 상태가 아니면 로그인 화면으로 이동
			action = new BoardWriteForm();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(spath.equals("/BoardWritePro.bo")) {
			action = new BoardWritePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(spath.equals("/BoardList.bo")) {
			action = new BoardList();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(spath.equals("/BoardContent.bo")) {
			action = new BoardContent();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(spath.equals("/BoardUpdateForm.bo")) {
			loginCheck(response, id);
			action = new BoardUpdateForm();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(spath.equals("/BoardUpdatePro.bo")) {
			action = new BoardUpdatePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(spath.equals("/BoardDeletePro.bo")) {
			action = new BoardDeletePro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(spath.equals("/Introduce.bo")) {
			forward=new ActionForward();
			forward.setPath("board/introduce.jsp");
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
	
	
	//로그인 상태가 아니면 로그인 화면으로 이동하는 함수 
	public void loginCheck(HttpServletResponse response, String id) throws IOException  {
		if(id == null) {
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out =response.getWriter(); 
			
			out.println("<script type=\'text/javascript\'>"); 
			out.println("alert(\'로그인을 해주세요.\');"); 
			out.println("location.href=\'MemberLoginForm.me\'"); 
			out.println("</script>"); 
			out.close(); 
		}
	}
	
	
	
	
	
} // 클래스
