package com.itwillbs.report.db;

import java.sql.Timestamp;

public class ReportDTO {
	private int report_id;
	private String title;
	private String content;
	private String content_img1;
	private String insert_id;
	private Timestamp insert_date;
	
	
	public int getReport_id() {
		return report_id;
	}
	public void setReport_id(int report_id) {
		this.report_id = report_id;
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
	public String getInsert_id() {
		return insert_id;
	}
	public void setInsert_id(String inset_id) {
		this.insert_id = inset_id;
	}
	public Timestamp getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(Timestamp insert_date) {
		this.insert_date = insert_date;
	}
	
	

	
	
}
