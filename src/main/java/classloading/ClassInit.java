package classloading;

/**
 * ��ļ̳й�ϵ���۲�<clinit>ʲôʱ��ִ��
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
		 * ��֤3��final�ֶ���׼���׶α����볣���أ�ͬ�������ʼ��SubClass
		 * �����
		 * �����
		 */
		private static final String FIN_FUNC = "I can not trigger initialization";
		
		public SubClass() {
			System.out.println("SubClass Instance()");
		}
	}

	public static void main(String[] args) {
		/**
		 * ��֤ 1: ������ᱣ֤������<init>()����ִ��֮ǰ�������<clinit>()���������Ѿ�ִ����� 
		 * �����
		 * SSClass 
		 * SuperClass init!
		 * 123
		 *
		 * ��û�д�������ı�����ȡ�Ȳ���ǰ�����಻������ʼ��״̬��
		 */
		System.out.println(SubClass.val);
		
		
		/**
		 * ��֤2���������鲻�ᴥ����ʼ��
		 * �����
		 * �����
		 */
		SubClass[] subClasses = new SubClass[5];
		
		/**
		 * ��֤3���䣺������Ȼ���ᴥ����ʼ��
		 */
		System.out.println(SubClass.FIN_FUNC);
	}

}
