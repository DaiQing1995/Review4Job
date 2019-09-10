package concurrent.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return new Random().nextInt(100);
			}
		};
		FutureTask<Integer> futureTask = new FutureTask<>(callable);
		new Thread(futureTask).start();
		Thread.sleep(1000);
		System.err.println(futureTask.get());
	}
}
