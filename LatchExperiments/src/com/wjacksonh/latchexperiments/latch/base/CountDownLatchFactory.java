package com.wjacksonh.latchexperiments.latch.base;

public interface CountDownLatchFactory {
	public CountDownLatchInterface createStartGate ();
	public CountDownLatchInterface createEndGate (int nThreads);
}
