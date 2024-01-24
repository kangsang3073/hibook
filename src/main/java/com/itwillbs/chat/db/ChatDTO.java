package com.itwillbs.chat.db;

public class ChatDTO {
	int chat_id;
	String from_id;
	String to_id;
	String chat_content;
	String chat_time;
	int chat_read;
	
	
	public int getchat_read() {
		return chat_read;
	}
	public void setchat_read(int chat_read) {
		this.chat_read = chat_read;
	}
	public int getchat_id() {
		return chat_id;
	}
	public void setchat_id(int chat_id) {
		this.chat_id = chat_id;
	}
	public String getfrom_id() {
		return from_id;
	}
	public void setfrom_id(String from_id) {
		this.from_id = from_id;
	}
	public String getto_id() {
		return to_id;
	}
	public void setto_id(String to_id) {
		this.to_id = to_id;
	}
	public String getchat_content() {
		return chat_content;
	}
	public void setchat_content(String chat_content) {
		this.chat_content = chat_content;
	}
	public String getchat_time() {
		return chat_time;
	}
	public void setchat_time(String chat_time) {
		this.chat_time = chat_time;
	}
	
	
	
}
