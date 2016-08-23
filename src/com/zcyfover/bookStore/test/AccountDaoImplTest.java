package com.zcyfover.bookStore.test;

import org.junit.Test;

import com.zcyfover.bookStore.Dao.AccountDao;
import com.zcyfover.bookStore.Dao.impl.AccountDaoImpl;
import com.zcyfover.bookStore.domain.Account;

public class AccountDaoImplTest {

	private AccountDao accountDao = new AccountDaoImpl();
	
	@Test
	public void testGet() {
		Account account = accountDao.get(1);
		System.out.println(account.getBalance());
	}

	@Test
	public void testUpdateBalance(){
		accountDao.updateBalance(1, 40);
	}
	
}
