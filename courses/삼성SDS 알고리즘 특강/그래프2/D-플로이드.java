
import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();
    static int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        int v = Integer.parseInt(bufferedReader.readLine()), e = Integer.parseInt(bufferedReader.readLine());
        int[][] graph = new int[v+1][v+1];
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++){
                if (i == j) graph[i][j] = 0;
                else graph[i][j] = INF;
            }
        }
        for (int i = 1; i <= e; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            int weight = Integer.parseInt(stringTokenizer.nextToken());
            graph[from][to] = Math.min(weight, graph[from][to]);
        }

        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v ; i++) {
                for (int j = 1; j <= v; j++) {
                    graph[i][j] = Math.min(graph[i][j],graph[i][k] + graph[k][j]);
                }
            }
        }
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++){
                if(graph[i][j] == INF) stringBuilder.append(0).append(" ");
                else stringBuilder.append(graph[i][j]).append(" ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
