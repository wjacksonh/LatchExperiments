package com.wjacksonh.latchexperiments.latch;

import java.util.concurrent.CountDownLatch;

import com.wjacksonh.latchexperiments.latch.base.CountDownLatchFactory;
import com.wjacksonh.latchexperiments.latch.base.CountDownLatchInterface;

public class MyCountDownLatchFactory implements CountDownLatchFactory {

	@Override
	public CountDownLatchInterface createStartGate() {
		
		return new JavaCountDownLatch(new CountDownLatch(1));
	}

	@Override
	public CountDownLatchInterface createEndGate(int nThreads) {

		return new JavaCountDownLatch(new CountDownLatch(nThreads));
	}

}
