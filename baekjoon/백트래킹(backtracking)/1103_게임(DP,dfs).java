import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken()), m = Integer.parseInt(stringTokenizer.nextToken());

        int[][] graph = new int[n][m];
        int[][] dp = new int[n][m];
        boolean[][] visited = new boolean[n][m];
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

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        // note: 마지막에 죽는 움직임에서 기본적으로 +1 되는데 시작값으로 더해줌
        queue.add(new int[]{0, 0, 1});
        int answer = 1;
        int lastPoppedTime = 0;
        ArrayList<ArrayList<int[]>> visiting = new ArrayList<>();
        
        // idx 0, 1 미리 만듦
        visiting.add(new ArrayList<>());
        visiting.add(new ArrayList<>());
        while (!queue.isEmpty()) {
            // [0]: x, [1]: y, [2]: time
            int[] xyt = queue.pop();
//            System.out.println("xyt = " + Arrays.toString(xyt));
            // 시간이 같거나 준다면 경로 탐색 후 백트래킹한 것.
            if(xyt[2] <= lastPoppedTime) {
                for(int i = xyt[2]; i <= lastPoppedTime; i++){
                    for(int[] xy : visiting.get(i)){
//                        System.out.println("release" + xy[0] + "," + xy[1]);
                        visited[xy[0]][xy[1]] = false;
                    }
                }
            }
            visited[xyt[0]][xyt[1]] = true;
            visiting.get(xyt[2]).add(new int[]{xyt[0], xyt[1]});
            lastPoppedTime = xyt[2];
            int weight = graph[xyt[0]][xyt[1]];
            for (int[] move : moves) {
                int dx = xyt[0] + move[0] * weight;
                int dy = xyt[1] + move[1] * weight;
                if(dx < 0 || dx >= n || dy < 0 || dy >= m) continue;
                if(graph[dx][dy] == -1) continue;
                if(visited[dx][dy]) {
                    System.out.print(-1);
                    return;
                };
                int diff = xyt[2] + 1 -  dp[dx][dy];
                if(diff > 0) {
                    int answerDiff = xyt[2]+1 - answer;
                    if(answerDiff > 0) {
                        answer = xyt[2]+1;
                        visiting.add(new ArrayList<>());
                    }
                    queue.push(new int[]{dx,dy,xyt[2]+1});
                    dp[dx][dy] = xyt[2]+1;
//                    System.out.println("visited: " + dx + "," + dy);
                }
            }
        }
        System.out.print(answer);
    }
}
