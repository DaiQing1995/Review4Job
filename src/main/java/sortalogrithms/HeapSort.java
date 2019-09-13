package sortalogrithms;

public class HeapSort{

	private void swap(int[] data, int index1, int index2){
		int tmp = data[index1];
		data[index1] = data[index2];
		data[index2] = tmp;
	}

	public void buildHeap(int[] data, int index){
		int father = (index - 1) / 2;

		for (; father >= 0; father --){
			if (father * 2 + 1 == index){
				if (data[father] < data[index]){
					swap(data, father, index);
				}
			}else{
				if (data[father] < data[father * 2 + 1]){
					swap(data, father, father * 2 + 1);
				}
				if (data[father] < data[father * 2 + 2]){
					swap(data, father, father * 2 + 2);
				}
			}
		}
		swap(data, 0, index);
	}

	public int[] sort(int[] data){
		for (int i = data.length - 1; i > 0; i --){
			buildHeap(data, i);
		}
		return data;
	}

	public static void main(String[] args){
		int[] data = {2,4,2,5,7,8,9,43,12,5,76,52,621,865,15,642};
		for (int i = 0;i < data.length; ++ i)
			System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
		new HeapSort().sort(data);
		for (int i = 0;i < data.length; ++ i)
			System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
	}
}

