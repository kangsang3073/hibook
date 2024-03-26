package com.itwillbs.util;

import java.sql.Timestamp;

public class ComCdDTO {
	// 데이터를 담아서 다른 클래스 전달 
	//멤버변수 => 데이터 은닉 => 캡슐화
	private String cdGrp;
	private String cdGrpNm;
	private String cd;
	private String cdNm;
	private String[] cdGrpnms = {"거래종류", "도서종류", "제품상태", "거래상태", "거래방식"};
	
	// 시간을 시분초→연월일 로 바꿔주기 위한 변수
	private String date; 
	
	
	public String[] getCdGrpnms() {
		return cdGrpnms;
	}
	public void setCdGrpnms(String[] cdGrpnms) {
		this.cdGrpnms = cdGrpnms;
	}
	public String getCdGrp() {
		return cdGrp;
	}
	public void setCdGrp(String cdGrp) {
		this.cdGrp = cdGrp;
	}
	public String getCdGrpNm() {
		return cdGrpNm;
	}
	public void setCdGrpNm(String cdGrpNm) {
		this.cdGrpNm = cdGrpNm;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getCdNm() {
		return cdNm;
	}
	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	

	
	
}
