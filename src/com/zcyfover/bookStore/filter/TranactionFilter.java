package com.zcyfover.bookStore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.SQLException;

import com.zcyfover.bookStore.utils.JDBCUtils;
import com.zcyfover.bookStore.web.ConnectionContext;

/**
 * Servlet Filter implementation class TranactionFilter
 */
@WebFilter("/*")
public class TranactionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TranactionFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		Connection connection = null;
		
		try {
			//1、获取链接
			connection = JDBCUtils.getConnection();
			
			//2、开启事务
			connection.setAutoCommit(false);
			
			//3、利用ThreadLocal把链接和当前线程绑定
			ConnectionContext.getInstance().bind(connection);
			
			//4、把请求转给目标servlet
			chain.doFilter(request, response);
			
			//5、不出异常就提交事务
			connection.commit();
			
		} catch (Exception e) {
			//6、出了异常就回滚事务
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect(req.getContextPath() + "/error.jsp");
			
		} finally{
			//7、解除绑定、关闭连接
			ConnectionContext.getInstance().remove();
			JDBCUtils.release(connection);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
