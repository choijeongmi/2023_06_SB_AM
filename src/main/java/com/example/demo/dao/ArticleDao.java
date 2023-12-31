package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.example.demo.vo.Article;

@Mapper
public interface ArticleDao {

	public void writeArticle(String title, String body);
	

	public Article getArticleById(int id);


	public void modifyArticle(int id, String title, String body);
	

	public void deleteArticle(int id);
	

	public List<Article> getArticles();


	public int getArticleById();
	
	public int getLastInsertId();
}