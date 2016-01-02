package com.wjacksonh.latchexperiments.latch;

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
