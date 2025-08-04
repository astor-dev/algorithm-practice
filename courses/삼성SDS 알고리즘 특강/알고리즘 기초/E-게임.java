import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static int[][] graph;
    public static int[][] dp;
    public static boolean[][] visited;
    public static int n;
    public static int m;
    public static boolean hasCycle = false;
    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        graph = new int[n][m];
        dp = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String row = bufferedReader.readLine();
            for (int j = 0; j < m; j++) {
                char node = row.charAt(j);
                if (node == 'H') {
                    graph[i][j] = -1;
                    continue;
                }
                graph[i][j] = node - '0';
            }
        }

        int answer = dfs(0, 0);
        System.out.print(hasCycle? -1 : answer);

    }

    public static int dfs(int x, int y) {
        if(hasCycle) return -1;
//        System.out.println("==========");
//        for(int[] dp1: dp) System.out.println(Arrays.toString(dp1));
//        System.out.println("==========");
        int weight = graph[x][y];
        for (int[] move : moves) {
            int dx = x + move[0] * weight;
            int dy = y + move[1] * weight;
            if(dx >= 0 && dx < n && dy >= 0 && dy < m && graph[dx][dy] != -1) {
                if (visited[dx][dy]) {
                    hasCycle = true;
                    return -1;
                }
                if(dp[dx][dy] > 0) {
                    dp[x][y] = Math.max(dp[dx][dy] + 1, dp[x][y]);
                    continue;
                }
                visited[dx][dy] = true;
                dp[x][y] = Math.max(dp[x][y], dfs(dx,dy) + 1);
                visited[dx][dy] = false;
            }
        }
        return dp[x][y] == 0 ? 1 : dp[x][y];
    }
}
