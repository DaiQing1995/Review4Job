package concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockInterruuptibleTest {

	static ReentrantLock lock1 = new ReentrantLock();
	static ReentrantLock lock2 = new ReentrantLock();

	private static void forceStop() {
		//定义两个获取锁的顺序不同
		Thread thread0 = new Thread(new ThreadDemo(lock1, lock2));
		Thread thread1 = new Thread(new ThreadDemo(lock2, lock1));
		thread0.start();
		thread1.start();
		//采取强行中断的方式
		thread0.interrupt();
	}
	
	private static void normalStop() {
		Thread thread0 = new Thread(new ThreadDemo1(lock1, lock2));
		Thread thread1 = new Thread(new ThreadDemo1(lock2, lock1));
		thread0.start();
		thread1.start();
	}
	
	public static void main(String[] args) {
//		forceStop();
		normalStop();
	}

	private static class ThreadDemo implements Runnable {

		Lock firstLock;
		Lock secondLock;

		public ThreadDemo(Lock firstLock, Lock secondLock) {
			this.firstLock = firstLock;
			this.secondLock = secondLock;
		}

		@Override
		public void run() {
			try {
				firstLock.lockInterruptibly();
				//更好的触发死锁
				TimeUnit.MILLISECONDS.sleep(10);
				secondLock.lockInterruptibly();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				firstLock.unlock();
				secondLock.unlock();
				System.out.println(Thread.currentThread().getName() + "ends!");
			}
		}
	}
	
	private static class ThreadDemo1 implements Runnable {

		Lock firstLock;
		Lock secondLock;

		public ThreadDemo1(Lock firstLock, Lock secondLock) {
			this.firstLock = firstLock;
			this.secondLock = secondLock;
		}

		@Override
		public void run() {
			// 该方案抄自网络，但是并不能保障线程最后拥有两把锁，所以不大合用。
			try {
				while(!firstLock.tryLock()) {
					TimeUnit.MILLISECONDS.sleep(10);
				}
				boolean flag = false; 
				while(!secondLock.tryLock()) {
					firstLock.unlock();
					flag = true;
					TimeUnit.MILLISECONDS.sleep(10);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				firstLock.unlock();
				secondLock.unlock();
				System.out.println(Thread.currentThread().getName() + "ends!");
			}
		}

	}
}
