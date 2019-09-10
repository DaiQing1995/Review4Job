package concurrent.lock;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E> {
	
	private int size;
	
	ReentrantLock lock = new ReentrantLock();
	
	LinkedList<E> list = new LinkedList<>();
	
	Condition notFull = lock.newCondition();
	Condition notEmpty = lock.newCondition();
	
	public MyBlockingQueue(int size){
		this.size = size;
	}
	
	public void enqueue(E e) {
		try {
			lock.tryLock(10, TimeUnit.MILLISECONDS);
			while (list.size() == size)
				notFull.await();
			list.add(e);
			System.out.println(e + " added");
			notEmpty.signal();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public E deQueue() {
		E e = null;
		try {
			lock.tryLock(10, TimeUnit.MILLISECONDS);
			while(list.size() == 0)
				notEmpty.notify();
			e = list.removeFirst();
			System.out.println(e + " outed");
			notFull.signal();
			return e;
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}finally {
			lock.unlock();
		}
		return e;
	}
	
	public static void main(String[] args) {
		MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(2);
		for (int i = 0;i < 10; ++ i) {
			int data = i;
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						queue.enqueue(data);
					} catch (Exception e) {
					}
				}
			}).start();
		}
		
		for (int i = 0;i < 10; ++ i) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					queue.deQueue();
					
				}
			}).start();
		}
	}
}
