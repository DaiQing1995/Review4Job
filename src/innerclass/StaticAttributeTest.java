package innerclass;

import java.util.Random;

public class StaticAttributeTest {

	public int out1 = 1;
	public int out2 = 2;
	public int out3 = 3;
	public static String staticStr = "dsa";
	public static String staticStr2 = new String("hhaa");
	
	public Inner inner;

	public class Inner{
		//只允许final 的static, 由于多个外部实例并发访问这个类的静态元素，将无法保证所有内部类这个static的一致
		/*
		 * 1.如果没有final，还表达了该静态域可在类加载之后就访问到，但是其依赖于外界的实例，所以这里语义上有冲突。
		 * 
		 * 此外还有不可以在运行中生成final
		 * The field ab cannot be declared static in a non-static inner type, unless initialized with a constant expression
		  public static final String ab = new String("dsa");
		 */
		public static final String a = "aas";
		public int x = 2;
		public Inner(){
			System.out.println(StaticAttributeTest.this.out1);
			System.out.println(StaticAttributeTest.staticStr);
		}
	}
	
	public static class StaticInner{
		// 静态内部类
		public String a = "aas";
		public int x = 2;
		public static String staticStr = "dsad";
		public StaticInner(){
			x = new Random().nextInt();
			// 无法访问外部对象内容，本身也是属于外部类
			System.out.println(StaticAttributeTest.staticStr);
		}
	}
	
	public static void main(String[] args) {
		StaticAttributeTest x = new StaticAttributeTest();
		Inner innerX = x.new Inner();
		x.inner = innerX;
		System.out.println(innerX.a);
		System.out.println(x.inner.x);

		StaticInner staticInner = new StaticAttributeTest.StaticInner();
		StaticInner staticInner2 = new StaticAttributeTest.StaticInner();
		System.out.println(staticInner.x);
		System.out.println(staticInner2.x);
		staticInner2.a = "321dasdfa";
		System.out.println(staticInner.a);
		System.out.println(staticInner.staticStr);
	}
}
