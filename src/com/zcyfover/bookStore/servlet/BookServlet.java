package com.zcyfover.bookStore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zcyfover.bookStore.domain.Account;
import com.zcyfover.bookStore.domain.Book;
import com.zcyfover.bookStore.domain.ShoppingCart;
import com.zcyfover.bookStore.domain.ShoppingCartItem;
import com.zcyfover.bookStore.domain.User;
import com.zcyfover.bookStore.service.AccountService;
import com.zcyfover.bookStore.service.BookService;
import com.zcyfover.bookStore.service.UserService;
import com.zcyfover.bookStore.web.BookStoreWebUtils;
import com.zcyfover.bookStore.web.CriteriaBook;
import com.zcyfover.bookStore.web.Page;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/bookServlet")
public class BookServlet extends HttpServlet {
	
	private BookService bookService = new BookService();
	private UserService userService = new UserService();
	private AccountService accountService = new AccountService();
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String methodName = request.getParameter("method");
		
		try {
//			Method method = getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 显示所有图书，实现页面跳转
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageNoStr = request.getParameter("pageNo");
		String minPriceStr = request.getParameter("minPrice");
		String maxPriceStr = request.getParameter("maxPrice");
		
		int pageNo = 1;
		int minPrice = 0;
		int maxPrice = Integer.MAX_VALUE;
		
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
//			System.out.println("pageNoStr数据转换错误");
		}
		try {
			minPrice = Integer.parseInt(minPriceStr);
		} catch (NumberFormatException e) {
//			System.out.println("pageNoStr数据转换错误");
		}
		try {
			maxPrice = Integer.parseInt(maxPriceStr);
		} catch (NumberFormatException e) {
//			System.out.println("pageNoStr数据转换错误");
		}
		
		CriteriaBook criteriaBook = new CriteriaBook(minPrice, maxPrice, pageNo);
		Page<Book> page = bookService.getPage(criteriaBook);
		
		request.setAttribute("bookpage", page);
		request.getRequestDispatcher("/views/books.jsp").forward(request, response);
		
	}

	/**
	 * 获取选中书的详细信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idStr = request.getParameter("id");
		int id = -1;
		
		Book book = null;
		
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {
		}
		
		if(id > 0){
			book = bookService.getBook(id);
		}
		if(book == null){
			response.sendRedirect(request.getContextPath() + "/views/common/error.jsp");
			return;
		}
		
		request.setAttribute("book", book);
		request.getRequestDispatcher("/views/book.jsp").forward(request, response);
		
	}
	
	/**
	 * 想购物车中添加书本
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//获取商品的id
		String idStr = request.getParameter("id");
		int id = -1;
		boolean flag = false;
		
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {
		}
		
		//获取购物车对象
		if(id > 0){
			ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
			//调用bookService 的addBookToCart方法
			flag = bookService.addBookToCart(id, sc);
		}
		
		//调用getBooks方法
		if(flag){
			getBooks(request, response);
			return ;
		}
		response.sendRedirect(request.getContextPath() + "/views/error.jsp");
		
	}
	
	public void forwardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		request.getRequestDispatcher("/views/" + page + ".jsp").forward(request, response);
	}
	
	public void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idStr = request.getParameter("id");
		int id = -1;
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {
		}
		
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
		
		if(id > 0){
			bookService.removeItemFromShoppingCart(id, sc);
		}
		
		if(sc.isEmpty()){
			request.getRequestDispatcher("/views/emptyCart.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/views/cart.jsp").forward(request, response);
	}
	
	public void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
		bookService.clearShoppingCart(sc);
		request.getRequestDispatcher("/views/emptyCart.jsp").forward(request, response);
	}
	
	/**
	 * 4. 在 updateItemQuantity 方法中, 获取 quanity, id, 再获取购物车对象, 调用 service 的方法做修改
	 * 5. 传回 JSON 数据: bookNumber:xx, totalMoney
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateItemQuantity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idStr = request.getParameter("id");
		String quantityStr = request.getParameter("quantity");
		int id = -1;
		int quantity = -1;
		try {
			id = Integer.parseInt(idStr);
			quantity = Integer.parseInt(quantityStr);
		} catch (Exception e) { }
		
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
		
		if(id > 0 && quantity > 0){
			bookService.updateItemQuantity(id, quantity, sc);
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("bookNumber", sc.getBookNumber());
		result.put("totalMoney", sc.getTotalMoney());
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(result);
		response.setContentType("html/javascript");
		response.getWriter().print(jsonStr);
		
	}
	
	/**
	 * 进行简单验证
	 * @param username
	 * @param accountId
	 * @return
	 */
	public StringBuffer validate(String username, String accountId, String password){
		StringBuffer errors = new StringBuffer("");
		
		if(username == null || username.trim().equals("")){
			errors.append("用户名不能为空<br>");
		}
		if(accountId == null || accountId.trim().equals("")){
			errors.append("账号不能为空");
		}
		if(password == null || password.trim().equals("")){
			errors.append("密码不能为空<br>");
		}
		return errors;
	}
	public StringBuffer validateUser(String username, String accountId, String password){
		
		boolean flag = false;
		StringBuffer errors2 = new StringBuffer("");
		User user = userService.getUser(username, password);
		if(user != null){
			int accountId2 = user.getAccountId();
			if(accountId.trim().equals("" + accountId2)){
				flag = true;
			}
		}
		if(!flag){
			errors2.append("用户名、账号和密码不匹配");
		}
		return errors2;
		
	}
	public StringBuffer validateBookStoreNumber(HttpServletRequest request){
		
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
		StringBuffer errors = new StringBuffer("");
		for(ShoppingCartItem sci : sc.getItems()){
			int quantity = sci.getQuantity();
			int storeNumber = bookService.getBook(sci.getBook().getId()).getStoreNumber();
			if(quantity > storeNumber){
				errors.append("《" + sci.getBook().getTitle() + "》" + "库存不足<br>");
			}
		}
		
		return errors;
	}
	public StringBuffer validateBalance(HttpServletRequest request, String accountId){
		
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
		StringBuffer errors = new StringBuffer("");
		
		Account account = accountService.getAccount(Integer.parseInt(accountId));
		if(sc.getTotalMoney() > account.getBalance()){
			errors.append("余额不足");
		}
		
		return errors;
	}
	
	public void cash(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String accountId = request.getParameter("accountId");
		String password = request.getParameter("password");
		//1、简单验证：验证表单域的值是否符合基本规范，不需查询数据库
		StringBuffer errors = validate(username, accountId, password);
		
		if(errors.toString().equals("")){
			//用户与账户的匹配验证
			errors = validateUser(username, accountId, password);
			
			if(errors.toString().equals("")){
				//验证库存是否充足
				errors = validateBookStoreNumber(request);
				if(errors.toString().equals("")){
					//余额验证
					errors = validateBalance(request, accountId);
				}
			}
			
		}
		if(!errors.toString().equals("")){
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/views/cash.jsp").forward(request, response);
			return;
		}
		
		//验证通过，执行具体的逻辑操作
		bookService.cash(BookStoreWebUtils.getShoppingCart(request), username, accountId, password);
		response.sendRedirect(request.getContextPath() + "/views/common/success.jsp");
	}
}
