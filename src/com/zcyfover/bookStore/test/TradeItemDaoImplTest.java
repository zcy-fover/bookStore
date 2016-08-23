package com.zcyfover.bookStore.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.junit.Test;

import com.zcyfover.bookStore.Dao.TradeItemDao;
import com.zcyfover.bookStore.Dao.impl.TradeItemDaoImpl;
import com.zcyfover.bookStore.domain.TradeItem;

public class TradeItemDaoImplTest {

	private TradeItemDao tradeItemDao = new TradeItemDaoImpl();

	@Test
	public void testBatchSave() {

		Collection<TradeItem> items = new ArrayList<>();
		
		items.add(new TradeItem(null, 1, 10, 12));
		items.add(new TradeItem(null, 2, 20, 12));
		items.add(new TradeItem(null, 3, 30, 12));
		items.add(new TradeItem(null, 4, 40, 12));
		items.add(new TradeItem(null, 5, 50, 12));
		
		tradeItemDao.batchSave(items);
	}

	@Test
	public void testGetTradeItemsWithTradeId() {
		Set<TradeItem> item = tradeItemDao.getTradeItemsWithTradeId(13);
		System.out.println(item);
	}

}
