package com.zcyfover.bookStore.Dao;

import java.util.Set;

import com.zcyfover.bookStore.domain.Trade;

public interface TradeDao {

	/**
	 * 向数据表中插入trade对象
	 * @param trade
	 */
	public abstract void insert(Trade trade);
	
	/**
	 * 根据userId获取何其关联的trade对象集
	 * @param userId
	 * @return
	 */
	public abstract Set<Trade> getTradesWithUserId(Integer userId);
	
}
