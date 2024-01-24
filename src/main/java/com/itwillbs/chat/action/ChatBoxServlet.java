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

import com.itwillbs.chat.db.ChatDTO;
import com.itwillbs.chat.db.ChatDAO;
import com.itwillbs.member.db.MemberDAO;


@WebServlet("/ChatBoxServlet")
public class ChatBoxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("ChatBoxServlet exe");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String mem_id = request.getParameter("id");
		if(mem_id == null || mem_id.equals("")) {
			response.getWriter().write("");
		} else {
			try {
				HttpSession session = request.getSession();
				if(!URLDecoder.decode(mem_id, "UTF-8").equals((String) session.getAttribute("id"))) {
					response.getWriter().write("");
					return;
				}
				mem_id = URLDecoder.decode(mem_id, "UTF-8");
				response.getWriter().write(getBox(mem_id));
//				System.out.println("ChatBoxServlet exe");
			} catch (Exception e) {
				response.getWriter().write("");
//				System.out.println("ChatBoxServlet err");
			}
			//특정사용자가 현재까지 읽지않은 메시지갯수를 클라이언트에게 출력
		} 
	}
	
	public String getBox(String mem_id) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getBox(mem_id);
		if(chatList.size() == 0) return "";
		for(int i = chatList.size() - 1; i >= 0; i--) {
			String unread = "";
			String mem_img = "";
			if(mem_id.equals(chatList.get(i).getto_id())) {
				unread = chatDAO.getUnreadChat(chatList.get(i).getfrom_id(), mem_id) + "";
				if(unread.equals("0")) unread = "";
			}
			if(mem_id.equals(chatList.get(i).getto_id())) {
				mem_img = new MemberDAO().getProfile(chatList.get(i).getfrom_id());
			} else {
				mem_img = new MemberDAO().getProfile(chatList.get(i).getto_id());
			}
			ChatDAO daof = new ChatDAO();
			String from_nickname = daof.getNickname(chatList.get(i).getfrom_id());
			result.append("[{\"value\": \"" + chatList.get(i).getfrom_id() + "\"},");
//			result.append("[{\"value\": \"" + from_nickname + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getto_id() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getchat_content() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getchat_time() + "\"},");
			result.append("{\"value\": \"" + unread + "\"},");
			result.append("{\"value\": \"" + mem_img + "\"},");
			result.append("{\"value\": \"" + from_nickname + "\"}]");
			if(i != 0) result.append(","); // 0이아니라면 다음 데이터가 존재
			
			System.out.println(from_nickname);
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getchat_id() + "\"}"); //가장 마지막 chat_id를 가져옴
		return result.toString(); // json 형태 문자열 반환
	}
	
}
