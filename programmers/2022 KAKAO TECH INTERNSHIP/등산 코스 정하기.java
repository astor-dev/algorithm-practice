import java.util.*;

class Solution {

    static int INF = 100_000_000;
    static class Edge {
        int to;
        int weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<List<Edge>> adjGraph = new ArrayList<>();
        for(int i = 0; i <= n; i++) adjGraph.add(new ArrayList<>());
        Set<Integer> gateSet = new HashSet<>();
        Set<Integer> summitSet = new HashSet<>();

        for(int g : gates) gateSet.add(g);
        for(int s : summits) summitSet.add(s);
        for(int[] path: paths) {
            boolean is0Gate = gateSet.contains(path[0]);
            boolean is1Gate = gateSet.contains(path[1]);
            boolean is0Summit = summitSet.contains(path[0]);
            boolean is1Summit = summitSet.contains(path[1]);
            if(!is0Summit && !is1Gate) {
                adjGraph.get(path[0]).add(new Edge(path[1],path[2]));
            }
            if(!is1Summit && !is0Gate) {
                adjGraph.get(path[1]).add(new Edge(path[0],path[2]));
            }
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(i -> i.weight));
        int[] intensity = new int[n+1];
        Arrays.fill(intensity, INF);
        for(int gate: gates) {
            intensity[gate] = 0;
            pq.add(new Edge(gate, 0));
        }
        // pq의 edge의 weight는 입력 당시 해당 정점(to) 까지의 intensity
        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            if(intensity[e.to] < e.weight) continue;
            for (Edge adj : adjGraph.get(e.to)) {
                int newIntensity = Math.max(intensity[e.to], adj.weight);
                if(newIntensity < intensity[adj.to]) {
                    pq.add(new Edge(adj.to, newIntensity));
                    intensity[adj.to] = newIntensity;
                }
            }
        }
        int[] answer = {INF, INF};
        for(int summit: summits) {
            if(answer[1] > intensity[summit]) {
                answer = new int[]{summit, intensity[summit]};
            } else if (answer[1] == intensity[summit] && answer[0] > summit) {
                answer = new int[]{summit, intensity[summit]};
            }

        }
        return answer;
    }
}