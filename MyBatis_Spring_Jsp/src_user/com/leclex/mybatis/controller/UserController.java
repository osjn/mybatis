package com.leclex.mybatis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leclex.mybatis.inter.IUserOperation;
import com.leclex.mybatis.model.Article;

@Controller
@RequestMapping("/article")
public class UserController {

	@Autowired
	IUserOperation userMapper;
	
	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response) {
		List<Article> articles = userMapper.getUserArticles(3);
		ModelAndView mav = new ModelAndView("list");
		mav.addObject("articles", articles);
		return mav;
	}
}
