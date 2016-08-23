package com.zcyfover.bookStore.exception;

public class DBException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DBException(){
		
	}
	
	public DBException(String msg){
		super(msg);
	}

}
