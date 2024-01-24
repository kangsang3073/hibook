package com.itwillbs.chat.action;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.chat.db.ChatDAO;


@WebServlet("/ChatUnreadServlet")
public class ChatUnreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String mem_id = request.getParameter("id");
		if(mem_id == null || mem_id.equals("")) {
			response.getWriter().write("0");
//			System.out.println("ChatUnreadServlet.err");
		} else {
			mem_id = URLDecoder.decode(mem_id, "UTF-8");
			HttpSession session = request.getSession();
//			System.out.println("ChatUnreadServlet.else");
			if(!URLDecoder.decode(mem_id, "UTF-8").equals((String) session.getAttribute("id"))) {
//				System.out.println("ChatUnreadServlet.if");
				response.getWriter().write("");
				return;
			}
			response.getWriter().write(new ChatDAO().getAllUnreadChat(mem_id)+""); //특정사용자가 현재까지 읽지않은 메시지갯수를 클라이언트에게 출력
		}
	}

}
