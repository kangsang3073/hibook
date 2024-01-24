package mypage.report;

import java.sql.Timestamp;

public class ReportDTO {
	// 신고(mem_report)테이블 
	private int report_id;
	private String mem_id;
	private String title;
	private String content;
	private String content_img1;
	private String content_img2;
	private String content_img3;
	private String insert_id;
	private Timestamp insert_date;
	
	// set get 메서드 
	public int getReport_id() {
		return report_id;
	}
	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
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