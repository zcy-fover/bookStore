package com.zcyfover.bookStore.test;

import java.sql.Connection;

import org.junit.Test;

import com.zcyfover.bookStore.Dao.UserDao;
import com.zcyfover.bookStore.Dao.impl.UserDaoImpl;
import com.zcyfover.bookStore.domain.User;
import com.zcyfover.bookStore.utils.JDBCUtils;
import com.zcyfover.bookStore.web.ConnectionContext;

public class UserDaoImplTest {

	UserDao userDao = new UserDaoImpl();
	
	@Test
	public void testGetUser() {
		
		Connection connection = JDBCUtils.getConnection();
		ConnectionContext.getInstance().bind(connection);
		
		User user = userDao.getUser("Tom", "123");
		System.out.println(user);
		
	}

}
