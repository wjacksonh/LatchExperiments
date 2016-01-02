package com.wjacksonh.latchexperiments.latch;

public interface CountDownLatchFactory {
	public CountDownLatchInterface createStartGate ();
	public CountDownLatchInterface createEndGate (int nThreads);
}
