package com.itwillbs.review.db;

import java.sql.Timestamp;

public class StarReviewDTO extends MemberDTO{
	private int manner_num;
	private String tgt_id;
	private double score;
	private String insert_id;
	private String review_content;
	private Timestamp review_date;
	private int count;
//	private String nickname;
//	private String mem_img;
	
	public int getManner_num() {
		return manner_num;
	}
	public void setManner_num(int manner_num) {
		this.manner_num = manner_num;
	}
	public String getTgt_id() {
		return tgt_id;
	}
	public void setTgt_id(String tgt_id) {
		this.tgt_id = tgt_id;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public Timestamp getReview_date() {
		return review_date;
	}
	public void setReview_date(Timestamp review_date) {
		this.review_date = review_date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
//	public String getNickname() {
//		return nickname;
//	}
//	public void setNickname(String nickname) {
//		this.nickname = nickname;
//	}
//	public String getMem_img() {
//		return mem_img;
//	}
//	public void setMem_img(String mem_img) {
//		this.mem_img = mem_img;
//	}
}
