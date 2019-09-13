package bishi.frequent;


/**
 * �ڲ������㷨֮������ Ĭ�ϰ��մ�С��������������
 * 
 * @author С��
 * @�������� 2015-3-24
 */
public class HeapSort {
	public static void main(String[] args) {
		// ��Ҫ�������������
		int[] array = new int[] { 8, 3, 2, 1, 7, 12, 53, 62, 123, 64, 83, 28, 95, 26, 46, 4, 6, 5 };
		// ���ԭ���������
		printResult(array);
		// ���ж��������
		for (int i = array.length - 1; i > 0; i--) {
			// ����n-1�ν��󶥶ѣ�ÿ�ν��ѣ�������С��ֵ�ŵ���λ������
			// ͬʱ��ÿ�ν��ѵĹ�����ѡ������ֵ��Ϊ��
			// �����󶥶ѵĹ���Ҳ�Ǵ�����ȫ�������Ĺ���
			buildMaxHeap(array, i);
		}

		// �����������ؽ��
		printResult(array);
	}

	/**
	 * �����󶥶ѵĹ���
	 * 
	 * @param array
	 * @param i
	 */
	private static void buildMaxHeap(int[] array, int i) {
		// ��Ҷ�ӽڵ�ĵ�һ�����ڵ㿪ʼѭ��
		for (int j = (i - 1) / 2; j >= 0; j--) {
			// ���һ���ڵ㲢�������ֻ��������
			if ((2 * j + 1 == i) && (i % 2 != 0)) {
				if (array[j] < array[2 * j + 1])
					swap(array, j, 2 * j + 1);
			} else {
				if (array[j] < array[2 * j + 1])
					swap(array, j, 2 * j + 1);
				if (array[j] < array[2 * j + 2])
					swap(array, j, 2 * j + 2);
			}
		}
		swap(array, 0, i);
	}

	/**
	 * �����Ӧ����Ľ��
	 * 
	 * @param array
	 */
	private static void printResult(int[] array) {
		for (int value : array)
			System.out.print(" " + value + " ");
		System.out.println();
	}

	/**
	 * ��������������������ֵ
	 * 
	 * @param array
	 * @param i
	 * @param j
	 */
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}