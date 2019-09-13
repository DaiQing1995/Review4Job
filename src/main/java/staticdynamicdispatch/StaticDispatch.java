package staticdynamicdispatch;

/**
 * �ڱ�����ȷ���ľ�̬����
 * 
 * �÷��������൱�в������ط����������ߣ�������ֻͨ�������������������ľ�̬���͵��ã�
 * ���ﲻ�������������Ķ�̬��
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
		// Human Ϊ��̬���ͣ�WomanΪʵ������
		Human woman = new Woman();
		// Human Ϊ��̬���ͣ� ManΪʵ������
		Human man = new Man();
		StaticDispatch sdp = new StaticDispatch();
		// sdpΪ������ man��humanΪ��������
		sdp.say(man);
		sdp.say(woman);
		/*
		 * output:
		 * Human saying 
		 * Human saying
		 */
	}
}
