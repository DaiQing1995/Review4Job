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
			System.out.println("�ְ��ڳԷ�");
		}

		public void doSomething(Drink arg) {
			System.out.println("�ְ��ں�ˮ");
		}
		public void doSomething(Do arg) {
			System.out.println("�ְ���Do");
		}
	}

	private static class Child extends Father {
		public void doSomething(Eat arg) {
			System.out.println("�����ڳԷ�");
		}

		public void doSomething(Drink arg) {
			System.out.println("�����ں�ˮ");
		}

		public void doSomething(Do arg) {
			System.out.println("������Do");
		}
	}

	public static void main(String[] args) {
		Father father = new Father();
		Father child = new Child();
		father.doSomething(new Eat());
		child.doSomething(new Drink());
		// do1��̬������Do��dosomething��������StaticDispatch�е�����
		Do do1 = new Eat();
		father.doSomething(do1);
		/*
		 output:
		 �ְ��ڳԷ�
	 	 �����ں�ˮ
		 �ְ���Do
		 */
	}

}