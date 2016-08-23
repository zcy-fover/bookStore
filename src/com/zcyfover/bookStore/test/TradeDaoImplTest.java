package com.zcyfover.bookStore.test;

import java.sql.Date;
import java.util.Set;

import org.junit.Test;

import com.zcyfover.bookStore.Dao.TradeDao;
import com.zcyfover.bookStore.Dao.impl.TradeDaoImpl;
import com.zcyfover.bookStore.domain.Trade;

public class TradeDaoImplTest {

	private TradeDao tradeDao = new TradeDaoImpl();
	
	@Test
	public void testInsertTrade() {
		Trade trade = new Trade();
		trade.setUserId(3);
		trade.setTradeTime(new Date(new java.util.Date().getTime()));
		
		tradeDao.insert(trade);
	}

	@Test
	public void testGetTradesWithUserId() {
		Set<Trade> trades = tradeDao.getTradesWithUserId(1);
		System.out.println(trades);
	}

}
