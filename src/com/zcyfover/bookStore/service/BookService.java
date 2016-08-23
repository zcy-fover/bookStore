package com.zcyfover.bookStore.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import com.zcyfover.bookStore.Dao.AccountDao;
import com.zcyfover.bookStore.Dao.BookDao;
import com.zcyfover.bookStore.Dao.TradeDao;
import com.zcyfover.bookStore.Dao.TradeItemDao;
import com.zcyfover.bookStore.Dao.UserDao;
import com.zcyfover.bookStore.Dao.impl.AccountDaoImpl;
import com.zcyfover.bookStore.Dao.impl.BookDaoImpl;
import com.zcyfover.bookStore.Dao.impl.TradeDaoImpl;
import com.zcyfover.bookStore.Dao.impl.TradeItemDaoImpl;
import com.zcyfover.bookStore.Dao.impl.UserDaoImpl;
import com.zcyfover.bookStore.domain.Book;
import com.zcyfover.bookStore.domain.ShoppingCart;
import com.zcyfover.bookStore.domain.ShoppingCartItem;
import com.zcyfover.bookStore.domain.Trade;
import com.zcyfover.bookStore.domain.TradeItem;
import com.zcyfover.bookStore.web.CriteriaBook;
import com.zcyfover.bookStore.web.Page;

public class BookService {

	private BookDao bookDaoImpl = new BookDaoImpl();
	private AccountDao accountDao = new AccountDaoImpl();
	private TradeDao tradeDao = new TradeDaoImpl();
	private TradeItemDao tradeItemDao = new TradeItemDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	
	public Page<Book> getPage(CriteriaBook cb){
		return bookDaoImpl.getPage(cb);
	}
	
	public Book getBook(int id){
		return bookDaoImpl.getBook(id);
	}
	
	public boolean addBookToCart(int id, ShoppingCart sc){
		
		Book book = bookDaoImpl.getBook(id);
		if(book != null){
			sc.addBook(book);
			return true;
		}
		return false;
		
	}
	
	public void removeItemFromShoppingCart(int id, ShoppingCart sc){
		Book book = bookDaoImpl.getBook(id);
		if(book != null){
			sc.removeItem(id);
		}
	}
	
	public void clearShoppingCart(ShoppingCart sc){
		sc.clear();
	}

	public void updateItemQuantity(int id, int quantity, ShoppingCart sc) {
		sc.updateItemQuantity(id, quantity);
	}

	public void cash(ShoppingCart shoppingCart, String username, String accountId, String password) {
		
		//1、更新mybooks数据表相关记录salesamount和storeamount
		bookDaoImpl.batchUpdateStoreNumberAndSalesAmount(shoppingCart.getItems());
		
//		int i = 10/0;
		
		//2、更新account数据表的记录balance
		accountDao.updateBalance(Integer.parseInt(accountId), shoppingCart.getTotalMoney());
		
		//3、向trade数据表添加一条记录
		Trade trade = new Trade();
		trade.setTradeTime(new Date(new java.util.Date().getTime()));
		trade.setUserId(userDao.getUser(username, password).getUserId());
		tradeDao.insert(trade);
		
		//4、向tradeItem数据表插入n条记录
		Collection<TradeItem> items = new ArrayList<>();
		for(ShoppingCartItem sci : shoppingCart.getItems()){
			TradeItem tradeItem = new TradeItem();
			tradeItem.setBookId(sci.getBook().getId());
			tradeItem.setQuantity(sci.getQuantity());
			tradeItem.setTradeId(trade.getTradeId());
			
			items.add(tradeItem);
		}
		tradeItemDao.batchSave(items);
		
		//5、清空购物车
		shoppingCart.clear();
		
	}
	
}
