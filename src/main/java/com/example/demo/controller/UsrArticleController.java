package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
// @Autowired > 해당 변수 및 메서드에 스프링이 관리하는 Bean을 자동으로 매핑해주는 개념 	
//	@Autowired > 필드주입                                           	    @Autowired >생성자 주입
//	private ArticleService articleService;                                  public UsrArticleController(ArticleService articleService) {
//                                                                              this.articleService = articleService;  }
	@Autowired
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	// 액션 메서드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title, String body) {
		
		if(Util.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		if(Util.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}
		
		articleService.writeArticle(title, body);
		
		int id = articleService.getLastInsertId();
		
		return ResultData.from("S-1", Util.f("%d번 게시글이 생성되었습니다.", id), articleService.getArticleById(id));
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articleService.getArticles();
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {
		
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			return ResultData.from("F-1", Util.f("%d번 게시글은 존재하지 않습니다", id)) ;
		}
		
		return ResultData.from("S-1", Util.f("%d번 게시글 입니다", id), foundArticle);
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			return id + "번 게시글은 존재하지 않습니다";
		}
		
		articleService.modifyArticle(id, title, body);
		
		return id + "번 게시글을 수정했습니다";
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			return id + "번 게시글은 존재하지 않습니다";
		}
		
		articleService.deleteArticle(id);
		
		return id + "번 게시글을 삭제했습니다";
	}
}