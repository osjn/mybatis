package com.leclex.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.leclex.mybatis.inter.IUserOperation;
import com.leclex.mybatis.model.Article;
import com.leclex.mybatis.model.User;

public class MybatisSpringTest {

	private static ApplicationContext context;
	
	static {
		context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
	}
	
	public static void main(String[] args) {
		IUserOperation mapper = (IUserOperation)context.getBean("userMapper");
		System.out.println("得到用户id=2的用户信息");
		User user = mapper.selectUserByID(2);
		System.out.println(user.getUserAddress());
		
		// 得到文章列表测试
		System.out.println("得到用户id为1的所有文章列表");
		List<Article> articles = mapper.getUserArticles(2);
		
		for (Article article : articles) {
			System.out.println(article.getContent() + "--" + article.getTitle());
		}
	}

}
