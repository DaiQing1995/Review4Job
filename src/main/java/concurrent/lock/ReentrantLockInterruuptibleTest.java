package concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockInterruuptibleTest {

	static ReentrantLock lock1 = new ReentrantLock();
	static ReentrantLock lock2 = new ReentrantLock();

	private static void forceStop() {
		//����������ȡ����˳��ͬ
		Thread thread0 = new Thread(new ThreadDemo(lock1, lock2));
		Thread thread1 = new Thread(new ThreadDemo(lock2, lock1));
		thread0.start();
		thread1.start();
		//��ȡǿ���жϵķ�ʽ
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
				//���õĴ�������
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
			// �÷����������磬���ǲ����ܱ����߳����ӵ�������������Բ�����á�
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
