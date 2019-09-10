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
		//ֻ����final ��static, ���ڶ���ⲿʵ���������������ľ�̬Ԫ�أ����޷���֤�����ڲ������static��һ��
		/*
		 * 1.���û��final��������˸þ�̬����������֮��ͷ��ʵ�������������������ʵ�������������������г�ͻ��
		 * 
		 * ���⻹�в�����������������final
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
		// ��̬�ڲ���
		public String a = "aas";
		public int x = 2;
		public static String staticStr = "dsad";
		public StaticInner(){
			x = new Random().nextInt();
			// �޷������ⲿ�������ݣ�����Ҳ�������ⲿ��
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
