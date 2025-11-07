import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] dp = new int[n+1][n+1];
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= i; j++) {
                int value = Integer.parseInt(stringTokenizer.nextToken());
                dp[i][j] = Math.max(dp[i-1][j] + value, dp[i-1][j-1] + value);
                if(i==n) answer = Math.max(answer, dp[i][j]);
            }
        }
        System.out.println(answer);
    }
}

