import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] a = new int[n], b = new int[n], c = new int[n], d = new int[n];
        for (int i = 0; i < n; i++ ){
            stringTokenizer = new StringTokenizer((bufferedReader.readLine()));
            a[i] = Integer.parseInt(stringTokenizer.nextToken());
            b[i] = Integer.parseInt(stringTokenizer.nextToken());
            c[i] = Integer.parseInt(stringTokenizer.nextToken());
            d[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int[] arr1 = new int[n*n];
        int[] arr2 = new int[n*n];

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr1[index] = a[i] + b[j];
                arr2[index] = c[i] + d[j];
                index++;
            }
        }
        long answer = 0;
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int lastTarget = Integer.MAX_VALUE;
        int lastValue = 0;
        for (int value : arr2) {
            int target = -value;
            if(lastTarget == target) {
                answer += lastValue;
                continue;
            }

            int lowerbound = 0;
            int upperbound = 0;

            int left = 0;
            int right = arr1.length - 1;
            // find lower bound
            while (left <= right) {
                int mid = (left + right) / 2;
                if (arr1[mid] >= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            lowerbound = left;
            left = 0;
            right = arr1.length - 1;
            // find upper bound
            while (left <= right) {
                int mid = (left + right) / 2;
                if (arr1[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            upperbound = left;
            lastValue = upperbound - lowerbound;
            answer += lastValue;
            lastTarget = target;
        }

        System.out.print(answer);
    }
}