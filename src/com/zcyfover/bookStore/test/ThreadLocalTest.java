package com.zcyfover.bookStore.test;

public class ThreadLocalTest implements Runnable{

	String name = null;
	int i =0;
	ThreadLocal<String> threadLocal = new ThreadLocal<>();
	
	public static void main(String[] args) {
		
		ThreadLocalTest threadLocalTest = new ThreadLocalTest();
		
		new Thread(threadLocalTest, "AAAA").start();
		new Thread(threadLocalTest, "BBBB").start();
		
	}

	@Override
	public void run() {
		
		for(; i < 20; i++){
			
//			使用单线程就没有了线程安全问题
//			synchronized (this) {
//				name = Thread.currentThread().getName();
//				try {
//					Thread.sleep(10);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				
//				System.out.println(Thread.currentThread().getName() + " : " + name);
//			}
			
			
//也可以使用如下方法，避免线程安全问题
			
			threadLocal.set(Thread.currentThread().getName());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName() + "---" + threadLocal.get());
			
		}
		
	}

}
