import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {

    // 엣지를 쫙 깔고
    // 엣지 전체를 노드수 -1 번 순회
    // 한번 더 순회해서 갱신되면 사이클 있는거임

    static class Edge {
        int from;
        int to;
        int weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    public static void main(String[] args) {
        int n = 12;
        int INF = 1_000_000_000;
        List<Edge> edges= new ArrayList<>();
        edges.add(new Edge(1,2,3));
        int[] distance = new int[n];
        Arrays.fill(distance, INF);
        int startNode = 0;
        distance[startNode] = 0;
        // 노드 수 만큼 반복(마지막 반복은 사이클탐지)
        for(int i = 0; i < n; i++) {
            boolean renewed = false;
            for(Edge e : edges) {
                if(distance[e.from] != INF && distance[e.to] > distance[e.from] + e.weight) {
                    distance[e.to] = distance[e.from] + e.weight;
                    renewed = true;
                }
            }
            if(i == n - 1 && renewed) {
                System.out.println("사이클!!");
                return;
            }
        }
    }
}