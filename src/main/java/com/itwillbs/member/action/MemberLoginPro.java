package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberLoginPro implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberLoginPro excute()");
		
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		
		MemberDAO dao=new MemberDAO();
		MemberDTO dto=dao.userCheck(id, pass);
		
		ActionForward forward = null;
		
		if(dto !=null){	
			if(dto.getMemSt().equals("정지") || dto.getMemSt().equals("탈퇴")){
				response.setContentType("text/html; charset=UTF-8"); // 자바에서 html(자바스크립트) 동작 코드 생성
				PrintWriter out = response.getWriter();
				out.println("<script type='text/javascript'>"); // PrintWrite 자바 출력클래스, response(HttServlet 클래스 내장객체)
				out.println("alert('로그인할 수 없는 회원입니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				
			} else {
				HttpSession session=request.getSession();
				session.setAttribute("id", id);
				

				// 관리자만 공지사항 쓸 수있음 
				session.setAttribute("adminYn", dto.getAdminYn());
				
				forward = new ActionForward();
				forward.setPath("main.do");
				forward.setRedirect(true);
			}
			
		}
		else{
			// id, pass 틀리면 MemberDTO 빈(null) 바구니 가져오기
			// => script   "아이디 비밀번호 틀림" 뒤로이동
//		    <script type="text/javascript">
//				alert("아이디 비밀번호 틀림");
//				history.back();
//		    </script>
			response.setContentType("text/html; charset=UTF-8"); // 자바에서 html(자바스크립트) 동작 코드 생성
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>"); // PrintWrite 자바 출력클래스, response(HttServlet 클래스 내장객체)
			out.println("alert('아이디 비밀번호가 틀립니다');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			//이동정보
			forward = null;
		}
		
		return forward;
	}
}
