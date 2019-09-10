package concurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallableAndFuture {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		CompletionService<Integer> cs = new ExecutorCompletionService<>(threadPool);
		for (int i = 0; i < 5; i ++) {
			final int taskId = i;
			cs.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return taskId;
				}
			});
		}
		for (int i = 0; i < 5; ++ i) {
			System.out.println(cs.take().get());
		}
	}
}
