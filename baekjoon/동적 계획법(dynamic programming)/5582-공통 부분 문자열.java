import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String s1 = br.readLine();
        String s2 = br.readLine();

        int[][] dp = new int[s1.length()+1][s2.length()+1];
        int cs = 0;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                dp[i][j] = (s1.charAt(i-1) == s2.charAt(j-1)) ? dp[i-1][j-1] + 1 : 0;
                cs = Math.max(dp[i][j], cs);
            }
        }
        System.out.println(cs);
    }
}