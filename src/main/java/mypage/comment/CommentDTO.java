package mypage.comment;

import java.sql.Timestamp;

public class CommentDTO {
	// 댓글(board_cmmt)테이블 
	private int cmmt_id;
	private int board_id;
	private String content;
	private int parent_id;
	private String secret_yn;
	private String insert_id;
	private Timestamp insert_date;
	private String title;
	private String board_type;
	
	// set get 메서드 
	public int getCmmt_id() {
		return cmmt_id;
	}
	public void setCmmt_id(int cmmt_id) {
		this.cmmt_id = cmmt_id;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getSecret_yn() {
		return secret_yn;
	}
	public void setSecret_yn(String secret_yn) {
		this.secret_yn = secret_yn;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	public Timestamp getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(Timestamp insert_date) {
		this.insert_date = insert_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBoard_type() {
		return board_type;
	}
	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}
	
	
}