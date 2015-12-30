package com.wjacksonh.philosopher;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;



/**
 * @author Walter
 * Create all elements necessary to run the dining philosopher solution.
 * TODO: This styill needs some validation that this solves the problem accurately as 
 * explained here: https://en.wikipedia.org/wiki/Dining_philosophers_problem
 */
public class PhilosopherMain {

	public static void main(String[] args) {
		
		ForksContainer forkContainer = new ForksContainer(new CopyOnWriteArrayList<ReentrantLock>());
		ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>(5);
		
		for (int i = 0; i < 5; i++) {
			forkContainer.getForks().add(new ReentrantLock(true));
			philosophers.add(new Philosopher(i, new ForkPair(i, forkContainer)));
		}
		
		for (Philosopher philosopher : philosophers) {
			new Thread(philosopher).start();
		}
	}

}
