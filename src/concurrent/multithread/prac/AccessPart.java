package concurrent.multithread.prac;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccessPart {

	volatile static int T = 0;

	private static class SubThread implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 10; ++i) {
				synchronized (AccessPart.class) {
					T++;
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadPool = Executors.newFixedThreadPool(50);
		for (int i = 0; i < 100_000; ++i)
			threadPool.execute(new SubThread());
		Thread.currentThread().join();
		System.out.println(T);
		threadPool.shutdown();
	}
}
