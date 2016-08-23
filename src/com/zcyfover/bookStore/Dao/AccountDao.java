package com.zcyfover.bookStore.Dao;

import com.zcyfover.bookStore.domain.Account;

public interface AccountDao {

	/**
	 * 根据accountId获取对应的Account对象
	 * @param accountId
	 * @return
	 */
	public abstract Account get(Integer accountId);
	
	/**
	 * 根据传入的accountId，amount更新指定账户的余额：扣除amount指定的钱数
	 * @param accountId
	 * @param amount
	 */
	public abstract void updateBalance(Integer accountId, float amount);
	
}
