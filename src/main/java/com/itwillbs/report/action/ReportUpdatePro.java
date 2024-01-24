package com.itwillbs.report.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.report.db.ReportDAO;
import com.itwillbs.report.db.ReportDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReportUpdatePro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReportUpdatePro execute()");

		// 객체생성
		// 1. request
		// 2. 웹서버 업로드 폴더 위치 -> webapp - upload 폴더 만들기
		String uploadPath = request.getRealPath("/upload");
		// 3. 파일크기
		int maxSize = 100 * 1024 * 1024;
		// 4. 한글처리
		// 5. 업로드 파일이름 동일할 때 -> 파일이름변경(DefaultFileRenamePolicy())

		MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8",
								new DefaultFileRenamePolicy());

		// 한글
		request.setCharacterEncoding("utf-8");
		// request => 파라미터 => 변수저장
		int num = Integer.parseInt(multi.getParameter("num"));
		String id = multi.getParameter("insert_id");
		String title = multi.getParameter("title");
		String contnet = multi.getParameter("content");
		String contnet_img1 = multi.getFilesystemName("content_img1");

		if(contnet_img1 == null) {
			contnet_img1 = multi.getParameter("oldfile1");
		}
		
		
		ReportDTO updateDto = new ReportDTO();

		updateDto.setReport_id(num);
		updateDto.setInsert_id(id);
		updateDto.setTitle(title);
		updateDto.setContent(contnet);
		updateDto.setContent_img1(contnet_img1);

		ReportDAO dao = new ReportDAO();
		dao.updatereport(updateDto);

		ActionForward forward = new ActionForward();
		forward.setPath("ReportList.re");
		forward.setRedirect(true);
		return forward;

	}

}
