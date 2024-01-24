package com.itwillbs.boardComment.db;

import java.sql.Timestamp;

public class BoardCmmtDTO {

	
	private int cmmtId;
	private int boardId;
	private String content;
	private int parentId;
	private String secretYn;
	private String insertId;
	private Timestamp insertDate;
	
	
	public int getCmmtId() {
		return cmmtId;
	}
	public void setCmmtId(int cmmtId) {
		this.cmmtId = cmmtId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getSecretYn() {
		return secretYn;
	}
	public void setSecretYn(String secretYn) {
		this.secretYn = secretYn;
	}
	public String getInsertId() {
		return insertId;
	}
	public void setInsertId(String insertId) {
		this.insertId = insertId;
	}
	public Timestamp getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}
	
	
}
