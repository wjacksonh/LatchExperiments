package com.wjacksonh.latchexperiments.latch;

import java.util.concurrent.CountDownLatch;

import com.wjacksonh.latchexperiments.latch.base.CountDownLatchInterface;

public class JavaCountDownLatch implements CountDownLatchInterface {

	private final CountDownLatch latch;
	
	public JavaCountDownLatch(CountDownLatch latch) {
		this.latch = latch;
	}
	
	@Override
	public void countDown() {
		latch.countDown();

	}

	@Override
	public void await() throws InterruptedException {
		latch.await();
	}

}
