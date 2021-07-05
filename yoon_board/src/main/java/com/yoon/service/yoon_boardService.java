package com.yoon.service;

import java.util.List;

import com.yoon.model.Paging;
import com.yoon.model.yoon_boardVO;

public interface yoon_boardService {
	/*게시글 등록*/
	public void enroll(yoon_boardVO yoon_boardvo);
	
	/*페이징을 하지않은 게시글 목록*/
	//public List<yoon_boardVO> getlist();
	
	/*게시글 목록 페이징*/
	public List<yoon_boardVO> getlistPaging(Paging paging);
	
	/*게시글 내용*/
	public yoon_boardVO getcontent(int bno);
	
	/*게시글 수정*/
	public void modify(yoon_boardVO yoon_boardvo);
	
	/*게시글 삭제*/
	public int delete(int bno);
	
	/*게시글 총개수*/
	public int getTotal(Paging paging);
}
