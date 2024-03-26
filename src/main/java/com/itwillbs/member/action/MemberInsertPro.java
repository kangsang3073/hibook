package com.itwillbs.member.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

//인터페이스 틀 상속 받기
public class MemberInsertPro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberInsertPro execute()");
		
		//폼에서 입력한 내용이 서버에 전달 => request 내장객체 저장
		//request 한글처리
		request.setCharacterEncoding("utf-8");
		
		String uploadPath=request.getRealPath("/upload");
		
		int maxSize=10*1024*1024;
		
		MultipartRequest multi=new MultipartRequest
				(request, uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
				System.out.println(uploadPath);
				
				
				
		// request 태그이름에 해당하는 값을 가져오기 => 변수에 저장
		String mem_id=multi.getParameter("mem_id");
		String mem_pass=multi.getParameter("mem_pass");
		String mem_nm=multi.getParameter("mem_nm");
		String nickname=multi.getParameter("nickname");
		String birth=multi.getParameter("birth");
		String zipcode=multi.getParameter("zipcode");
		String addr=multi.getParameter("addr");
		String addr_dtl=multi.getParameter("addr_dtl");
		String email=multi.getParameter("email");
		String phone=multi.getParameter("phone");
		String mem_st=multi.getParameter("mem_st");
		String admin_yn=multi.getParameter("admin_yn");
		String sns_type=multi.getParameter("sns_type");
		String sns_id=multi.getParameter("sns_id");
		Timestamp join_date=new Timestamp(System.currentTimeMillis());
		Timestamp delete_date=new Timestamp(System.currentTimeMillis());
		
		String mem_img=multi.getFilesystemName("mem_img");
		MemberDTO dto=new MemberDTO();
		System.out.println("MemberDTO 바구니 주소 : "+dto);
		
		
		// => 기억장소에 id, pass, name, date 값을 저장
		
		dto.setMemId(mem_id);
		dto.setMemPass(mem_pass);
		dto.setMemNm(mem_nm);
		dto.setNickname(nickname);
		dto.setBirth(birth);
		dto.setZipcode(zipcode);
		dto.setAddr(addr);
		dto.setAddrDtl(addr_dtl);
		dto.setEmail(email);
		dto.setPhone(phone);
		dto.setMemImg(mem_img);
		dto.setMemSt(mem_st);
		dto.setAdminYn(admin_yn);
		dto.setSnsType(sns_type);
		dto.setSnsId(sns_id);
		dto.setJoinDate(join_date);
		dto.setDeleteDate(delete_date);
		
		MemberDAO dao=new MemberDAO();
		System.out.println("MemberDAO 주소 : "+dao);
		// dao.insertMember(id, pass, name, date);
		
		if(dao.getMember(mem_id) != null) {
			alertHistoryBack(response, "중복된 아이디입니다.");
			return null;
		}
		
//		if(dao.nicknameCheck(nickname) != null) {
//			alertHistoryBack(response, "중복된 닉네임입니다.");
//			return null;
//		}
		
		
		dao.insertMember(dto);
		
//		//MemberLoginForm.me 주소변경되면서 이동정보 저장 
//		ActionForward forward=new ActionForward();
//		forward.setPath("MemberLoginForm.me");
//		forward.setRedirect(true);
//		
//		return forward;
		
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out =response.getWriter(); 
		
		out.println("<script type=\'text/javascript\'>"); 
		out.println("alert(\'회원가입이 완료되었습니다.\');"); 
		out.println("location.href='MemberLoginForm.me'"); 
		
		out.println("</script>"); 
		out.close();
		
		return null;
	}
	
	
	public void alertHistoryBack(HttpServletResponse response, String msg) throws IOException {
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out =response.getWriter(); 
		
		out.println("<script type=\'text/javascript\'>"); 
		out.println("alert(\'"+msg+"\');"); 
		out.println("history.back();"); 
		out.println("</script>"); 
		out.close(); 
		
	}

}
