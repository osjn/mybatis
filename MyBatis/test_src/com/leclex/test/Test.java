package com.leclex.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.leclex.mybatis.inter.IUserOperation;
import com.leclex.mybatis.model.Article;
import com.leclex.mybatis.model.User;

public class Test {

	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try {
			reader = Resources.getResourceAsReader("config/Configuration.xml");
			setSqlSessionFactory(new SqlSessionFactoryBuilder().build(reader));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getUserList(String userName) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			// User user =
			// (User)session.selectOne("com.leclex.mybatis.models.UserMapper.selectUserByID",
			// 1);
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			// User user = userOperation.selectUserByID(1);
			// System.out.println(user.getUserAddress());
			// System.out.println(user.getUserName());
			List<User> users = userOperation.selectUsers(userName);
			for (User user : users) {
				System.out.println(user.getId() + ":" + user.getUserName() + ":" + user.getUserAddress());
			}
		} finally {
			session.close();
		}
	}

	/**
	 * ��������,���Ӻ󣬱����ύ���񣬷��򲻻�д�뵽���ݿ�.
	 */
	public void addUser() {
		User user = new User();
		user.setUserAddress("����㳡");
		user.setUserName("����");
		user.setUserAge(80);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			userOperation.addUser(user);
			session.commit();
			System.out.println("��ǰ���ӵ��û�idΪ��" + user.getId());
		} finally {
			session.close();
		}
	}

	public void updateUser() {
		// �ȵõ��û���Ȼ���޸ģ��ύ
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			User user = userOperation.selectUserByID(1);
			user.setUserAddress("ԭ����ħ�����ֶ�����԰��");
			userOperation.updateUser(user);
			session.commit();
		} finally {
			session.close();
		}
	}

	public void deleteUser(int id) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			userOperation.deleteUser(id);
			session.commit();
		} finally {
			session.close();
		}
	}

	public void getUserArticles(int userid) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			List<Article> articles = userOperation.getUserArticles(userid);
			for (Article article : articles) {
				System.out.println(article.getTitle() + ":" + article.getContent() + ":������:"
						+ article.getUser().getUserName() + ":��ַ:" + article.getUser().getUserAddress());
			}
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) {
		Test testUser = new Test();
		// testUser.getUserList("%");
		// testUser.addUser();
		// testUser.updateUser();
		// testUser.deleteUser(1);
		testUser.getUserArticles(2);
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public static void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		Test.sqlSessionFactory = sqlSessionFactory;
	}

}