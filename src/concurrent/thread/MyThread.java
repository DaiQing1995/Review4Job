package concurrent.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyThread extends Thread{
	
	public void run() {
		System.out.println("MyThread.run()");
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("program running");
		Date date = new Date();
		int taskSize = 5;
		
		ExecutorService pool = Executors.newFixedThreadPool(taskSize);
		
		List<Future> list = new ArrayList<>();
		for (int i = 0; i < taskSize; i ++) {
			Callable<Object> c = new MyCallable(i + "");
			Future<Object> f = pool.submit(c);
			list.add(f);
		}
		pool.shutdown();
		
		for (Future f : list) {
			System.out.println(f.get().toString());
		}
		
		Date date2 = new Date();
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("This is runnable");
				
			}
		};
		System.out.println("program finished, time: " + (date2.getTime() - date.getTime()));
	}
	
}
