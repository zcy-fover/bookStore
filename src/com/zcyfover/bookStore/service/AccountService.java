package com.zcyfover.bookStore.service;

import com.zcyfover.bookStore.Dao.AccountDao;
import com.zcyfover.bookStore.Dao.impl.AccountDaoImpl;
import com.zcyfover.bookStore.domain.Account;

public class AccountService {

	private AccountDao accountDao = new AccountDaoImpl();
	
	public Account getAccount(int accountId){
		return accountDao.get(accountId);
	}
	
}
