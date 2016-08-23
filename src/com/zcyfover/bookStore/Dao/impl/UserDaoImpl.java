package com.zcyfover.bookStore.Dao.impl;

import com.zcyfover.bookStore.Dao.UserDao;
import com.zcyfover.bookStore.domain.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User getUser(String username, String password) {

		String sql = "SELECT userId, username, accountId, password FROM userinfo WHERE username = ?";
		return query(sql, username);
	}

}
