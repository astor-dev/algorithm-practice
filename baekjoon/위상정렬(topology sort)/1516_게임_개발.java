import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();

    /*
    DP + 위상정렬
     */
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] buildTime = new int[n+1];
        int[] inDegree = new int[n+1];
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        List<List<Integer>> adj = new ArrayList<>();
        List<List<Integer>> parent = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
            parent.add(new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            buildTime[i] = Integer.parseInt(stringTokenizer.nextToken());
            while(stringTokenizer.hasMoreTokens()) {
                int num = Integer.parseInt(stringTokenizer.nextToken());
                if (num == -1) break;
                adj.get(num).add(i);
                parent.get(i).add(num);
                inDegree[i]++;
            }
        }


        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()){
            int node = queue.poll();
            int maxParentBuildTime = 0;
            for(int parentNode : parent.get(node)) {
                maxParentBuildTime = Math.max(maxParentBuildTime, dp[parentNode]);
            }
            dp[node] = Math.min(maxParentBuildTime + buildTime[node], dp[node]);
            for(int adjNode : adj.get(node)) {
                inDegree[adjNode]--;
                if(inDegree[adjNode] == 0) {
                    queue.add(adjNode);
                }
            }
        }
        for(int i = 1; i<=n; i++) {
            stringBuilder.append(dp[i]).append("\n");
        }
        System.out.println(stringBuilder);
    }
}
