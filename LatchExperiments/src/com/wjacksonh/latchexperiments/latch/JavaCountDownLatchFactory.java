package com.wjacksonh.latchexperiments.latch;

import com.wjacksonh.latchexperiments.latch.base.CountDownLatchFactory;
import com.wjacksonh.latchexperiments.latch.base.CountDownLatchInterface;

public class JavaCountDownLatchFactory implements CountDownLatchFactory {

	@Override
	public CountDownLatchInterface createStartGate() {
		
		return new MyCountDownLatch(1);
	}

	@Override
	public CountDownLatchInterface createEndGate(int nThreads) {

		return new MyCountDownLatch(nThreads);
	}

}
