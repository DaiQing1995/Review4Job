package failfast;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class TestFailFast {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i + "");
		}
		Iterator<String> iterator = list.iterator();
		int i = 0;
		try {
			while (iterator.hasNext()) {
				if (i == 3) {
					// 非结构修改没问题
					// list.set(3,"2");
					// 结构上变化
					list.remove(3);
				}
				System.out.println(iterator.next());
				i++;
			}
			System.out.println("woo haha");
		} catch (ConcurrentModificationException e) {
			e.printStackTrace();
		}finally {
			System.out.println("ahh haha");
		}
	}
}
