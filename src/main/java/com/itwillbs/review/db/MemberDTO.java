package com.itwillbs.review.db;

import java.sql.Timestamp;

public class MemberDTO {
	// 데이터를 담아서 다른 클래스 전달 
	//멤버변수 => 데이터 은닉 => 캡슐화
	private String memId;//
	private String memPass;//
	private String memNm;//
	private String nickname;//
	private String birth;//
	private String zipcode;//
	private String addr;//
	private String addrDtl;//
	private String email;//
	private String memImg;//
	private String memSt;//
	private String memType;
	private String phone;
	private String adminYn;//
	private String snsType;//
	private String snsId;//
	private Timestamp joinDate;//
	private Timestamp deleteDate;//
	 
	// set get 메서드
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemNm() {
		return memNm;
	}
	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAddrDtl() {
		return addrDtl;
	}
	public void setAddrDtl(String addrDtl) {
		this.addrDtl = addrDtl;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMemImg() {
		return memImg;
	}
	public void setMemImg(String memImg) {
		this.memImg = memImg;
	}
	public String getMemSt() {
		return memSt;
	}
	public void setMemSt(String memSt) {
		this.memSt = memSt;
	}
	public String getAdminYn() {
		return adminYn;
	}
	public void setAdminYn(String adminYn) {
		this.adminYn = adminYn;
	}
	public String getSnsType() {
		return snsType;
	}
	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}
	public String getSnsId() {
		return snsId;
	}
	public void setSnsId(String snsId) {
		this.snsId = snsId;
	}
	public Timestamp getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}
	public Timestamp getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Timestamp deleteDate) {
		this.deleteDate = deleteDate;
	}
	public String getMemType() {
		return memType;
	}
	public void setMemType(String memType) {
		this.memType = memType;
	}
	
}