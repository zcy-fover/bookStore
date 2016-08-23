package com.zcyfover.bookStore.Dao.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import com.zcyfover.bookStore.Dao.TradeDao;
import com.zcyfover.bookStore.domain.Trade;

public class TradeDaoImpl extends BaseDaoImpl<Trade> implements TradeDao {

	@Override
	public void insert(Trade trade) {

		String sql = "INSERT INTO trade (userid, tradetime) VALUES (?, ?)";
		long tradeId = insert(sql, trade.getUserId(), trade.getTradeTime());
		trade.setTradeId((int)tradeId);
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Set<Trade> getTradesWithUserId(Integer userId) {

		String sql = "SELECT tradeId, userId, tradeTime FROM trade "+
					 "WHERE userId = ? ORDER BY tradeTime DESC";
		return new LinkedHashSet(queryForList(sql, userId));
	}

}
