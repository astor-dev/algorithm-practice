import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();
//        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int t = Integer.parseInt(bufferedReader.readLine());
        long[][] dp = new long[31][31];
        for (int i = 0; i <= 30; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for(int i = 1;  i<= 30; i++) {
            for(int j =1; j<= 30; j++) {
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }

        for(int i = 1; i <= t; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken()), m = Integer.parseInt(stringTokenizer.nextToken());
            stringBuilder.append(dp[m][n]).append("\n");
        }
        System.out.println(stringBuilder);
    }
}
// 파스칼 삼각형 풀이 104ms 14292KB
// BigInteger 사용 풀이 128ms 14780KB
//import java.io.*;
//        import java.math.BigInteger;
//import java.util.*;
//
//
//public class main.java.Main {
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer stringTokenizer;
//        StringBuilder stringBuilder = new StringBuilder();
////        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
//
//        int t = Integer.parseInt(bufferedReader.readLine());
//        BigInteger fact[] = new BigInteger[31];
//        fact[0] = BigInteger.ONE;
//        for(int i = 1;  i<= 30; i++) {
//            fact[i] = fact[i-1].multiply(BigInteger.valueOf(i));
//        }
//
//        for(int i = 1; i <= t; i++) {
//            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
//            int n = Integer.parseInt(stringTokenizer.nextToken()), m = Integer.parseInt(stringTokenizer.nextToken());
//            stringBuilder.append(fact[m].divide(fact[n].multiply(fact[m-n]))).append("\n");
//        }
//        System.out.println(stringBuilder);
//    }
//}
