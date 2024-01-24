package com.itwillbs.boardComment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.itwillbs.boardComment.db.BoardCmmtDAO;
import com.itwillbs.boardComment.db.BoardCmmtDTO;
import com.itwillbs.util.CmmUtil;

public class BoardCmmtDeletePro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// request 한글처리
		request.setCharacterEncoding("utf-8");

		// request변수에 저장
		int cmmtId = Integer.parseInt(request.getParameter("cmmtId"));
		
		// BoardCmmtDTO 객체생성
		BoardCmmtDTO cmmtDto = new BoardCmmtDTO();
		
		// BoardCmmtDAO 객체생성 -> 기억장소 할당
		BoardCmmtDAO cmmtDao = new BoardCmmtDAO();

		ActionForward forward = null;
		
		// deleteBoardCmmt(cmmtId) 메서드 호출
		// insert,update,delete : 쿼리성공 =1리턴, 실패=0리턴
		int result = cmmtDao.deleteBoardCmmt(cmmtId); 
		JSONObject obj = new JSONObject();
		obj.put("result", result);

		CmmUtil.jsonProcess(response, obj.toJSONString());

		return null;
	}

} // 클래스
