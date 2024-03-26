package com.itwillbs.board.db;

import java.sql.Timestamp;

public class BoardDTO {
	// 패키지 board BoardDTO
	// 멤버변수 : num name subject content readcount date
	// set get 만들기
	private int boardId;
	private String boardType;
	private String noticeyn;
	private String title;
	private String content;
	private int parentId;
	private String contentImg1;
	private String contentImg2;
	private String contentImg3;
	private int viewCnt;
	private int ref;
	private String insertId;
	private Timestamp insertDate;
	// 검색용 : db X
	private String keyword;
	
	// 닉네임 추가
	private String nickname;
	// private String insertDate;
	// private Date insertDate;

	
	// 게시판목록에 댓글개수 나오게
	private int cmmtCount;
	
	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	public String getNoticeyn() {
		return noticeyn;
	}

	public void setNoticeyn(String noticeyn) {
		this.noticeyn = noticeyn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentImg1() {
		return contentImg1;
	}

	public void setContentImg1(String contentImg1) {
		this.contentImg1 = contentImg1;
	}

	public String getContentImg2() {
		return contentImg2;
	}

	public void setContentImg2(String contentImg2) {
		this.contentImg2 = contentImg2;
	}

	public String getContentImg3() {
		return contentImg3;
	}

	public void setContentImg3(String contentImg3) {
		this.contentImg3 = contentImg3;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
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

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getCmmtCount() {
		return cmmtCount;
	}

	public void setCmmtCount(int cmmtCount) {
		this.cmmtCount = cmmtCount;
	}
	
}