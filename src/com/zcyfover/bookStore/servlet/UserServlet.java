package com.zcyfover.bookStore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcyfover.bookStore.domain.User;
import com.zcyfover.bookStore.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取 username 请求参数的值
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//调用 UserService 的 getUser(username) 获取User 对象：要求 trades 是被装配好的，而且每一个 Trade 对象的 items 也被装配好
		User user = userService.getUserWithUsername(username, password);
		
		//把 User 对象放入到 request 中
		if(user == null){
			response.sendRedirect(request.getContextPath() + "/views/common/error.jsp");
			return ;
		}
		request.setAttribute("user", user);
		
		//转发页面到 /views/trades.jsp
		request.getRequestDispatcher("/views/trades.jsp").forward(request, response);
		
	}

}
