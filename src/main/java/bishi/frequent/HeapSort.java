package bishi.frequent;


/**
 * 内部排序算法之堆排序 默认按照从小到大进行排序操作
 * 
 * @author 小浩
 * @创建日期 2015-3-24
 */
public class HeapSort {
	public static void main(String[] args) {
		// 需要进行排序的数组
		int[] array = new int[] { 8, 3, 2, 1, 7, 12, 53, 62, 123, 64, 83, 28, 95, 26, 46, 4, 6, 5 };
		// 输出原数组的内容
		printResult(array);
		// 进行堆排序操作
		for (int i = array.length - 1; i > 0; i--) {
			// 进行n-1次建大顶堆，每次建堆，都把最小的值放到根位置上面
			// 同时在每次建堆的过程中选出最大的值作为根
			// 创建大顶堆的过程也是创建完全二叉树的过程
			buildMaxHeap(array, i);
		}

		// 输出排序后的相关结果
		printResult(array);
	}

	/**
	 * 建立大顶堆的过程
	 * 
	 * @param array
	 * @param i
	 */
	private static void buildMaxHeap(int[] array, int i) {
		// 从叶子节点的第一个父节点开始循环
		for (int j = (i - 1) / 2; j >= 0; j--) {
			// 最后一个节点并且这棵树只有左子树
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
	 * 输出相应数组的结果
	 * 
	 * @param array
	 */
	private static void printResult(int[] array) {
		for (int value : array)
			System.out.print(" " + value + " ");
		System.out.println();
	}

	/**
	 * 交换数组中两个变量的值
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