package mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypage.member.MemberDAO;
import mypage.member.MemberDTO;

public class MypageUpdatePro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 프로필사진,닉네임수정 pro mypageImgUpdatePro.jsp
		// request 파라미터 => 변수저장
		request.setCharacterEncoding("utf-8");
		String mem_pass = request.getParameter("mem_pass");
		String phone = request.getParameter("phone");
		String zipcode = request.getParameter("zipcode");
		String addr = request.getParameter("addr");
		String addr_dtl = request.getParameter("addr_dtl");
		String email = request.getParameter("email");
		// 세션에서 id 값 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		// MemberDTO 객체생성
		// set메서드호출, 저장
		MemberDTO dto = new MemberDTO();
		dto.setMem_pass(mem_pass);
		dto.setPhone(phone);
		dto.setZipcode(zipcode);
		dto.setAddr(addr);
		dto.setAddr_dtl(addr_dtl);
		dto.setEmail(email);
		dto.setMem_id(id);
		System.out.println("------------------------------1");
		// MemberDAO 객체생성
		// 메서드 호출
		MemberDAO dao = new MemberDAO();
		dao.updateMember(dto);

//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out=response.getWriter();
//		out.println("<script type='text/javascript'>");
//		out.println("alert('회원정보 수정이 완료되었습니다.');");
//		out.println("</script>");
//		out.close();

		// 이동
		ActionForward forward = new ActionForward();
		forward.setPath("MypageUpdateForm.mypage");
		forward.setRedirect(true);

		return forward;

	}

}
