import java.io.*;
import java.util.*;

public class Main {

    /**
     * 누적합 배열을 만듦
     * 커서부터 누적합 배열 뒷부분까지
     * (누적합[i] - 커서의 누적합) >= S가 되는 구간 중 가장 짧은 구간 == lowerBound 이진탐색으로 찾음
     * Max(i-cursor, answer)
     */

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken()), S = Integer.parseInt(stringTokenizer.nextToken());

        int[] prefixSumArr = new int[N+1];
        int sum = 0;
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= N; i++) {
            int value = Integer.parseInt(stringTokenizer.nextToken());
            sum += value;
            prefixSumArr[i] = sum;
        }
        int answer = Integer.MAX_VALUE;
//        System.out.println(Arrays.toString(prefixSumArr));
        for (int i = 0; i <= N; i++) {

            int target = prefixSumArr[i] + S;
            int left = i;
            int right = N;
            while (left <= right) {
                int mid = (left + right) / 2;
                if(prefixSumArr[mid] >= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if( 0 < left && left < N+1) {
                answer = Math.min(answer, left - i);
            }
        }
        System.out.print(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}
