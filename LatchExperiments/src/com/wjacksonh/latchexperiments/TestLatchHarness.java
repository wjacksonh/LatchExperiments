package com.wjacksonh.latchexperiments;

import java.util.concurrent.CountDownLatch;

public class TestLatchHarness {

	public long timeTasks(int nThreads, final Runnable task)
		throws InterruptedException {
		
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);
		
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
						// TODO Auto-generated catch block
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
