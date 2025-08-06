import java.io.*;
import java.util.*;

public class Main{

    /*
    벨만포드고, 30 x 30 그래프
    Edge를 만약 수기로 만든다고 치면, MaxV = 900, MaxE ~= 3600. 천언저리라 벨만포드로 수용가능함
    그래프 그리고 dx 하는 식으로 그냥 하면 될 듯
     */
    static class Edge {
        final int fromX, fromY, toX, toY, weight;
        Edge(int fromX, int fromY, int toX, int toY, int weight) {
            this.fromX = fromX; this.fromY = fromY;
            this.toX = toX; this.toY = toY;
            this.weight = weight;
        }
    }

    static int[][] moves = {{1,0},{-1,0},{0,1},{0,-1}};
    static int W;
    static int H;
    static int G;
    static int E;
    static long[][] distance;
    static List<Edge> edges;
    static long INF = 100_000_000_000L;
    static long CANNOT_ACCESS = 200_000_000_000L;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();
        while(true) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            W = Integer.parseInt(stringTokenizer.nextToken());
            H = Integer.parseInt(stringTokenizer.nextToken());
            distance = new long[W][H];
            for(int i = 0; i < W; i++) Arrays.fill(distance[i], INF);
            if(W == 0 && H == 0)  break;
            G = Integer.parseInt(bufferedReader.readLine());
            edges = new ArrayList<>();
            List<int[]> cannotReach = new ArrayList<>();
            List<int[]> cannotMove = new ArrayList<>();

            // 묘비
            for(int i = 0; i < G; i++){
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                int y = Integer.parseInt(stringTokenizer.nextToken());
                cannotReach.add(new int[]{x,y});
            }
            E = Integer.parseInt(bufferedReader.readLine());
            // 귀신구멍
            for(int i = 0; i < E; i++){
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int x1 = Integer.parseInt(stringTokenizer.nextToken());
                int y1 = Integer.parseInt(stringTokenizer.nextToken());
                int x2 = Integer.parseInt(stringTokenizer.nextToken());
                int y2 = Integer.parseInt(stringTokenizer.nextToken());
                int t = Integer.parseInt(stringTokenizer.nextToken());
                cannotMove.add(new int[]{x1,y1});
                edges.add(new Edge(x1, y1, x2, y2, t));
            }
            // 나머지 엣지
            for (int x = 0; x < W; x++) {
                for(int y = 0; y < H; y++) {
                    final int fx = x;
                    final int fy = y;
                    // 람다 땜시;
                    if (cannotMove.stream().anyMatch(xy -> xy[0] == fx && xy[1] == fy)) continue;;
                    for(int[] move : moves) {
                        int dx = x+move[0];
                        int dy = y+move[1];
                        if(dx < 0 || dx >= W || dy < 0 || dy >= H) continue;
                        if(cannotReach.stream().anyMatch(xy -> xy[0] == dx && xy[1] == dy)) continue;

                        edges.add(new Edge(x,y,dx,dy,1));
                    }
                }
            }

            // 벨만 포드
            distance[0][0] = 0;
            boolean hasCycle = false;
            // 노드 수 = W*H. 노드 수만큼 반복
            for(int i = 1; i <= W*H; i++) {
                for(Edge edge : edges) {
                    if(distance[edge.fromX][edge.fromY] == INF) continue;
                    if(edge.fromX == W-1 && edge.fromY == H-1) continue;
                    if(distance[edge.toX][edge.toY] > edge.weight + distance[edge.fromX][edge.fromY]) {
                        distance[edge.toX][edge.toY] = edge.weight + distance[edge.fromX][edge.fromY];
                        if(i == W*H) hasCycle = true;
                    }
                }
            }
            if(hasCycle) stringBuilder.append("Never").append("\n");
            else if(distance[W-1][H-1] == INF) stringBuilder.append("Impossible").append("\n");
            else stringBuilder.append(distance[W-1][H-1]).append("\n");
        }


        System.out.println(stringBuilder);
    }
}
