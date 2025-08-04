import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[n+3];
        int cur = 1;

        dp[1] = 1;
        dp[2] = 1;
        while(cur <= n-2) {
            dp[cur+2] = dp[cur] + dp[cur+1];
            cur++;
        }
        System.out.print(dp[n]);
    }
}
