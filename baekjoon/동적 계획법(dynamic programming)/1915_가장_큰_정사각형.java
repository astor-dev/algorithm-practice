import java.io.*;
import java.util.*;

public class Main{

    /*
    그냥 관찰력이 중요했던 문제
    점화식이 안떠오르면, 직접 그려가면서 현상적으로 특징을 추려가자
     */
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken()), m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] dp =  new int[n+1][m+1];
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            String string = bufferedReader.readLine();
            for (int j = 1; j <= m; j++) {
                int number = string.charAt(j-1) - '0';
                if (number == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                int left = dp[i][j-1];
                int up = dp[i-1][j];
                int upLeft = dp[i-1][j-1];
                dp[i][j] = Math.min(Math.min(left, up), upLeft) + 1;
                answer = Math.max(dp[i][j], answer);
            }
        }
        System.out.println(answer*answer);

    }
}
