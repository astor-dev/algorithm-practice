import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        final int from, to, weight;
        Edge(int from, int to, int weight) { this.from = from; this.to = to; this.weight = weight; }
    }

    // 벨만포드 1번 딸깍만 하면됨 static 필요 x
    // 그래프는 유향
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            edges.add(new Edge(
                    Integer.parseInt(stringTokenizer.nextToken()),
                    Integer.parseInt(stringTokenizer.nextToken()),
                    Integer.parseInt(stringTokenizer.nextToken())
            ));
        }

        // BellmanFord
        boolean hasCycle = false;
        long[] distances = new long[n+1];
        long INF = 100_000_000;
        Arrays.fill(distances, INF);
        distances[1] = 0;
        for(int i = 1; i <= n; i++) {
            for(Edge edge : edges) {
                if( distances[edge.from] != INF && distances[edge.from] + edge.weight < distances[edge.to]) {
                    distances[edge.to] = distances[edge.from] + edge.weight;
                    if(i==n) hasCycle = true;
                }
            }
        }

        if(hasCycle) {
            System.out.println(-1);
            return;
        }
        for(int i = 2; i <= n; i++) stringBuilder.append(distances[i] == INF ? -1 : distances[i]).append("\n");
        System.out.println(stringBuilder);
    }
}
