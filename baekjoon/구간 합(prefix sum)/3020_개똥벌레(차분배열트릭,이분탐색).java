import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int H;
    public static void main (String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer =  new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        H = Integer.parseInt(stringTokenizer.nextToken());
        boolean shouldBeNegated = false;
        int[] counter = new int[H];
        int[] weight = new int[H+1];
        for (int i = 0; i < N; i++) {
            int obstacle = Integer.parseInt(bufferedReader.readLine());
            if (shouldBeNegated) {
                weight[H - obstacle]++;
                weight[H]--;
            } else {
                // 석순: 아래에서부터 obstacle 길이 올라감
                weight[0]++;
                weight[obstacle]--;
            }
            shouldBeNegated = !shouldBeNegated;
        }
        counter[0] = weight[0];
        for (int i = 1; i < H; i++) {
            counter[i] = counter[i-1] + weight[i];

        }
//        System.out.println(Arrays.toString(weight));
//        System.out.println(Arrays.toString(counter));
        Arrays.sort(counter);
        // 최소값
        int minValue = counter[0];
        int left = 0;
        int right = H -1;
        // upperBound 찾기
        while(left <= right) {
            int mid = (left + right) / 2;
            if( counter[mid] > minValue ) {
                right = mid -1;
            } else {
                left = mid  + 1;
            }
        }
        System.out.println(minValue + " " + left);
    }
}
