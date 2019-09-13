package concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockReEntranceTest{
	
	static ReentrantLock lock = new ReentrantLock();
	
	private static class ThreadDemo implements Runnable{

		@Override
		public void run() {
			// 可重入性
			for (int i = 0;i < 3; ++ i) {
				lock.lock();
			}
			for (int i  = 0;i < 2 ; ++ i)
				lock.unlock();	
		}
		
	}
	
	public static void main(String[] args) {
		new Thread(new ThreadDemo()).start();
		lock.lock();
		lock.unlock();
		System.out.println("finished");
	}
}
