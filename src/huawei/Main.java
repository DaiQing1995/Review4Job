package huawei;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 2019年8月7日华为笔试第二题，根据表格顺序，从底向上建树，之后进行索引查找元素。
 * @author DaiQing
 */
public class Main {

	static int[] Labels;		// 对应边（名字，也可当节点，只是个表示的字符）
	static int[] HasChild;		// 是否有孩子
	static int[] Pounds;		// 是否为父亲下的第一个孩子，仅有 1/0 取值

	static int[] Values;

	static ArrayList<Node> leaves = new ArrayList<>();
	static ArrayList<Node> allNodes = new ArrayList<>();

	

	static LinkedList<Node> oneDeList = new LinkedList<>();
	static LinkedList<Node> anoDeList = new LinkedList<>();
	static LinkedList<Node> deList = oneDeList;
	static LinkedList<Node> anoList = anoDeList;
	
	
	static class Node {
		ArrayList<Node> child;
		Node parent;
		boolean isLeaf;
		int pos;
		int val;
		int pouds;
		int label;
		int layer;

		public Node(int pos, int label, int hasChild, int pouds) {
			child = new ArrayList<>();
			this.pos = pos;
			this.pouds = pouds;
			this.label = label;
			if (hasChild == 1) {
				this.isLeaf = false;
			} else {
				this.isLeaf = true;
				leaves.add(this);
			}
			allNodes.add(this);
		}

		public int hasLayerChild() {
			int i = 0;
			Node p = this;
			while (p.child.size() != 0) {
				i++;
				p = p.child.get(0);
			}
			return i;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		Labels = new int[M];
		HasChild = new int[M];
		Pounds = new int[M];
		for (int i = 0; i < M; ++i) {
			Labels[i] = sc.nextInt();
		}
		for (int i = 0; i < M; ++i) {
			HasChild[i] = sc.nextInt();
		}
		for (int i = 0; i < M; ++i) {
			Pounds[i] = sc.nextInt();
		}
		int N = sc.nextInt();
		Values = new int[N];
		for (int i = 0; i < N; ++i)
			Values[i] = sc.nextInt();

		for (int i = 0; i < M; ++i) {
			new Node(i, Labels[i], HasChild[i], Pounds[i]);
		}

		for (int i = 0; i < leaves.size(); ++i) {
			leaves.get(i).val = Values[i];
			leaves.get(i).layer = 0;
		}

		int eatLeaves = 0;
		for (int i = 0; i < allNodes.size(); ++i) {
			boolean eatLeaf = false;
			Node node = allNodes.get(i);
			if (!node.isLeaf) {
				int pdTimes = 0;
				while (!deList.isEmpty() && deList.peek().pouds != 0 && pdTimes == 0) {
					Node pop = deList.pop();
					if (pop.pouds == 1)
						pdTimes ++;
					pop.parent = node;
					node.child.add(pop);
					if (pop.isLeaf) {
						eatLeaf = true;
						eatLeaves ++;
					}
				}
				while (!deList.isEmpty() && deList.peek().pouds == 0 && node.hasLayerChild() > deList.peek().hasLayerChild()) {
					Node pop = deList.pop();
					pop.parent = node;
					node.child.add(pop);
					if (pop.isLeaf) {
						eatLeaf = true;
						eatLeaves ++;
					}
				}
			}
			chooseList(node, eatLeaf, eatLeaves);
		}

		int queries = sc.nextInt();
		int head = sc.nextInt();
		deList = deList.size() == 0 ? anoList : deList;
		ArrayList<Node> nodesArr = new ArrayList<>();
		for (int i = 0, n = deList.size();i < n; ++ i) {
			nodesArr.add(deList.pop());
		}
		Node root = null;
		for (int i = 0; i < nodesArr.size(); ++i) {
			if (nodesArr.get(i).label == head)
				root = nodesArr.get(i);
		}

		Node p = root;
		for (int i = 1; i < queries; ++i) {
			int q = sc.nextInt();
			for (int j = 0; j < p.child.size(); ++j) {
				if (p.child.get(j).label == q) {
					p = p.child.get(j);
					if (p.isLeaf) {
						System.out.println(p.val);
						sc.close();
						return;
					}
					break;
				}
			}
		}
		System.out.println(0);
		sc.close();
	}

	private static void chooseList(Node node, boolean eatLeaf, int eatLeaves) {
		if (eatLeaf) {
			anoDeList.add(node);
		}else if (node.isLeaf){
			oneDeList.add(node);
		}else {
			int childSum = node.hasLayerChild();
			if (!oneDeList.isEmpty() && !anoDeList.isEmpty()) {
				if (childSum == oneDeList.getFirst().hasLayerChild()) {
					oneDeList.add(node);
					deList = oneDeList;
					anoList = anoDeList;
				}else {
					anoDeList.add(node);
					deList = anoDeList;
					anoList = oneDeList;
				}
			}else if (!oneDeList.isEmpty()){
				handleEqualLayer(oneDeList, anoDeList,node);
			}else if (!anoDeList.isEmpty()){
				handleEqualLayer(anoDeList, oneDeList,node);
			}
		}
		if (eatLeaves == Values.length) {
			deList = anoDeList;
			anoList = oneDeList;
		}
	}



	private static void handleEqualLayer(LinkedList<Node> tmpList2, LinkedList<Node> emptyList, Node node) {
		if (tmpList2.getLast().hasLayerChild() == node.hasLayerChild())
			tmpList2.add(node);
		else
			emptyList.add(node);
	}
}
/**
 * 
input:
15
115 112 116 97 111 121 114 101 105 112 121 114 102 115 116
0   0   0   1   1   0   1   0   0   0   0   1   1   1   1
1   1   0   1   0   1   1   1   0   0   0   1   1   0   0
8
1 2 3 4 5 6 7 8
3
116 114 112
output:
7
 */
