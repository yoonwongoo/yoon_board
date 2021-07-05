package com.yoon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoon.model.Paging;
import com.yoon.model.PagingDTO;
import com.yoon.model.yoon_boardVO;
import com.yoon.service.yoon_boardService;





@Controller
@RequestMapping("/board/*")
public class yoon_boardController {
	
	@Autowired
	private yoon_boardService boardservice;
	
	private static final Logger log = LoggerFactory.getLogger(yoon_boardController.class);

	@GetMapping("/getcontent")
	public void getcontent(int bno, Model model, Paging paging){
		log.info("게시글 내용페이지 진입.");
		 model.addAttribute("pageInfo", boardservice.getcontent(bno) );
		 model.addAttribute("paging",paging);
		 
	}
	
	@GetMapping("/modify")
	public void modifygetcontent(int bno, Model model,Paging paging) {
		log.info("게시글 수정페이지 진입");
		 model.addAttribute("pageInfo", boardservice.getcontent(bno) );
		 model.addAttribute("paging",paging);
	}
	
	@PostMapping("/modify")
	public String modifycontent(yoon_boardVO yoon_boardvo,RedirectAttributes rttr) {
		log.info("게시글 수정페이지 진입");
		boardservice.modify(yoon_boardvo);
		
		rttr.addFlashAttribute("result","modifysuccess");
		
		return "redirect:/board/list";
	}
	
	
	/* 
	 * 게시글목록을 페이징하지않고 GET하는 메서드..
	@GetMapping("/list")
	public void list(Model model) {
		System.out.println(boardservice.getlist());
		log.info("게시글 목록페이지 진입");
		model.addAttribute("list", boardservice.getlist());
	}
	*/
	@GetMapping("/list")
	public void GetlistPaing(Model model, Paging paging){
		log.info("게시글목록페이징 접속");
		
		model.addAttribute("list",boardservice.getlistPaging(paging));
		
		int total =boardservice.getTotal(paging);
		PagingDTO pagingdto = new PagingDTO(paging, total);
		model.addAttribute("pagingMaker", pagingdto);
	}
	
	
	

	@GetMapping("/enroll")
	public void enroll() {
		log.info("게시글 등록 페이지 진입");
		
	}
	
	@PostMapping("/enroll")
	public String board_enroll(yoon_boardVO yoonboardvo,RedirectAttributes rttr) {
		
		boardservice.enroll(yoonboardvo);
		rttr.addFlashAttribute("result", "enrollCheck");
		return "redirect:/board/list";
	}
	
	
	@PostMapping("/delete")
	public String deletecontent(int bno, RedirectAttributes rttr) {
		boardservice.delete(bno);
		rttr.addFlashAttribute("result", "deletesucess");
		return "redirect:/board/list";
	}
	

}
