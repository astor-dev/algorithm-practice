import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int N;
    static int M;

    static long[] prefixSumA;
    static long[] prefixSumB;
    public static void main (String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bufferedReader.readLine());
        N = Integer.parseInt(bufferedReader.readLine());

        prefixSumA = new long[N];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        prefixSumA[0] = Integer.parseInt(stringTokenizer.nextToken());
        for (int i = 1 ; i < N; i++) {
            prefixSumA[i] = Integer.parseInt(stringTokenizer.nextToken()) + prefixSumA[i-1];
        }

        M = Integer.parseInt(bufferedReader.readLine());
        prefixSumB = new long[M];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        prefixSumB[0] = Integer.parseInt(stringTokenizer.nextToken());
        for (int i = 1 ; i < M; i++) {
            prefixSumB[i] = Integer.parseInt(stringTokenizer.nextToken()) + prefixSumB[i-1];
        }



        // B는 부배열의 합을 구해놓음

        long[] bSum = new long[M * (M+1) / 2];
        int index = 0;
        for ( int i = 0; i < M; i++) {
            for (int j = i; j < M; j++) {
                bSum[index++] =  prefixSumB[j] - (i==0 ? 0 : prefixSumB[i-1]);
            }
        }

        Arrays.sort(bSum);
        long answer = 0;
        for ( int i = 0; i < N; i++) {
            for (int j = i; j < N; j++ ) {
                long rangeSum = prefixSumA[j] - (i==0 ? 0 : prefixSumA[i-1]);
                long target = T - rangeSum;
                int left = 0;
                int right = bSum.length -1;
                while ( left <= right ) {
                    int mid = (left + right) / 2;
                    if (bSum[mid] >= target) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                int lowerBound = left;
                left = 0;
                right = bSum.length -1;
                while ( left <= right ) {
                    int mid = (left + right) / 2;
                    if (bSum[mid] > target) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                int upperBound = left;

                answer += upperBound - lowerBound;
            }
        }
        System.out.print(answer);
    }
}