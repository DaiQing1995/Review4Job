package basicdata.box;

public class BasicData {
	public static void main(String[] args) {
		Integer a1 = new Integer(1);
		Integer a2 = a1;
		a1 ++;
		System.out.println(a1);
		System.out.println(a2);
		Character c1 = new Character('a');
		Character c2 = c1;
		c1 = 'b';
		System.out.println(c1);
		System.out.println(c2);
		Long long1 = new Long(1000l);
		Long long2 = long1;
		long1 ++;
		System.out.println(long1);
		System.out.println(long2);
	}
}
