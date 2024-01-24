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

@WebServlet("/ChatSubmitServlet")
public class ChatSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String from_id = request.getParameter("from_id");
		String to_id = request.getParameter("to_id");
		String chat_content = request.getParameter("chat_content");
		if(from_id == null || from_id.equals("") || to_id == null || to_id.equals("")
				|| chat_content == null || chat_content.equals("")) {
			response.getWriter().write("0");
		} else if(from_id.equals(to_id)) {
			response.getWriter().write("-1");
		} else {
			from_id = URLDecoder.decode(from_id, "UTF-8");
			to_id = URLDecoder.decode(to_id, "UTF-8");
			HttpSession session = request.getSession();
			if(!URLDecoder.decode(from_id, "UTF-8").equals((String) session.getAttribute("id"))) {
				response.getWriter().write("");
				return;
			}
			chat_content = URLDecoder.decode(chat_content, "UTF-8");
			response.getWriter().write(new ChatDAO().submit(from_id, to_id, chat_content) + "");
		}
	}

}
