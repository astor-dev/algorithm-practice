import java.io.*;
import java.util.*;


public class Main {

    public static class Node {
        Node parent;
        int rank;

        Node(){
            this.parent = this;
            this.rank = 0;
        }

        public Node find() {
            if (parent != this) parent = parent.find();
            return parent;
        }

        public void union (Node target) {
            Node root = this.find();
            Node targetRoot = target.find();

            if (root == targetRoot) return;

            if (root.rank < targetRoot.rank) {
                root.parent = targetRoot;
            } else {
                targetRoot.parent = root;
                if(root.rank == targetRoot.rank) root.rank ++;
            }
        }

        public String isConnected(Node target) {
            return this.find() == target.find() ? "YES" : "NO";
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st. nextToken());
        Node[] graph = new Node[n+1];
        for (int i = 0; i <= n; i++) graph[i] = new Node();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            boolean isUnion = "0".equals(st.nextToken());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            if(isUnion) graph[a].union(graph[b]);
            else sb.append(graph[a].isConnected(graph[b])).append("\n");
        }
        System.out.print(sb);
    }
}