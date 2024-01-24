package com.itwillbs.report.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.AbstractDocument.Content;

import com.itwillbs.report.db.ReportDAO;
import com.itwillbs.report.db.ReportDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReportWritePro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("\"ReportWritePro execute()");
		// 4. 한글처리
		request.setCharacterEncoding("utf-8");

		// 객체생성
		// 1. request // 2. 웹서버 업로드 폴더 위치 -> webapp - upload 폴더 만들기
		String uploadPath = request.getRealPath("/upload");

		// 3. 파일크기 10M
		int maxSize = 100 * 1024 * 1024;

		// 4. 업로드 파일이름 동일할 때 -> 파일이름변경(DefaultFileRenamePolicy())
		MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8",
				new DefaultFileRenamePolicy());

		Timestamp date = new Timestamp(System.currentTimeMillis());
		String id = multi.getParameter("id");
		String title = multi.getParameter("title");
		String contnet = multi.getParameter("content");
		String contentImg1 = multi.getFilesystemName("content_img1");

		// BoardDTO 객체생성
		ReportDTO dto = new ReportDTO();

		// BoardDAO 객체생성
		ReportDAO dao = new ReportDAO();

		// set메서드 호출해서 값 저장

		dto.setInsert_id(id);
		dto.setTitle(title);
		dto.setContent(contnet);
		dto.setInsert_date(date);
		dto.setContent_img1(contentImg1);
		dto.setInsert_date(date);

		System.out.println(dto.getInsert_id());
		// 패키지 board 파일이름 BoardDAO
		// 리턴할형없음 insertBoard(BoardDTO dto) 메서드 정의

		// insertBoard(dto) 메서드 호출 => 첫번째 ? pstmt.setInt(1, 1));
		dao.insertreport(dto);

		// 글목록 list.jsp
//		response.sendRedirect("list.jsp");
		ActionForward forward = new ActionForward();
		forward.setPath("ReportList.re");
		forward.setRedirect(true);
		return forward;

	}

}
