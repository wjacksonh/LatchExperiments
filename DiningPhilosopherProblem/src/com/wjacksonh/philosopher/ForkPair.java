package com.wjacksonh.philosopher;



/**
 * @author Walter
 * This is the ForkPair class that will enforce synchronization and acquisition of pars of forks
 * to prevent each philosopher from only obtaining one.
 */
public class ForkPair {

	private int                      philosopher;
	private ForksContainer           forkContainer;
	
	public ForkPair(int philosopher, ForksContainer forkContainer) {
		
		this.philosopher = philosopher;
		this.forkContainer = forkContainer;
	}
	
	public void pickupForks () 
			throws InterruptedException
	{
		boolean result = false;
		int forkIndex1 = philosopher;
		int forkIndex2 = ((philosopher < (forkContainer.getForks().size()-1)) ? (philosopher + 1) : 0); 
		
		forkContainer.lock(); 
		try {			
			while(result == false) {
			
				// Use tryLock either both forks can be obtained or none.
				if (forkContainer.getForks().get(forkIndex1).tryLock() && forkContainer.getForks().get(forkIndex2).tryLock()) {
					result = true;
					break;
				} else {
					// Be sure to unlock any fork that was obtained by tryLock when only 
					// one or none could be obtained.
					if (forkContainer.getForks().get(forkIndex1).isHeldByCurrentThread())
						forkContainer.getForks().get(forkIndex1).unlock();
					
					if (forkContainer.getForks().get(forkIndex2).isHeldByCurrentThread())
						forkContainer.getForks().get(forkIndex2).unlock();
				}
				
				forkContainer.await();
			}
		} finally {
			forkContainer.unlock();
		}
	}
	
	public void putdownForks ()
	{
		int forkIndex1 = philosopher;
		int forkIndex2 = ((philosopher < (forkContainer.getForks().size()-1)) ? (philosopher + 1) : 0); 
		
		forkContainer.lock(); 
		try {
			// TODO: This should be re-thought a bit. The check for isHeldByCurrentThread 
			// might not be necessary but if we can not release the lock then what?
			if (forkContainer.getForks().get(forkIndex1).isHeldByCurrentThread())
				forkContainer.getForks().get(forkIndex1).unlock();
			
			if (forkContainer.getForks().get(forkIndex2).isHeldByCurrentThread())
				forkContainer.getForks().get(forkIndex2).unlock();
			
			// TODO: Should we avoid this if we can not unlock? Could it lead to deadlock if we do?
			forkContainer.signalAll();
		} finally {
				forkContainer.unlock();
		}
	}
}
