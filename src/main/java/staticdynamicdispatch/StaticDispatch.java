package staticdynamicdispatch;

/**
 * 在编译器确定的静态分配
 * 
 * 该方法在主类当中参数重载方法，调用者（宗量）只通过参数（数据宗量）的静态类型调用，
 * 这里不存在数据宗量的多态。
 * @author DaiQing
 *
 */
public class StaticDispatch {
	private static class Human {
	}

	private static class Man extends Human {
	}

	private static class Woman extends Human {
	}

	public void say(Human m) {
		System.out.println("Human saying");
	}

	public void say(Woman m) {
		System.out.println("Woman saying");
	}

	public void say(Man m) {
		System.out.println("Man saying");
	}

	public static void main(String[] args) {
		// Human 为静态类型，Woman为实际类型
		Human woman = new Woman();
		// Human 为静态类型， Man为实际类型
		Human man = new Man();
		StaticDispatch sdp = new StaticDispatch();
		// sdp为宗量， man，human为数据宗量
		sdp.say(man);
		sdp.say(woman);
		/*
		 * output:
		 * Human saying 
		 * Human saying
		 */
	}
}
