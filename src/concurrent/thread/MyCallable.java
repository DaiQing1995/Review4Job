package concurrent.thread;

import java.util.Date;
import java.util.concurrent.Callable;

class MyCallable implements Callable<Object>{

	private String taskNum;
	
	public MyCallable(String taskNum) {
		this.taskNum = taskNum;
	}
	
	@Override
	public Object call() throws Exception {
		System.out.println("task " + taskNum + " start.");
		Date dateTmp1 = new Date();
		Thread.sleep(1000);
		Date dateTmp2 = new Date();
		long time = dateTmp2.getTime() - dateTmp1.getTime();
		System.out.println("task " + taskNum + " finished.");
		return taskNum + " return, time:" + time;
	}

}
