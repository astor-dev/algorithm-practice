import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int from;
        int to;
        int distance;
        Edge(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int v = Integer.parseInt(br.readLine());
        List<Edge> edges = new ArrayList<>();

        // bellmanford
        int startNode = 1;
        int INF = 100_000_000;
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[startNode] = 0;
        boolean hasCycle = false;
        // NOTE: v-1 회 반복!!
        for (int i = 1; i <= v; i++) {
            for(Edge edge: edges) {
                int newDist = dist[edge.from] + edge.distance;
                if(dist[edge.from] != INF && dist[edge.to] > newDist) {
                    dist[edge.to] = newDist;
                    // 1회 더 실행 시 갱신이 발생하는 경우 사이클 존재
                    if(i==v) {
                        hasCycle = true;
                    }
                }

            }

        }
    }
}
