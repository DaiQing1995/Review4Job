package concurrent.multithread.prac;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * target is to create thread and add a variable to 1000_000_000
 * 
 * @author DaiQing
 *
 */
public class CountDownLatchTest {

	static CountDownLatch latch = new CountDownLatch(10_000);
	volatile static int N = 0;

	private static class SubThread implements Runnable {
		@Override
		public void run() {
			synchronized (SubThread.class) {	
				System.out.println("latch: " + latch.getCount() + ", " + Thread.currentThread().getId() + "is going to count down");
				latch.countDown();
				N++;
				System.out.println("N is " + N);
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(20);
		for (int i = 0; i < 10_000; ++i)
			newFixedThreadPool.execute(new SubThread());
		System.out.println("main waiting");
		latch.await(3000, TimeUnit.MILLISECONDS);
		System.out.println("main get");
		System.out.println(N);
		newFixedThreadPool.shutdown();
	}

}
