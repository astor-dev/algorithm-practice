import java.io.*;
import java.util.*;


public class Main {

    static class Node implements Comparable<Node> {
        int value;
        int order;
        boolean visited;
        PriorityQueue<Node> edge;

        Node(int value) {
            this.value = value;
            this.visited = false;
            this.edge = new PriorityQueue<>();
            this.order = 0;
        }

        @Override
        public int compareTo(Node node){
            return this.value - node.value;
        }
    }

    static int N; // 정점의 수
    static int M; // 간선의 수
    static int R; // 시작 정점
    static Node[] graph;

    static Deque<Node> queue = new ArrayDeque<Node>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));


    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        graph = new Node[N+1];
        for(int i = 1; i <= N; i++) graph[i] = new Node(i);


        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
            graph[u].edge.add(graph[v]);
            graph[v].edge.add(graph[u]);
        }
        bfs(R);
        for (int i = 1; i <= N; i++){
            bw.write(String.valueOf(graph[i].order));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    static void bfs(int startNode) throws IOException {
        int order = 1;
        queue.add(graph[startNode]);
        graph[startNode].order = order++;
        graph[startNode].visited = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            while (!node.edge.isEmpty()) {
                Node neighbor = node.edge.poll();
                if (!neighbor.visited) {
                    neighbor.visited = true;
                    neighbor.order = order++;
                    queue.add(neighbor);
                }
            }
        }
    }
}