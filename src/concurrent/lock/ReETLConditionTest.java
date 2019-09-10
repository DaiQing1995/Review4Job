package concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReETLConditionTest {
	
	static Lock lock = new ReentrantLock();
	static Condition condition = lock.newCondition();
	
	public static void main(String[] args){
		lock.lock();
		new Thread(new SignalThread()).start();
		try {
			System.out.println("main thread wait");
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		System.out.println("continue run");
	}

	private static class SignalThread implements Runnable{
		@Override
		public void run() {
			lock.lock();
			System.out.println("son thread notify");
			condition.signal();
			lock.unlock();
		}
		
	}
	
}
