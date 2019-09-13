package concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceConcurrency {
	
	private static AtomicReference<Integer> atomicUserRef = new AtomicReference<>(0);
	
	// 创建针对属性的
	private static AtomicIntegerFieldUpdater<User> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");
	private static User user;
	
	private static class User{
		private String name;
		public volatile int old;
		
		public User(String name, int old) {
			this.name = name;
			this.old = old;
		}
		
		public String getName() {
			return name;
		}
		
		public int getOld() {
			return old;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
//		testAtomicReference();
		testAtomicIntegerFieldUpdater();
	}

	
	private static void testAtomicIntegerFieldUpdater() {
		user = new User("user", 100);
		atomicIntegerFieldUpdater.incrementAndGet(user);
		System.out.println("user age:" + atomicIntegerFieldUpdater.get(user));
	}
	
	private static void testAtomicReference() throws InterruptedException {
		atomicUserRef.compareAndSet(0, 1);
		atomicUserRef.compareAndSet(0, 2);
		atomicUserRef.compareAndSet(1, 3);
		atomicUserRef.compareAndSet(2, 4);
		atomicUserRef.compareAndSet(3, 5);
		System.out.println("Currency demo  " + atomicUserRef.get().toString());
	}
	
}
