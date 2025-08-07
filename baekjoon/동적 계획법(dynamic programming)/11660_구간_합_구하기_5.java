import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer stringTokenizer;
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken()), M = Integer.parseInt(stringTokenizer.nextToken());

        int[][] prefixSum = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= N; j++) {
                prefixSum[i][j] = prefixSum[i][j-1] + Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x1 = Integer.parseInt(stringTokenizer.nextToken());
            int y1 = Integer.parseInt(stringTokenizer.nextToken());
            int x2
                    = Integer.parseInt(stringTokenizer.nextToken());
            int y2 = Integer.parseInt(stringTokenizer.nextToken());
            int answer = 0;
            for (int j = x2; j >= x1 ; j--) {
                answer += prefixSum[j][y2] - prefixSum[j][y1-1];
            }
            stringBuilder.append(answer).append("\n");
        }
        System.out.println(stringBuilder);

    }
}
