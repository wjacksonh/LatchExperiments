package com.wjacksonh.latchexperiments.latch.base;

public interface CountDownLatchInterface {
	public void countDown();
	public void await () throws InterruptedException;
}
