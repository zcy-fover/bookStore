package com.zcyfover.bookStore.test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.zcyfover.bookStore.Dao.impl.BookDaoImpl;
import com.zcyfover.bookStore.domain.Book;
import com.zcyfover.bookStore.domain.ShoppingCartItem;
import com.zcyfover.bookStore.utils.JDBCUtils;
import com.zcyfover.bookStore.web.ConnectionContext;
import com.zcyfover.bookStore.web.CriteriaBook;
import com.zcyfover.bookStore.web.Page;

public class BookDaoImplTest {

	private BookDaoImpl bookDao = new BookDaoImpl();
	
	static{
		Connection connection = JDBCUtils.getConnection();
		ConnectionContext.getInstance().bind(connection);
	}
	
	@Test
	public void testGetBook() {
	
		Book book = bookDao.getBook(1);
		System.out.println(book);
	}

	@Test
	public void testGetPage() {
		
		CriteriaBook cb = new CriteriaBook(0, Integer.MAX_VALUE, 3);
		Page<Book> page = bookDao.getPage(cb);
		
		System.out.println("getPageNo:" + page.getPageNo());
		System.out.println("getTotalPageNumber:" + page.getTotalPageNumber());
		System.out.println("getPageSize:" + page.getPageSize());
		System.out.println("getPrevPage:" + page.getPrevPage());
		System.out.println("getNextPage:" + page.getNextPage());
		System.out.println("getTotalItemNumber:" + page.getTotalItemNumber());
	}

	@Test
	public void testGetTotalBookNumber() {

		CriteriaBook cb = new CriteriaBook(0, Integer.MAX_VALUE, 3);
		long count = bookDao.getTotalBookNumber(cb);
		System.out.println(count);
	}

	@Test
	public void testGetPageList() {
		
		CriteriaBook cb = new CriteriaBook(30, Integer.MAX_VALUE, 5);
		List<Book> list = bookDao.getPageList(cb, 5);
		System.out.println(list);
	}

	@Test
	public void testGetStoreNumber() {
		
		int storeNumber = bookDao.getStoreNumber(5);
		System.out.println(storeNumber);
		
	}

	@Test
	public void testBatchUpdateStoreNumberAndSalesAmount() {
		
		Collection<ShoppingCartItem> items = new ArrayList<>();
		
		Book book = bookDao.getBook(1);
		ShoppingCartItem sci = new ShoppingCartItem(book);
		sci.setQuantity(10);
		items.add(sci);
		
		book = bookDao.getBook(2);
		sci = new ShoppingCartItem(book);
		sci.setQuantity(10);
		items.add(sci);
		
		book = bookDao.getBook(3);
		sci = new ShoppingCartItem(book);
		sci.setQuantity(10);
		items.add(sci);
		
		book = bookDao.getBook(4);
		sci = new ShoppingCartItem(book);
		sci.setQuantity(10);
		items.add(sci);
		
		book = bookDao.getBook(5);
		sci = new ShoppingCartItem(book);
		sci.setQuantity(10);
		items.add(sci);
		
		bookDao.batchUpdateStoreNumberAndSalesAmount(items);
	}

}
