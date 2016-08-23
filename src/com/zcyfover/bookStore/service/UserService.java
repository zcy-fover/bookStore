package com.zcyfover.bookStore.service;

import java.util.Set;

import com.zcyfover.bookStore.Dao.BookDao;
import com.zcyfover.bookStore.Dao.TradeDao;
import com.zcyfover.bookStore.Dao.TradeItemDao;
import com.zcyfover.bookStore.Dao.UserDao;
import com.zcyfover.bookStore.Dao.impl.BookDaoImpl;
import com.zcyfover.bookStore.Dao.impl.TradeDaoImpl;
import com.zcyfover.bookStore.Dao.impl.TradeItemDaoImpl;
import com.zcyfover.bookStore.Dao.impl.UserDaoImpl;
import com.zcyfover.bookStore.domain.Trade;
import com.zcyfover.bookStore.domain.TradeItem;
import com.zcyfover.bookStore.domain.User;

public class UserService {

	private UserDao userDao = new UserDaoImpl();
	private TradeDao tradeDao = new TradeDaoImpl();
	private TradeItemDao tradeItemDao = new TradeItemDaoImpl();
	private BookDao bookDao = new BookDaoImpl();
	
	public User getUser(String username, String password){
		return userDao.getUser(username, password);
	}
	
	public User getUserWithUsername(String username, String password){
		
		//	调用 UserDAO 的方法获取 User 对象
		User user = userDao.getUser(username, password);
		if(user == null){
			return null;
		}
		
		//	调用 TradeDAO 的方法获取 Trade 的集合，把其装配为 User 的属性
		int userId = user.getUserId();
		
		//	调用 TradeItemDAO 的方法获取每一个 Trade 中的 TradeItem 的集合，并把其装配为 Trade 的属性
		//	获取 User
		//	遍历 User 的 Trade 集合
		//	遍历 Trade 的 TradeItem 的集合	
		Set<Trade> trades = tradeDao.getTradesWithUserId(userId);
		if(trades != null){
			for(Trade trade : trades){
				int tradeId = trade.getTradeId();
				Set<TradeItem> items = tradeItemDao.getTradeItemsWithTradeId(tradeId);
				
				if(items != null){
					for(TradeItem item :items){
						item.setBook(bookDao.getBook(item.getBookId()));
					}
					trade.setItems(items);
				}
				
			}
		}
		user.setTrades(trades);
		return user;
	}
	
}
