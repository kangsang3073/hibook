package com.itwillbs.board.action;

import java.io.File;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWritePro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardWritePro execute()");
		
		// board/writePro.jsp
		// request 한글처리
		request.setCharacterEncoding("utf-8");
		
		// 객체생성
		// 1. request // 2. 웹서버 업로드 폴더 위치 -> webapp - upload 폴더 만들기
		String uploadPath = request.getRealPath("/upload");
		
		// 3. 파일크기
		int maxSize = 100 * 1024 * 1024;
		
		// 4. 업로드 파일이름 동일할 때 -> 파일이름변경(DefaultFileRenamePolicy())
		MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8",
				new DefaultFileRenamePolicy());

		// request 변수에 저장
		Timestamp insertDate = new Timestamp(System.currentTimeMillis());
		String boardType = multi.getParameter("boardType");
		//String noticeYn = multi.getParameter("noticeYn");
		String noticeYn = multi.getParameter("noticeYn")== null ? "N" : multi.getParameter("noticeYn");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		int parentId = Integer.parseInt(multi.getParameter("parentId"));
		String insertId = multi.getParameter("insertId");
		String contentImg1 = multi.getFilesystemName("contentImg1");
		String contentImg2 = multi.getFilesystemName("contentImg2");
		String contentImg3 = multi.getFilesystemName("contentImg3");

		// BoardDTO 객체생성
		BoardDTO dto = new BoardDTO();

		// BoardDAO 객체생성
		BoardDAO dao = new BoardDAO();
		
		int ref = 0;
		if(parentId == 0) { // parentId가 0이면
			// 원글이고, 답글이 아니고, parentId==0 
			ref = dao.getRef();
			
		} else {
			// 원글이 아니고, 답글이고, parentId !=0 
			ref = dao.getRefRe(parentId);
			
		}
		// set메서드 호출해서 값 저장
		// dto.setNum(num);
		dto.setBoardType(boardType);
		dto.setNoticeyn(noticeYn);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setParentId(parentId);
		dto.setInsertId(insertId);
		dto.setInsertDate(insertDate);
		dto.setContentImg1(contentImg1);
		dto.setContentImg2(contentImg2);
		dto.setContentImg3(contentImg3);
		dto.setRef(ref);

		// insertBoard(dto) 메서드 호출
		dao.insertBoard(dto);

		ActionForward forward = new ActionForward();
		// forward.setPath("BoardList.bo");
		forward.setPath("BoardList.bo?boardType="+boardType);
		forward.setRedirect(true);
		return forward;
	} // if 끝


}
