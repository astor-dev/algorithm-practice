import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer stringTokenizer;
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken()), M = Integer.parseInt(stringTokenizer.nextToken());

        int[] prefixSum = new int[N+1];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i-1] + Integer.parseInt(stringTokenizer.nextToken());
        }
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            stringBuilder.append(
                    - prefixSum[Integer.parseInt(stringTokenizer.nextToken())-1] + prefixSum[Integer.parseInt(stringTokenizer.nextToken())]
            ).append("\n");
        }
        System.out.println(stringBuilder);

    }
}