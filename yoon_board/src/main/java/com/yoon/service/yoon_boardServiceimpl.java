package com.yoon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoon.mapper.yoon_boardMapper;
import com.yoon.model.Paging;
import com.yoon.model.yoon_boardVO;
/*게시판 서비스에는 게시글을 등록하는 서비스가 있다.. 그래서 인터페이스로 등록한 매퍼를 의존성주입을하여 매퍼를 추가한다.*/

@Service
public class yoon_boardServiceimpl implements yoon_boardService {
	
	@Autowired
	private  yoon_boardMapper boardmapper;
	
	/*게시글 등록*/
	@Override
	public void enroll(yoon_boardVO yoon_boardvo) {
		
		boardmapper.enroll(yoon_boardvo);
	
		
	}
	/*페이징을 적용하지않은 게시글 목록*/
	/*@Override
	public List<yoon_boardVO> getlist() {
		// TODO Auto-generated method stub
		return boardmapper.getlist();
	}*/

	
	/*게시글 내용*/
	@Override
	public yoon_boardVO getcontent(int bno) {

		return boardmapper.getcontent(bno);
	}
	
	/*게시글 수정*/
	@Override
	public void modify(yoon_boardVO yoon_boardvo) {
		
		boardmapper.modify(yoon_boardvo);
		
	}
	/*게시글 삭제*/
	@Override
	public int delete(int bno) {
		
		return boardmapper.delete(bno);
	}
	/*게시글 목록 페이징*/
	@Override
	public List<yoon_boardVO> getlistPaging(Paging paging) {
	
		return boardmapper.getlistPaging(paging);
	}

	/*게시글의 총개수*/
	@Override
	public int getTotal(Paging paging) {
		
		return boardmapper.getTotal(paging);
	}

}
