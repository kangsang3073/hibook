package com.itwillbs.market.db;

import java.sql.Timestamp;

public class MarketDTO {
/* DB 저장 변수 */
	//회원 정보
	private int market_id;
	private String insert_id;
	private String nickname;
	//게시글 기본 정보
	private String title;
	private String content;
	private Timestamp insert_date;
	private int view_cnt;
	private int report_cnt;
	private String review;
	//책거래 정보
	private String book_price;
	private String book_st;
	private String book_type;
	//거래 정보
	private String trade_type;
	private String trade_st;
	private String trade_inperson;
	private String trade_place;
	//이미지 url 정보
	private String[] imgUrls; // 판매게시판 이미지 5개 배열 저장
	private String url; // 판매목록 대표이미지 1개 저장


	/* DB 저장 필요없이 이미지 url 계산에 사용되는 변수 */
	private static int imgLengthMax = 5; // 게시글의 최대 이미지 수를 제한, 해당 static 변수 값은 수정 불가능하게 get함수만 사용
	private String[] oldImgUrls;
	private int oldImgNum;
	private int preImgNum;
	
	//set, get 함수
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getMarket_id() {
		return market_id;
	}
	public void setMarket_id(int market_id) {
		this.market_id = market_id;
	}
	public String getInsert_id() {
		return insert_id;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}
	public Timestamp getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(Timestamp insert_date) {
		this.insert_date = insert_date;
	}
	public int getReport_cnt() {
		return report_cnt;
	}
	public void setReport_cnt(int report_cnt) {
		this.report_cnt = report_cnt;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getBook_price() {
		return book_price;
	}
	public void setBook_price(String book_price) {
		this.book_price = book_price;
	}
	public String getTrade_place() {
		return trade_place;
	}
	public void setTrade_place(String trade_place) {
		this.trade_place = trade_place;
	}
	public int getOldImgNum() {
		return oldImgNum;
	}
	public void setOldImgNum(int oldImgNum) {
		this.oldImgNum = oldImgNum;
	}
	public int getPreImgNum() {
		return preImgNum;
	}
	public void setPreImgNum(int preImgNum) {
		this.preImgNum = preImgNum;
	}
	public static int getImgLengthMax() {
		return imgLengthMax;
	}
	public String[] getOldImgUrls() {
		if(oldImgUrls!=null){
			String[] tempoldImgUrls= new String[oldImgUrls.length];
			System.arraycopy(oldImgUrls, 0, tempoldImgUrls, 0, oldImgUrls.length);
			return tempoldImgUrls;
		}else{
			return null;
		}
	}
	public void setOldImgUrls(String[] oldImgUrls) {
		if(oldImgUrls!=null){
			this.oldImgUrls = new String[oldImgUrls.length];
			System.arraycopy(oldImgUrls, 0, this.oldImgUrls, 0, oldImgUrls.length);
		}else{
			this.oldImgUrls = null;
		}
	}
	public String[] getImgUrls() {
		if(imgUrls!=null){
			String[] tempimgUrls= new String[imgUrls.length];
			System.arraycopy(imgUrls, 0, tempimgUrls, 0, imgUrls.length);
			return tempimgUrls;
		}else{
			return null;
		}
	}
	public void setImgUrls(String[] imgUrls) {
		if(imgUrls!=null){
			this.imgUrls = new String[imgUrls.length];
			System.arraycopy(imgUrls, 0, this.imgUrls, 0, imgUrls.length);
		}else{
			this.imgUrls = null;
		}
	}
	public String getBook_st() {
		return book_st;
	}
	public void setBook_st(String book_st) {
		this.book_st = book_st;
	}
	public String getBook_type() {
		return book_type;
	}
	public void setBook_type(String book_type) {
		this.book_type = book_type;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getTrade_st() {
		return trade_st;
	}
	public void setTrade_st(String trade_st) {
		this.trade_st = trade_st;
	}
	public String getTrade_inperson() {
		return trade_inperson;
	}
	public void setTrade_inperson(String trade_inperson) {
		this.trade_inperson = trade_inperson;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


}
