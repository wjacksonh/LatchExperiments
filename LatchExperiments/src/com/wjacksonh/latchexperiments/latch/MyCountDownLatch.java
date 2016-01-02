package com.wjacksonh.latchexperiments.latch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.wjacksonh.latchexperiments.latch.base.CountDownLatchInterface;

public class MyCountDownLatch implements CountDownLatchInterface{

	private boolean  latchLocked = true;
	private int      count = 0;
	private final    ReentrantLock lock;
	private final    Condition     cond;
	
	public MyCountDownLatch(int count) {
		
		this.lock = new ReentrantLock();
		this.cond = lock.newCondition();
		
		this.count = count;
	}
	
	@Override
	public void countDown() {
		
		lock.lock();
		try {
		
			if(latchLocked == true) {
			
				count--;
				
				if(count == 0){
					cond.signalAll();
					
					latchLocked = false;
				}			
			}
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public void await () throws InterruptedException {
		lock.lock();
		try {
	
			if(latchLocked == true){
			
				cond.await();
			}
		
		} finally {
			lock.unlock();
		}
	}
}
