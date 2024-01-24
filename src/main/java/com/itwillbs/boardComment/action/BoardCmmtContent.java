package com.itwillbs.boardComment.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.itwillbs.boardComment.db.BoardCmmtDAO;
import com.itwillbs.boardComment.db.BoardCmmtDTO;
import com.itwillbs.util.CmmUtil;

public class BoardCmmtContent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardContent execute()");

		// 세션에서 "id"값을 가져오기 -> 변수저장( 다운캐스팅 이루어짐.)
		HttpSession session = request.getSession(); // - 세션 객체생성
		String cmmtId = request.getParameter("cmmtId");

		// BoardCmmtDAO 객체생성 -> 기억장소 할당
		BoardCmmtDAO cmmtDao = new BoardCmmtDAO();

		// BoardDTO dto = dao.getMember(id)메서드 호출
		BoardCmmtDTO dto = cmmtDao.getBoardCmmt(Integer.parseInt(cmmtId));
		JSONObject obj = new JSONObject();
		obj.put("content", dto.getContent());
		obj.put("secretYn", dto.getSecretYn());
		
		CmmUtil.jsonProcess(response, obj.toJSONString());

		return null;
	}

}
