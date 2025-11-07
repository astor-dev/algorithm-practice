import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int from, to;
        Edge(int u, int v) { this.from = Math.min(u,v); this.to = Math.max(u,v); }

//        오늘의 교훈. TreeSet은 equals를 사용하지 않는다.
//        HashSet/Map만 equals와 hashCode()를 사용한다
//        @Override
//        public boolean equals(Object o) {
//            if(o == null || o.getClass() != getClass()) return false;
//            Edge target = (Edge) o;
//            return target.from == this.from && target.to == this.to;
//        }

        @Override
        public int compareTo(Edge target){
            return this.from != target.from ? this.from - target.from : this.to - target.to;
        }
    }

    static Set<Edge> articulationEdges;
    static int V, E;
    static List<List<Integer>> graph;
    static int[] lowLinkValue;
    static int[] reachIndex;
    static int index = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        V = Integer.parseInt(stringTokenizer.nextToken());
        E = Integer.parseInt(stringTokenizer.nextToken());
        articulationEdges = new TreeSet<>();
        lowLinkValue = new int[V+1];
        reachIndex = new int[V+1];
        graph = new ArrayList<>();
        for(int i = 0; i <= V; i++) graph.add(new ArrayList<>());
        for(int  i = 0; i < E; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = Integer.parseInt(stringTokenizer.nextToken()), v = Integer.parseInt(stringTokenizer.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dfs(1, 0);
        articulationEdges.forEach(articulationEdge ->stringBuilder.append(articulationEdge.from).append(" ").append(articulationEdge.to).append("\n"));
        System.out.println(articulationEdges.size());
        System.out.println(stringBuilder);

    }

    static void dfs(int currentNode, int parentNode) {
        index++;
        lowLinkValue[currentNode] = reachIndex[currentNode] = index;

        for(int adjacentNode : graph.get(currentNode)) {
            if(adjacentNode == parentNode) continue;
            if(reachIndex[adjacentNode] != 0 ) {
                lowLinkValue[currentNode] = Math.min(lowLinkValue[currentNode], reachIndex[adjacentNode]);
            } else {
                dfs(adjacentNode, currentNode);
                lowLinkValue[currentNode] = Math.min(lowLinkValue[currentNode], lowLinkValue[adjacentNode]);
                if(lowLinkValue[adjacentNode] > reachIndex[currentNode]) {
                    articulationEdges.add(new Edge(currentNode, adjacentNode));
                }
            }
        }
    }
}
