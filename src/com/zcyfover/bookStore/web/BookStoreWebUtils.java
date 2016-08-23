package com.zcyfover.bookStore.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zcyfover.bookStore.domain.ShoppingCart;

public class BookStoreWebUtils {

	/**
	 * 获取购物车对象，若session中有购物车对象，则从session中获取，
	 * 			        若没有，则新创建一个，然后添加到session中
	 * @param request
	 * @return
	 */
	public static ShoppingCart getShoppingCart(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
		if(sc == null){
			sc = new ShoppingCart();
			session.setAttribute("ShoppingCart", sc);
		}
		
		return sc;
	}
}
