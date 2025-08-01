import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {


    static boolean[][] visited;
    // x, y , d
    static int[][][][] dp;
    public static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int maxW = Integer.parseInt(stringTokenizer.nextToken());
        int maxH = Integer.parseInt(stringTokenizer.nextToken());
        dp = new int[maxW+1][maxH+1][2][2];
        // direction 0=w+free, 1=w+restricted / 2=h+free, 3=h+restricted
        int free = 0;
        int restricted = 1;
        int prevW = 0;
        int prevH = 1;
        for(int i = 1; i <= maxW; i++) dp[i][1][free][prevW] = 1;

        for(int i = 2; i <= maxH; i++) dp[1][i][free][prevH] = 1;
        for (int w = 2; w <= maxW; w++) {
            for (int h = 2; h <= maxH; h++) {
                // 가로 이동 처리
                if (w <= maxW) {
                    // 직진인 경우
                    dp[w][h][free][prevW] = (dp[w-1][h][free][prevW] + dp[w-1][h][restricted][prevW]) % 100000;
                    // 꺾는 경우
                    dp[w][h][restricted][prevW] = (dp[w-1][h][free][prevH]) % 100000;
                }
                // 세로 이동 처리
                if (h <= maxH) {
                    dp[w][h][free][prevH] = (dp[w][h-1][free][prevH] + dp[w][h-1][restricted][prevH]) % 100000;
                    dp[w][h][restricted][prevH] = dp[w][h-1][free][prevW] % 100000;
                }
            }
        }
        System.out.println((dp[maxW][maxH][free][prevH] + dp[maxW][maxH][free][prevW] + dp[maxW][maxH][restricted][prevH] + dp[maxW][maxH][restricted][prevW]) % 100000);
    }
}
