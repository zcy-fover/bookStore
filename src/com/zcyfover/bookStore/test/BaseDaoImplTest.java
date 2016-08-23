package com.zcyfover.bookStore.test;

import java.sql.Date;
import java.util.List;

import org.junit.Test;

import com.zcyfover.bookStore.Dao.impl.BaseDaoImpl;
import com.zcyfover.bookStore.Dao.impl.BookDaoImpl;
import com.zcyfover.bookStore.domain.Book;

public class BaseDaoImplTest {

	@SuppressWarnings("rawtypes")
	private BaseDaoImpl baseDaoImpl = new BaseDaoImpl();
	private BookDaoImpl bookDaoImpl = new BookDaoImpl();
	
	@Test
	public void testBaseDaoImpl() {

	}

	@Test
	public void testInsert() {
		
		String sql = "INSERT INTO trade (userid, tradetime) VALUES (?, ?)";
		long id = baseDaoImpl.insert(sql, 1, new Date(new java.util.Date().getTime()));
		System.out.println("新插入的记录的ID = " + id);
	}

	@Test
	public void testUpdate() {
		
		String sql = "UPDATE mybooks SET Salesamount = ? WHERE id = ?";
		bookDaoImpl.update(sql, 10, 4);
	}

	@Test
	public void testQuery() {
		
		String sql = "SELECT id, author, title, price, publishingDate, salesAmount, storeNumber, remark " +
					 "FROM mybooks WHERE id = ?";
		Book book = bookDaoImpl.query(sql, 1);
		System.out.println(book);
		
	}

	@Test
	public void testQueryForList() {
		
		String sql = "SELECT id, author, title, price, publishingDate, salesAmount, storeNumber, remark " +
				 "FROM mybooks WHERE id < ?";
		List<Book> list = bookDaoImpl.queryForList(sql, 4);
		System.out.println(list);
	}

	@Test
	public void testGetSingleVal() {

		String sql = "SELECT count(id) FROM mybooks";
		long count = bookDaoImpl.getSingleVal(sql);
		System.out.println("共有 " + count + "本书。");
	}

	@Test
	public void testBatch() {
		
		String sql = "UPDATE mybooks SET salesAmount = ?, storeNumber = ? " +
					 "WHERE id = ?";
		bookDaoImpl.batch(sql, new Object[]{5, 5, 5}, new Object[]{5, 5, 6}, new Object[]{5, 5, 7});
	}

}
