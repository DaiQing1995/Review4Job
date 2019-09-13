package concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class ThreadSafeConcurrency {
	
	private static int CLIENT_COUNT = 5000;
	private static int THREAD_COUNT = 200;
	//使用Atomic的包装类
	private static AtomicIntegerArray values = new AtomicIntegerArray(10);
	
	
	private static ExecutorService ec = Executors.newCachedThreadPool();
	private final static Semaphore semaphore = new Semaphore(THREAD_COUNT);
	private final static CountDownLatch  countDownLatch = new CountDownLatch(CLIENT_COUNT);
	
	public static void main(String[] args) throws InterruptedException {
		testAtomicIntArray();
	}

	private static void testAtomicIntArray() throws InterruptedException {
		for (int i = 0; i < CLIENT_COUNT; ++ i) {
			ec.execute(()->{
				try {
					semaphore.acquire();
					for (int j = 0; j < 10; ++ j) {
						// 非CAS类型
//						values.set(i, newValue);
						values.incrementAndGet(j);
					}
					semaphore.release();
				}catch (Exception e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		ec.shutdown();
		for(int i = 0; i < 10; ++ i)
			System.out.println(values.get(i) + " ");
	}
	
}
