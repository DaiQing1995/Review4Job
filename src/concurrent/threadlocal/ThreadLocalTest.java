package concurrent.threadlocal;

/**
 * ThreadLocal�����ڸ��ض�Thread�洢���ݵ� Local��ʾ��ֻ���ض����Ǹ�thread�ܹ���ȡ������
 * 
 * ��ͬ�̷߳���ͬһ��ThreadLocal��get������ThreadLocal�ڲ���Ӹ��Ե��߳���ȡ��һ�����飬
 * Ȼ���ٴ������и��ݵ�ǰThreadLocal������ȥ���ҳ���Ӧ��valueֵ������Ȼ����ͬ�߳��е������ǲ�ͬ�ģ�
 * �����Ϊʲôͨ��ThreadLocal�����ڲ�ͬ���߳���ά��һ�����ݵĸ������ұ˴˻������š�
 * 
 * 
 * ʲôʱ����ThreadLocal�أ�
 * ���������ݹ��࣬���ڻ���ʱ��ThreadLocal����һ���ֳɵ�ȫ�ֱ�������ʵҲ����ӵ������ȫ�ֱ����ĺô�
 * @author DaiQing
 *
 */
public class ThreadLocalTest {

	public static void main(String[] args) throws InterruptedException {
		//ThreadLocal��ʵ���Ͼ���thread�ڲ���ֻ����thread����ȫ�ֱ���������˸��ֲ�������
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
