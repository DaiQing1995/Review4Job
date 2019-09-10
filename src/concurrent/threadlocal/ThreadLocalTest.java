package concurrent.threadlocal;

/**
 * ThreadLocal适用于给特定Thread存储数据的 Local表示了只有特定的那个thread能够获取到数据
 * 
 * 不同线程访问同一个ThreadLocal的get方法，ThreadLocal内部会从各自的线程中取出一个数组，
 * 然后再从数组中根据当前ThreadLocal的索引去查找出对应的value值，很显然，不同线程中的数组是不同的，
 * 这就是为什么通过ThreadLocal可以在不同的线程中维护一套数据的副本并且彼此互不干扰。
 * 
 * 
 * 什么时候用ThreadLocal呢？
 * 当参数传递过多，过于混乱时，ThreadLocal就是一个现成的全局变量，其实也就是拥有所有全局变量的好处
 * @author DaiQing
 *
 */
public class ThreadLocalTest {

	public static void main(String[] args) throws InterruptedException {
		//ThreadLocal，实际上就是thread内部（只属于thread）的全局变量，免除了各种参数传递
		final ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
		final ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();

		new Thread(new Runnable() {
			@Override
			public void run() {
				threadLocal1.set("A");
				threadLocal1.set("AAA");
				threadLocal2.set(1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(1 + " " +
						Thread.currentThread().getName() + " " + threadLocal1.get() + " " + threadLocal2.get());
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				threadLocal1.set("B");
				threadLocal2.set(2);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(2 + " " + 
						Thread.currentThread().getName() + " " + threadLocal1.get() + " " + threadLocal2.get());
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				//output: Thread-2 null null
				System.out.println(3 + " " + 
						Thread.currentThread().getName() + " " + threadLocal1.get() + " " + threadLocal2.get());
			}
		}).start();
		
		Thread.sleep(5000);
		//output: main null null
		System.out.println("main " + threadLocal1.get() + " " + threadLocal2.get()); 
	}
}
