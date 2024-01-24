package com.itwillbs.chat.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.chat.db.ChatDAO;
import com.itwillbs.chat.db.ChatDTO;

@WebServlet("/ChatListServlet")
public class ChatListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String from_id = request.getParameter("from_id");
		String to_id = request.getParameter("to_id");
		String listType = request.getParameter("listType");
		
		if(from_id == null || from_id.equals("") || to_id == null || to_id.equals("")
				|| listType == null || listType.equals("")) 
			response.getWriter().write("");
//		else if (listType.equals("ten")) response.getWriter().write(getTen(URLDecoder.decode(from_id,"UTF-8"), URLDecoder.decode(to_id,"UTF-8")));
		else {
			try {
				HttpSession session = request.getSession();
				if(!URLDecoder.decode(from_id,"UTF-8").equals((String) session.getAttribute("id"))) {
					response.getWriter().write("");
					return;
				}
				response.getWriter().write(getID(URLDecoder.decode(from_id, "UTF-8"), URLDecoder.decode(to_id,"UTF-8"), listType));
			} catch (Exception e) {
				response.getWriter().write(""); // 오류 발생 시 공백문자 출력
			}
		}
	}

	
	public String getID(String from_id, String to_id, String chat_id) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByID(from_id, to_id, chat_id);
		if(chatList.size() == 0) return "";
		for(int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getfrom_id() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getto_id() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getchat_content() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getchat_time() + "\"}]");
			if(i != chatList.size() -1) result.append(","); //만약 마지막 원소가 아니라면
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getchat_id() + "\"}"); //가장 마지막 chat_id를 가져옴
		chatDAO.readChat(from_id, to_id); //읽음 처리
		return result.toString(); // json 형태 문자열 반환
	}
}
