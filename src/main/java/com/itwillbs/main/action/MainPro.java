package com.itwillbs.main.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class MainPro {

	public ArrayList<BoardDTO> getMainBoardList(HttpServletRequest request, HttpServletResponse response,String boardType) {
		// 원래 하던 것 처럼 데이터를 DB에서 조회한 후 페이지 이동을 위한 forward로 리턴을 하게 되면, 아래↓ excute함수만 실행해서 jsp로 가기때문에 
		// 여기서는 조회한 데이터를 리턴해주고, forward를 이용한 페이지 이동은 MainfrontController에서 세팅을 함.
		System.out.println("MainBoardPro.getMainBoardList");
		
		
		// BoardDAO에 있는 쿼리함수 사용하기 위해 생성 
		BoardDAO dao = new BoardDAO();
		
		//dao 함수호출 - ArrayList<BoardDTO>에 정보 담기.
		ArrayList<BoardDTO> list = dao.getMainBoardList(boardType);
		
		return list; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
