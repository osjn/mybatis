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
		System.out.println("�õ��û�id=2���û���Ϣ");
		User user = mapper.selectUserByID(2);
		System.out.println(user.getUserAddress());
		
		// �õ������б�����
		System.out.println("�õ��û�idΪ1�����������б�");
		List<Article> articles = mapper.getUserArticles(2);
		
		for (Article article : articles) {
			System.out.println(article.getContent() + "--" + article.getTitle());
		}
	}

}