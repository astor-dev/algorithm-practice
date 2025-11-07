import java.io.*;
import java.util.*;


public class Main {
    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int weight;
        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge target) {
            return this.weight - target.weight;
        }
    }

    public static int[] rank;
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
        rank = new int[V+1];
        parent = new int[V+1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i = 1; i <= V; i++) parent[i] = i;
        while(E-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), weight = Integer.parseInt(st.nextToken());
            Edge edge = new Edge(u,v,weight);
            pq.add(edge);
        }
        int totalWeight = 0;
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            boolean noCycle = union(edge.u, edge.v);
            if(noCycle) {
                totalWeight += edge.weight;
            }
        }
        System.out.print(String.valueOf(totalWeight));
    }

    static int find(int node){
        if(parent[node] != node) parent[node] = find(parent[node]);
        return parent[node];
    }

    static boolean union(int node1, int node2){
        int parent1 = find(node1), parent2 = find(node2);
        if(parent1 != parent2) {
            if (rank[parent1] > rank[parent2]) {
                // 랭크 낮은 쪽을 높은 쪽에 합침
                parent[parent2] = parent1;
            } else {
                parent[parent1] = parent2;
                if (rank[parent1] == rank[parent2]) {
                    rank[parent2]++;
                }
            }
            return true;
        }
        return false;
    }
}