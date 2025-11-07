import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {

    static int[] parent;
    static int[] rank;
    static class Edge {
        int from;
        int to;
        int cost;
        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public String toString(){
            return this.from + ", " + this.to + " = " + this.cost;
        }
    }
    static List<Edge> graph;
    static int n;
    static int m;

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(bufferedReader.readLine());
        m = Integer.parseInt(bufferedReader.readLine());
        graph = new ArrayList<>();
        rank = new int[n+1];
        parent = new int[n+1];
        for(int i = 1; i <= n; i++) parent[i] = i;
        for(int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from =  Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());
            graph.add(new Edge(from, to, cost));
        }

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.cost));
        priorityQueue.addAll(graph);
        int sum = 0;
        while(!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if(!union(edge.from, edge.to)) continue;
            sum += edge.cost;
        }
        System.out.println(sum);
    }

    static boolean union(int x, int y){
        int parent1 = find(x);
        int parent2 = find(y);
        if (parent1 == parent2) return false;
        if(rank[parent1] > rank[parent2]) {
            parent[parent2] = parent1;
        } else {
            parent[parent1] = parent2;
            if(rank[parent1] == rank[parent2]) rank[parent2]++;
        }
        return true;
    }

    static int find(int node) {
        if(parent[node] != node) parent[node] = find(parent[node]);
        return parent[node];
    }


}
