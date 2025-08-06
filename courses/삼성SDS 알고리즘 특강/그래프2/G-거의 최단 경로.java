import java.io.*;
import java.util.*;

public class Main{
    /*
    목적: 다익스트라에 쓰인 엣지 찾기
    다익스트라를 돌리면서 해당 노드에 방문하기 까지 거친 엣지들을 저장하자
    그리고 그 엣지 빼고 다익스트라 한번 더
     */

    static class Edge {
        int target, weight;
        Edge (int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    static class PathNode {
        int u;
        List<PathNode> parents = new ArrayList<>();

        PathNode(int u) {
            this.u = u;
        }
    }

    static int INF = 1_000_000_000;
    static List<List<Edge>> adj;
    static PathNode[] path;
    static int[] distance;
    static int N;
    static int M;

    static int S;
    static int D;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();
        while(true) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            N = Integer.parseInt(stringTokenizer.nextToken());
            M = Integer.parseInt(stringTokenizer.nextToken());
            if (N == 0 && M == 0) break;
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            S = Integer.parseInt(stringTokenizer.nextToken());
            D = Integer.parseInt(stringTokenizer.nextToken());
            adj = new ArrayList<>();
            for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
            for (int i = 0; i < M; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int from = Integer.parseInt(stringTokenizer.nextToken());
                int to = Integer.parseInt(stringTokenizer.nextToken());
                int weight = Integer.parseInt(stringTokenizer.nextToken());
                adj.get(from).add(new Edge(to, weight));
            }
            dijkstra(true);
            Set<Long> edgesToRemove = new HashSet<>();
            Queue<PathNode> queue = new ArrayDeque<>();
            Set<Integer> visited = new HashSet<>();

            queue.add(path[D]);
            visited.add(D);

            while (!queue.isEmpty()) {
                PathNode current = queue.poll();
                for (PathNode parent : current.parents) {
                    int u = parent.u;
                    int v = current.u;
                    edgesToRemove.add((long)u * N + v);

                    if (!visited.contains(u)) {
                        visited.add(u);
                        queue.add(parent);
                    }
                }
            }
            for (long edgeValue : edgesToRemove) {
                int u = (int) (edgeValue / N);
                int v = (int) (edgeValue % N);
                adj.get(u).removeIf(edge -> edge.target == v);
            }

            int result = dijkstra(false);
            stringBuilder.append(result != INF ? result : -1).append("\n");
        }
        System.out.println(stringBuilder);
    }


    static int dijkstra(boolean shouldCountPath) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        // distance 및 parent 초기화
        // 0<= 노드 < N
        distance = new int[N];
        Arrays.fill(distance,INF);
        // 시작 노드 초기화
        distance[S] = 0;
        if(shouldCountPath) {
            path = new PathNode[N];
            for(int i = 0; i < N; i++) path[i] = new PathNode(i);
        }
        priorityQueue.add(new Edge(S, 0));
        while(!priorityQueue.isEmpty()) {
            Edge currentEdge = priorityQueue.poll();
            if (currentEdge.weight > distance[currentEdge.target]) continue;
            for (Edge adjacentEdge : adj.get(currentEdge.target)) {
                int newWeight = adjacentEdge.weight + distance[currentEdge.target];
                if(newWeight < distance[adjacentEdge.target]) {
                    // 갱신 시
                    Edge newEdge = new Edge(adjacentEdge.target, newWeight);
                    priorityQueue.add(newEdge);
                    if(shouldCountPath) {
                        path[adjacentEdge.target].parents.clear(); // 이전 부모 정보는 모두 무효화
                        path[adjacentEdge.target].parents.add(path[currentEdge.target]);
                    }
                    distance[adjacentEdge.target] = newWeight;
                } else if(shouldCountPath && newWeight == distance[adjacentEdge.target]) {
                    path[adjacentEdge.target].parents.add(path[currentEdge.target]);

                }
            }
        }
        return distance[D];
    }
}