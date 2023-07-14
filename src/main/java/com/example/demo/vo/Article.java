package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
@AllArgsConstructor > 여기에 필드에 쓴 모든생성자만 만들어줌
@NoArgsConstructor > 기본 생성자를 만들어줌
@Data > getter, setter 만들어줌
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {		 
		
		private int id;
		private String title;
		private String body;
		
}
