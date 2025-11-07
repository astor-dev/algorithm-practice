import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int target;
        int cost;
        Edge(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge target){
            return this.cost - target.cost;
        }
    }

    static List<List<Edge>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= N; i ++) graph.add(new ArrayList<>());
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), weight = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Edge(v, weight));
            graph.get(v).add(new Edge(u, weight));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken()), v2= Integer.parseInt(st.nextToken());

        int startToV1 = dijkstra(1)[v1];
        int StartToV2 = dijkstra(1)[v2];
        int v1ToV2 = dijkstra(v1)[v2];
        int v1ToEnd = dijkstra(v1)[N];
        int v2ToEnd = dijkstra(v2)[N];

        if((startToV1 == Integer.MAX_VALUE || v1ToV2 == Integer.MAX_VALUE || v2ToEnd == Integer.MAX_VALUE)
                && (StartToV2 == Integer.MAX_VALUE || v1ToV2 == Integer.MAX_VALUE || v1ToEnd == Integer.MAX_VALUE)) {
            System.out.print(-1);
            return;
        }
        int result = Math.min(startToV1 + v1ToV2 + v2ToEnd, StartToV2 + v1ToV2 + v1ToEnd);
        System.out.print(result);
    }
    static int[] dijkstra (int startNode) {
        int[] distanceGraph = new int[graph.size()+1];
        for(int i = 0; i <= graph.size(); i++) distanceGraph[i] = Integer.MAX_VALUE;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        distanceGraph[startNode] = 0;
        pq.add(new Edge(startNode, 0));
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            if(distanceGraph[edge.target] < edge.cost) continue;
            for (Edge adjEdge : graph.get(edge.target)) {
                int cost = adjEdge.cost + edge.cost;
                if(distanceGraph[adjEdge.target] > cost) {
                    distanceGraph[adjEdge.target] = cost;
                    pq.add(new Edge(adjEdge.target, cost));
                }
            }
        }
        return distanceGraph;
    }
}