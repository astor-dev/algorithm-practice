import java.io.*;
import java.util.*;


public class Main {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();
    /*
    문제는 유향그래프에서, 특정 노드까지 도달 가능한 다른 노드 수 + 노드가 도달할 수 있는 다른 노드 수 = 자신을 제외한 전체 노드 수인 수를 셈
     */
    static boolean[][] reachable;
    public static void main(String[] args) throws Exception {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m =Integer.parseInt(stringTokenizer.nextToken());
        reachable = new boolean[n+1][n+1];
        for (int i = 1; i <= n; i++) reachable[i][i] =true;
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int child = Integer.parseInt(stringTokenizer.nextToken());
            int parent = Integer.parseInt(stringTokenizer.nextToken());
            reachable[child][parent] = true;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    reachable[i][j] = (reachable[i][k] && reachable[k][j] || reachable[i][j]);
                }
            }
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
//            System.out.println(Arrays.toString(reachable[i]));
            int tallerCount = 0;
            int shorterCount = 0;
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                boolean isShorter = reachable[j][i];
                boolean isTaller = reachable[i][j];
                if(isShorter) shorterCount++;
                if(isTaller) tallerCount++;
            }
            if(tallerCount+shorterCount == n-1) {
                count++;
            }
        }
        System.out.println(count);
    }
}