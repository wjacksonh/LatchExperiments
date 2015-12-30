package com.wjacksonh.philosopher;



/**
 * @author Walter
 * This is the Philosopher. Quite simple he eats only when he obtains both forks and 
 * release when he is done.
 */
public class Philosopher implements Runnable{

	private int      philosopher;
	private ForkPair forkPair;
	
	public Philosopher(int philosopher, ForkPair forkPair) {
		
		this.philosopher = philosopher;
		this.forkPair = forkPair;
	};
	
	@Override
	public void run() {
		
		while(true){
			try {
				forkPair.pickupForks();

				System.out.println("Philosopher " + philosopher +  "Eating");
				
				Thread.sleep(100);
	
				forkPair.putdownForks();
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
