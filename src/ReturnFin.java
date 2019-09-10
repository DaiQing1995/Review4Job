
public class ReturnFin {

	
	public static int tryMethodTest() {
		int a = 1;
		try {
			a = a / 0;
		}catch (Exception e) {
			a = 100;
			a = 1;
			return a;
		} finally {
			a = 1000;
			return a;
		}
	}
	
	static int[] data = new int[310];
	
	private static void generate() {
		data[1] = 4;
		data[2] = 4;
		data[3] = 6;
		data[4] = 6;
		int iIndex = 6;
		int jIndex = 0;
		for (int i = 4; i < 500; i += 4) {
			data[i] = iIndex + jIndex;
			iIndex -= (i / 2 - 1);
			jIndex += 2;
			
			iIndex -= 1;
			jIndex += 2;
			
			iIndex += 2;
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(tryMethodTest());
		
		
		
		
	}
}
