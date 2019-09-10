package staticdynamicdispatch;

public class StaticAndDynamicDispatch {
	private static class Do{
	}
	private static class Eat extends Do{
	}

	private static class Drink extends Do{
	}

	private static class Father {
		public void doSomething(Eat arg) {
			System.out.println("爸爸在吃饭");
		}

		public void doSomething(Drink arg) {
			System.out.println("爸爸在喝水");
		}
		public void doSomething(Do arg) {
			System.out.println("爸爸在Do");
		}
	}

	private static class Child extends Father {
		public void doSomething(Eat arg) {
			System.out.println("儿子在吃饭");
		}

		public void doSomething(Drink arg) {
			System.out.println("儿子在喝水");
		}

		public void doSomething(Do arg) {
			System.out.println("儿子在Do");
		}
	}

	public static void main(String[] args) {
		Father father = new Father();
		Father child = new Child();
		father.doSomething(new Eat());
		child.doSomething(new Drink());
		// do1静态分派了Do的dosomething方法，见StaticDispatch中的例子
		Do do1 = new Eat();
		father.doSomething(do1);
		/*
		 output:
		 爸爸在吃饭
	 	 儿子在喝水
		 爸爸在Do
		 */
	}

}