package com.wjacksonh.latchexperiments.latch;

public interface CountDownLatchInterface {
	public void countDown();
	public void await () throws InterruptedException;
}
