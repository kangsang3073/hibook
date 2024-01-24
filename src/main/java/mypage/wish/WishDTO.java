package mypage.wish;

public class WishDTO {
	// 찜(attention)테이블 
	private int market_id;
	private String insert_id;
	
	// set get 메서드 
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
}