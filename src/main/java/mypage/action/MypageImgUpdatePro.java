package mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mypage.member.MemberDAO;
import mypage.member.MemberDTO;

public class MypageImgUpdatePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//객체생성
		// 1. request 
		// 2. 웹서버 업로드 폴더 위치 => webapp - upload 폴더 만들기
		String uploadPath=request.getRealPath("/upload");
		// 3. 파일크기  10M
		int maxSize=10*1024*1024;
		// 4. 한글처리
		// 5. 업로드 파일이름 동일할때 => 파일이름변경(DefaultFileRenamePolicy)
		MultipartRequest multi=new MultipartRequest
		(request, uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());

		// 프로필사진,닉네임수정 pro mypageImgUpdatePro.jsp
		//  multi 파라미터 => 변수저장 
		String mem_img = multi.getFilesystemName("mem_img");
		//수정할 업로드 파일이 없으면 기존파일 유지
		if(mem_img==null) {
			mem_img=multi.getParameter("oldfile");
		}
		String nickname = multi.getParameter("nickname");
		//세션에서 id 값 가져오기
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");

		// MemberDTO 객체생성
		// set메서드호출, 저장 
		MemberDTO dto = new MemberDTO();
		dto.setMem_img(mem_img);
		dto.setNickname(nickname);
		dto.setMem_id(id);

		// MemberDAO 객체생성 
		// 리턴할형 없음 updateMemberImg(MemberDTO dto) 메서드 정의  
		// 메서드 호출 
		MemberDAO dao = new MemberDAO();
		dao.updateMemberImg(dto);

		// 마이페이지 메인으로 이동 
		ActionForward forward=new ActionForward();
		forward.setPath("MypageMain.mypage");
		forward.setRedirect(true);
		return forward;
	}

}
