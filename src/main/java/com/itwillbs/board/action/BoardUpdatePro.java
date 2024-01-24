package com.itwillbs.board.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardUpdatePro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		
		// 객체생성
		// 1. request
		// 2. 웹서버 업로드 폴더 위치 -> webapp - upload 폴더 만들기
		String uploadPath = request.getRealPath("/upload");
		// 3. 파일크기
		int maxSize = 100*1024*1024;
		// 4. 한글처리
		// 5. 업로드 파일이름 동일할 때 -> 파일이름변경(DefaultFileRenamePolicy())
		
		MultipartRequest  multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
				
				
		// 폼에서 입력한 내용이 서버에 전달 → request 내장객체 저장 ,
		// request 한글처리
		request.setCharacterEncoding("utf-8");
		// request 태그이름에 해당하는 값을 가져오기 → 변수에 저장(id,pass,name)
		int boardId = Integer.parseInt(multi.getParameter("boardId"));
		String insertId = multi.getParameter("insertId");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String contentImg1 = multi.getFilesystemName("contentImg1");
		String contentImg2 = multi.getFilesystemName("contentImg2");
		String contentImg3 = multi.getFilesystemName("contentImg3");
		String boardType = multi.getParameter("boardType");
		
		if(contentImg1 == null) {
			contentImg1 = multi.getParameter("oldfile1");
		}
		
		if(contentImg2 == null) {
			contentImg2 = multi.getParameter("oldfile2");
		}
		
		if(contentImg3 == null) {
			contentImg3 = multi.getParameter("oldfile3");
		}

		// 수정할 내용을 바구니 객체생성 -> 바구니에 저장
		BoardDTO updateDto = new BoardDTO();

		// set 메서드호출
		updateDto.setBoardId(boardId);
		updateDto.setInsertId(insertId);
		updateDto.setTitle(title);
		updateDto.setContent(content);
		updateDto.setContentImg1(contentImg1);
		updateDto.setContentImg2(contentImg2);
		updateDto.setContentImg3(contentImg3);

		// MemberDAO 객체생성
		BoardDAO dao = new BoardDAO();

		// updateBoard(updateDto)메서드 호출
		dao.updateBoard(updateDto);

		// forward 객체생성 전 변수선언.
		ActionForward forward = new ActionForward();
		// forward.setPath("BoardList.bo");
		forward.setPath("BoardList.bo?boardType="+boardType);
		forward.setRedirect(true);
		return forward;
	}

}
