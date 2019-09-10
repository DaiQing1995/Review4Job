import java.util.HashMap;
import java.util.Vector;

public class Test {
	
	public static void StrAndHashCodeTest() {
		String s = "OK";
		StringBuilder sb = new StringBuilder(s);
		System.out.println(s.hashCode() + " " + sb.hashCode());
		
		String t = new String("OK");
		StringBuilder tb = new StringBuilder(s);
		System.out.println(t.hashCode() + " " + tb.hashCode());
		System.out.println(System.identityHashCode(s) + " " + System.identityHashCode(s));
		System.out.println(s == t);
		System.out.println(s.equals(t));
		
		String tt = new String("OK");
		System.out.println(t.equals(tt));
		System.out.println(t == tt);
	}
	
	
	public static class TestNode{
		int a;
		
		public TestNode(int val) {
			this.a = val;
		}
		
//		@Override
//		public boolean equals(Object o) {
//			TestNode x = (TestNode) o;
//			if (x.a == this.a) {
//				return true;
//			}else {
//				return false;
//			}
//		}
//		
		@Override
		public int hashCode() {
			return 1;
		}
	}
	
	public static void main(String[] args) {
		LearnHashMap<TestNode, Integer> testHash = new LearnHashMap<>();
		for (int i = 0;i < 25; ++ i) {
			System.out.println(i + 1);
			if (i == 8) {
				System.out.println("cp");
			}
			testHash.put(new TestNode(i), 1);
		}
		System.out.println(testHash.size());
		Vector<Integer> vec = new Vector<>();
	}
}
