package bishi.kuaishou;

/*作者：檐之
链接：https://www.nowcoder.com/discuss/233371?toCommentId=3676654
来源：牛客网
第四题 树的好序列
这道题区分度大一点，大概就是总数减去纯红色连通域的个数，再就是记得取模
4 4
1 2 1
2 3 1
3 4 1
*/

 
import java.util.*;
 
 
class Node {
    int label;
    List<Node> neighbors;
    List<Boolean> edgeColors;
 
    public static final Boolean RED = false;
    public static final Boolean BLACK = true;
 
    public Node(int label) {
        this.label = label;
        neighbors = new ArrayList<>();
        edgeColors = new ArrayList<>();
    }
 
    public static Boolean parseColor(int color) {
        return color == 1 ? BLACK : RED;
    }
}
 
public class MainTiMu3 {
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        Node[] graph = new Node[n + 1];
        for (int i = 0; i < (n - 1); i++) {
            int e1 = scanner.nextInt();
            int e2 = scanner.nextInt();
            int color = scanner.nextInt();
            if (graph[e1] == null) {
                graph[e1] = new Node(e1);
            }
            if (graph[e2] == null) {
                graph[e2] = new Node(e2);
            }
            graph[e1].neighbors.add(graph[e2]);
            graph[e1].edgeColors.add(Node.parseColor(color));
            graph[e2].neighbors.add(graph[e1]);
            graph[e2].edgeColors.add(Node.parseColor(color));
        }
        System.out.println(helper(graph, k));
    }
 
    private static final int MOD = 1000000007;
 
    private static int helper(Node[] graph, int k) {
        int n = graph.length - 1;
        long total = pow(n, k);
        List<Integer> setList = new ArrayList<>();
        Set<Node> visited = new HashSet<>();
        Queue<Node> q = new LinkedList<>();
        for (int i = 1; i < graph.length; i++) {
            if (visited.contains(graph[i])) {
                continue;
            }
            Set<Node> redSet = new HashSet<>();
            redSet.add(graph[i]);
            visited.add(graph[i]);
            q.offer(graph[i]);
            while (!q.isEmpty()) {
                Node node = q.poll();
                for (int j = 0; j < node.neighbors.size(); j++) {
                    if (node.edgeColors.get(j) == Node.RED && !visited.contains(node.neighbors.get(j))) {
                        Node next = node.neighbors.get(j);
                        q.offer(next);
                        redSet.add(next);
                        visited.add(next);
                    }
                }
            }
            setList.add(redSet.size());
        }
        for (Integer size : setList) {
            total = (total + MOD - pow(size, k)) % MOD;
        }
        return (int) total;
    }
 
    private static long pow(int n, int k) {
        long total = 1;
        for (int i = 0; i < k; i++) {
            total = (total * n) % MOD;
        }
        return total;
    }
}