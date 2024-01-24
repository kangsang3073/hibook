package mypage.market;

import java.sql.Timestamp;

public class MarketDTO {
	// 중고거래(market)테이블 
	private int market_id;
	private String trade_type;
	private String title;
	private String content;
	private int view_cnt;
	private int report_cnt;
	private String review;
	private String book_type;
	private String trade_inperson;
	private String book_st;
	private String trade_st;
	private String book_price;
	private String insert_id;
	private Timestamp insert_date;
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	// set get 메서드 
	public int getMarket_id() {
		return market_id;
	}
	public void setMarket_id(int market_id) {
		this.market_id = market_id;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
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
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getBook_type() {
		return book_type;
	}
	public void setBook_type(String book_type) {
		this.book_type = book_type;
	}
	public String getTrade_inperson() {
		return trade_inperson;
	}
	public void setTrade_inperson(String trade_inperson) {
		this.trade_inperson = trade_inperson;
	}
	public String getBook_st() {
		return book_st;
	}
	public void setBook_st(String book_st) {
		this.book_st = book_st;
	}
	public String getTrade_st() {
		return trade_st;
	}
	public void setTrade_st(String trade_st) {
		this.trade_st = trade_st;
	}
	public String getBook_price() {
		return book_price;
	}
	public void setBook_price(String book_price) {
		this.book_price = book_price;
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