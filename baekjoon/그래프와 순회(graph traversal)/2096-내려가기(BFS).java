import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();

        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] move = {{1,-1},{1,0},{1,1}};
        int[][] graph = new int[n][3];
        boolean[][] visited = new boolean[n][3];

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            graph[i][0] = a;
            graph[i][1] = b;
            graph[i][2] = c;
        }
        int[][] minDP = new int[n][3];
        int[][] maxDP = new int[n][3];
        for (int[] arr : minDP) {
            Arrays.fill(arr, 100_000_000);
        }

        // bfs로 처리
        Deque<int[]> queue = new ArrayDeque<>();
        // int[0] = 목표 x, int[1] = 목표 y
        queue.add(new int[]{0,0});
        queue.add(new int[]{0,1});
        queue.add(new int[]{0,2});
        maxDP[0][0] = graph[0][0];
        maxDP[0][1] = graph[0][1];
        maxDP[0][2] = graph[0][2];
        minDP[0][0] = graph[0][0];
        minDP[0][1] = graph[0][1];
        minDP[0][2] = graph[0][2];

        while (!queue.isEmpty()) {
            int[] xys = queue.poll();
            int x = xys[0];
            int y = xys[1];

            for (int[] d : move) {
                int dx = x + d[0];
                int dy = y + d[1];
                if(dx >= n || dy < 0 || dy > 2) continue;

                maxDP[dx][dy] = Math.max(maxDP[dx][dy], maxDP[x][y] + graph[dx][dy]);
                minDP[dx][dy] = Math.min(minDP[dx][dy], minDP[x][y] + graph[dx][dy]);
                if(!visited[dx][dy]) {
                    queue.add(new int[]{dx, dy});
                    visited[dx][dy] = true;
                }
            }
        }

        int max = Math.max(Math.max(maxDP[n-1][0], maxDP[n-1][1]), maxDP[n-1][2]);
        int min = Math.min(Math.min(minDP[n-1][0], minDP[n-1][1]), minDP[n-1][2]);
        stringBuilder.append(max).append(" ").append(min);
        System.out.print(stringBuilder);

    }
}
