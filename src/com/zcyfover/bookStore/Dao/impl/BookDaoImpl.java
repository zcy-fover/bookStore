package com.zcyfover.bookStore.Dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.zcyfover.bookStore.Dao.BookDao;
import com.zcyfover.bookStore.domain.Book;
import com.zcyfover.bookStore.domain.ShoppingCartItem;
import com.zcyfover.bookStore.web.CriteriaBook;
import com.zcyfover.bookStore.web.Page;

public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {

	@Override
	public Book getBook(int id) {

		String sql = "SELECT id, author, title, price, publishingDate, "
					 + "salesAmount, storeNumber, remark FROM mybooks WHERE id = ?";
		return query(sql, id);
		
	}

	@Override
	public Page<Book> getPage(CriteriaBook cb) {

		Page<Book> page = new Page<>(cb.getPageNo());
		
		page.setTotalItemNumber(getTotalBookNumber(cb));
		//校验pageNo的合法性
		cb.setPageNo(page.getPageNo());
		page.setList(getPageList(cb, 3));
		
		return page;
	}

	@Override
	public long getTotalBookNumber(CriteriaBook cb) {

		String sql = "SELECT count(id) FROM mybooks WHERE price >= ? AND price <= ?";
		return getSingleVal(sql, cb.getMinPrice(), cb.getMaxPrice());
	}

	@Override
	public List<Book> getPageList(CriteriaBook cb, int pageSize) {

		String sql = "SELECT id, author, title, price, publishingDate, " + 
					 "salesAmount, storeNumber, remark FROM mybooks " + 
					 "WHERE price >= ? AND price <= ? " + 
					 "LIMIT ?, ?";
		return queryForList(sql, cb.getMinPrice(), cb.getMaxPrice(),
				           (cb.getPageNo() - 1) * pageSize, pageSize);
	}

	@Override
	public int getStoreNumber(Integer id) {

		String sql = "SELECT storeNumber FROM mybooks WHERE id = ?";
		return getSingleVal(sql, id);
	}

	@Override
	public void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items) {
		
		String sql = "UPDATE mybooks SET salesAmount = salesAmount + ?, " +
					 "storeNumber = storenumber - ? " +
				     "WHERE id = ?";
		Object [][] params = null;
		params = new Object[items.size()][3];
		List<ShoppingCartItem> scis = new ArrayList<>(items);
		for(int i = 0; i < items.size(); i++){
			params[i][0] = scis.get(i).getQuantity();
			params[i][1] = scis.get(i).getQuantity();
			params[i][2] = scis.get(i).getBook().getId();
		}
		
		batch(sql, params);
	}

}
