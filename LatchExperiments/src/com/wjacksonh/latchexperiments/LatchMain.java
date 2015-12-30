package com.wjacksonh.latchexperiments;

public class LatchMain {

	public static void main(String[] args) {

		TestLatchHarness harness = new TestLatchHarness();
		
		try {
			long time = harness.timeTasks(5, new Runnable() {
				
				@Override
				public void run() {
					
					try {
						Thread.sleep(0, 100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
			});
			
			System.out.println("The harness took " + time + " nano secs.");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
