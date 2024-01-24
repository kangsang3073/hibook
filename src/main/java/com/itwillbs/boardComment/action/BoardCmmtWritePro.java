package com.itwillbs.boardComment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.itwillbs.boardComment.db.BoardCmmtDAO;
import com.itwillbs.boardComment.db.BoardCmmtDTO;
import com.itwillbs.util.CmmUtil;

public class BoardCmmtWritePro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardWritePro execute()");

		// request 한글처리
		request.setCharacterEncoding("utf-8");

		// request content, secretYn, parentId 가져와서 변수에 저장
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String content = request.getParameter("content");
		String secretYn = request.getParameter("secretYn");
		String boardId = request.getParameter("boardId");

		
		// BoardCmmtDTO 객체생성
		BoardCmmtDTO cmmtDto = new BoardCmmtDTO();

		// set메서드 호출해서 값 저장
		cmmtDto.setContent(content);
		cmmtDto.setSecretYn(secretYn);
		cmmtDto.setBoardId(Integer.parseInt(boardId));
		cmmtDto.setInsertId(id);
		
		// BoardCmmtDAO 객체생성
		BoardCmmtDAO cmmtDao = new BoardCmmtDAO();

		// insertBoardCmmt(cmmtDto) 메서드 호출
		int result = cmmtDao.insertBoardCmmt(cmmtDto); // insert,update : 쿼리성공 =1리턴, 실패=0리턴
		
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
	
		CmmUtil.jsonProcess(response, obj.toJSONString());
		
		return null;
	}
	


}
