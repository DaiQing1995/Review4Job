package concurrent.multithread.prac;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccessPart {

	volatile static int T = 0;

	private static class SubThread implements Runnable {
		@Override
		public void run() {
			synchronized (AccessPart.class) {
				T++;
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadPool = Executors.newFixedThreadPool(50);
		for (int i = 0; i < 1000_000; ++i)
			threadPool.execute(new SubThread());
		while (T != 1000_000) {
			System.out.println(T);
			Thread.currentThread().sleep(100);
		}
		System.out.println(T);
		threadPool.shutdown();
	}
}
