package com.zcyfover.bookStore.Dao;

import java.util.Collection;
import java.util.List;

import com.zcyfover.bookStore.domain.Book;
import com.zcyfover.bookStore.domain.ShoppingCartItem;
import com.zcyfover.bookStore.web.CriteriaBook;
import com.zcyfover.bookStore.web.Page;

public interface BookDao {

	/**
	 * 根据id获取指定的Book对象
	 * @param id
	 * @return
	 */
	public abstract Book getBook(int id);
	
	/**
	 * 根据传入的CriteriaBook对象返回对应的Page对象
	 * @param cb
	 * @return
	 */
	public abstract Page<Book> getPage(CriteriaBook cb);
	
	/**
	 * 根据传入的CriteriaBook对象返回其对应的记录数
	 * @param cb
	 * @return
	 */
	public abstract long getTotalBookNumber(CriteriaBook cb);

	/**
	 * 根据传入的CriteriaBook和pageSize返回当前页对应的list
	 * @param cb
	 * @param pageSize
	 * @return
	 */
	public abstract List<Book> getPageList(CriteriaBook cb, int pageSize);

	/**
	 * 返回指定id的book的storeNumber值
	 * @param id
	 * @return
	 */
	public abstract int getStoreNumber(Integer id);

	/**
	 * 根据传入的 ShoppingCartItem 的集合, 
	 * 批量更新 books 数据表的 storenumber 和 salesnumber 字段的值
	 * @param items
	 */
	public abstract void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items);












}
