package com.wjacksonh.latchexperiments;

import com.wjacksonh.latchexperiments.latch.JavaCountDownLatchFactory;
import com.wjacksonh.latchexperiments.latch.MyCountDownLatchFactory;

public class LatchMain {

	public static void main(String[] args) {
		
		TestLatchHarness harness = new TestLatchHarness(new MyCountDownLatchFactory());
		
		try {
			long time = harness.timeTasks(5, new Runnable() {
				
				@Override
				public void run() {
					
					try {
						Thread.sleep(0, 100);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						
						e.printStackTrace();
					}				
				}
			});
			
			System.out.println("The harness took " + time + " nano secs.");
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

}
