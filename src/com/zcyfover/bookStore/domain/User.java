package com.zcyfover.bookStore.domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class User {

	private Integer userId;
	private String username;
	private String password;
	private int accountId;

	private Set<Trade> trades = new LinkedHashSet<Trade>();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Set<Trade> getTrades() {
		return trades;
	}

	public void setTrades(Set<Trade> trades) {
		this.trades = trades;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", accountId="
				+ accountId + ", trades=" + trades + "]";
	}

}
