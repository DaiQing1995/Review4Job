package classloading;

/**
 * 类的继承关系，观察<clinit>什么时候执行
 * @author DaiQing
 *
 */
public class ClassInit {

	private static class SSClass {
		static {
			System.out.println("SSClass");
		}
	}

	private static class SuperClass extends SSClass {
		static {
			System.out.println("SuperClass init!");
		}

		public static int val = 123;

		public SuperClass() {
			System.out.println("Super Class Instance()");
		}

	}

	private static class SubClass extends SuperClass {
		static {
			System.out.println("SubClass init");
		}
		static int a;

		/**
		 * 验证3：final字段在准备阶段被放入常量池，同样不会初始化SubClass
		 * 输出：
		 * 无输出
		 */
		private static final String FIN_FUNC = "I can not trigger initialization";
		
		public SubClass() {
			System.out.println("SubClass Instance()");
		}
	}

	public static void main(String[] args) {
		/**
		 * 验证 1: 虚拟机会保证在子类<init>()方法执行之前，父类的<clinit>()方法方法已经执行完毕 
		 * 输出：
		 * SSClass 
		 * SuperClass init!
		 * 123
		 *
		 * 在没有触发子类的变量获取等操作前，子类不会进入初始化状态。
		 */
		System.out.println(SubClass.val);
		
		
		/**
		 * 验证2：生成数组不会触发初始化
		 * 输出：
		 * 无输出
		 */
		SubClass[] subClasses = new SubClass[5];
		
		/**
		 * 验证3补充：这里仍然不会触发初始化
		 */
		System.out.println(SubClass.FIN_FUNC);
	}

}
