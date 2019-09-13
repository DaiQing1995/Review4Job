package exception;

public class ReturnInFinally {
	
	public static int test() {
		int a = 0;
		try {
			a = 5 / 0;
			System.out.println("sadsadas");
			return -1;
		}catch (Exception e) {
			System.out.println("12321");
		}finally {
			return -2;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(test());
	}
}
