package com.wjacksonh.latchexperiments;

import com.wjacksonh.latchexperiments.latch.CountDownLatchFactory;
import com.wjacksonh.latchexperiments.latch.CountDownLatchInterface;

public class TestLatchHarness {
	
	private final CountDownLatchFactory latchFactory;

	public TestLatchHarness(CountDownLatchFactory latchFactory) {
		this.latchFactory = latchFactory;
	}
	
	public long timeTasks(int nThreads, final Runnable task)
		throws InterruptedException {
		
		final CountDownLatchInterface startGate = latchFactory.createStartGate();
		final CountDownLatchInterface endGate = latchFactory.createEndGate(nThreads);
		
		for (int i = 0; i < nThreads; i++) {
			
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + " waits");
						startGate.await();
						System.out.println(Thread.currentThread().getName() + " has started");
						
						try {
							task.run();
						} finally {
							endGate.countDown();
						}
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						
						e.printStackTrace();
					}
				}
			});
			t.start();
		}
		
		long start = System.nanoTime();
		
		startGate.countDown();
		endGate.await();
		
		System.out.println("All threads have completed");
		long end = System.nanoTime();
				
		return end-start;
	}
}
