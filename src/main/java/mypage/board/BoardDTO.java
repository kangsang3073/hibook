package mypage.board;

import java.sql.Timestamp;

public class BoardDTO {
	// 게시판(board)테이블 
	private int board_id;
	private String board_type;
	private String notice_yn;
	private String title;
	private String content;
	private String content_img1;
	private String content_img2;
	private String content_img3;
	private int view_cnt;
	private int report_cnt;
	private int ref;
	private int re_step;
	private int re_level;
	private String insert_id;
	private Timestamp insert_date;
	
	// set get 메서드 
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getBoard_type() {
		return board_type;
	}
	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}
	public String getNotice_yn() {
		return notice_yn;
	}
	public void setNotice_yn(String notice_yn) {
		this.notice_yn = notice_yn;
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
	public String getContent_img1() {
		return content_img1;
	}
	public void setContent_img1(String content_img1) {
		this.content_img1 = content_img1;
	}
	public String getContent_img2() {
		return content_img2;
	}
	public void setContent_img2(String content_img2) {
		this.content_img2 = content_img2;
	}
	public String getContent_img3() {
		return content_img3;
	}
	public void setContent_img3(String content_img3) {
		this.content_img3 = content_img3;
	}
	public int getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}
	public int getReport_cnt() {
		return report_cnt;
	}
	public void setReport_cnt(int report_cnt) {
		this.report_cnt = report_cnt;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
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
	
}