package com.yoon.model;

public class Paging {
	//현재 페이지
	private int pageNum;
	//페이지에 보여줘야할 게시글 갯수
	private int amount;
	//검색 키워드
	private String keyword;
	
	public Paging() {
		this(1, 10);
	}
	public Paging(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "Paging [pageNum=" + pageNum + ", amount=" + amount + "]";
	}
	
	
}
