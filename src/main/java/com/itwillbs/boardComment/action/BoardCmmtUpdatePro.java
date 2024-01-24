package com.itwillbs.boardComment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.itwillbs.boardComment.db.BoardCmmtDAO;
import com.itwillbs.boardComment.db.BoardCmmtDTO;
import com.itwillbs.util.CmmUtil;

public class BoardCmmtUpdatePro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// request 한글처리
		request.setCharacterEncoding("utf-8");

		// request변수에 저장
		int cmmtId = Integer.parseInt(request.getParameter("cmmtId"));
		String content = request.getParameter("content");
		String secretYn = request.getParameter("secretYn");

		// BoardCmmtDTO 객체생성
		BoardCmmtDTO cmmtDto = new BoardCmmtDTO();

		// set메서드 호출해서 값 저장
		cmmtDto.setCmmtId(cmmtId);
		cmmtDto.setContent(content);
		cmmtDto.setSecretYn(secretYn);
		
		// BoardCmmtDAO 객체생성
		BoardCmmtDAO cmmtDao = new BoardCmmtDAO();

		// updateBoardCmmt(cmmtDto) 메서드 호출
		int result = cmmtDao.updateBoardCmmt(cmmtDto); // insert,update : 쿼리성공 =1리턴, 실패=0리턴
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);

		CmmUtil.jsonProcess(response, obj.toJSONString());

		return null;
	}

}
