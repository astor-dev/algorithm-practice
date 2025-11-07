import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    static class Edge {
        int target;
        int weight;
        Edge(int target, int weight) {
            this.target = target;
            this.weight =weight;
        }
    }
    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int v = Integer.parseInt(stringTokenizer.nextToken()), e = Integer.parseInt(stringTokenizer.nextToken());
        int startNode = Integer.parseInt(bufferedReader.readLine());
        List<List<Edge>> adjEdges = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            adjEdges.add(new ArrayList<>());
        }


        for (int i = 1; i <= e; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            int weight = Integer.parseInt(stringTokenizer.nextToken());
            adjEdges.get(from).add(new Edge(to, weight));
        }

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        int[] distance = new int[v+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        priorityQueue.add(new Edge(startNode, 0));
        distance[startNode] = 0;
        while(!priorityQueue.isEmpty()) {
            Edge current = priorityQueue.poll();
            if(distance[current.target] < current.weight) continue;
            for( Edge adj : adjEdges.get(current.target) ) {
                int adjNewCost = distance[current.target] + adj.weight;
                if(adjNewCost < distance[adj.target]) {
                    priorityQueue.add(new Edge(adj.target, adjNewCost));
                    distance[adj.target] = adjNewCost;
                }
            }
        }
        for (int i = 1; i <= v; i++) {
            if(distance[i] == Integer.MAX_VALUE) {
                stringBuilder.append("INF").append("\n");
                continue;
            }
            stringBuilder.append(distance[i]).append("\n");
        }

        System.out.println(stringBuilder);
    }
}
