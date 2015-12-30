package com.wjacksonh.philosopher;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author Walter
 * This class contains the concurrent container for the forks array list
 * the ReentrantLock and condition to synchronize on all forks for obtaining 
 * fork pairs. Starvation should be reduced due to the fair flag on the lock
 */
public final class ForksContainer {

	private final CopyOnWriteArrayList<ReentrantLock> forks;
	private final ReentrantLock                       lock;
	private final Condition                           cond;
	
	public ForksContainer(CopyOnWriteArrayList<ReentrantLock> forks) {
		this.forks = forks;		
		this.lock = new ReentrantLock(true);
		this.cond = lock.newCondition();
	}
	
	public CopyOnWriteArrayList<ReentrantLock> getForks() { return forks; }
	
	public void lock()       { lock.lock();}
	public void unlock()     { lock.unlock();}
	public void signalAll () { cond.signalAll(); }
	public void await () 
			throws InterruptedException {
		cond.await(); 
	}  
		
}
