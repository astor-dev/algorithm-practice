import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int K;
    static int M;

    static long[] segmentTree;
    static long[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        segmentTree = new long[4*N];
        arr = new long[N+1];
        for (int i = 1; i <= N; i++){
            arr[i] = Long.parseLong(bufferedReader.readLine());
        }
        build(1, 1, N);
        for (int i = 0; i < K+M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            long c = Long.parseLong(stringTokenizer.nextToken());
            // b 번째 수를 c로 변경
            if (a == 1) {
                update(1, 1, N, b, c);
            }
            // b 부터 c 까지의 구간합
            if (a == 2) {
                long value = query(1, 1, N, b, (int) c);
                stringBuilder.append(value).append("\n");
            }
        }
        System.out.print(stringBuilder);




    }

    static void build(int node, int start, int end){
        if(start == end) {
            segmentTree[node] = arr[start];
            return;
        }
        // 이분탐색해서 재귀하고 리프부터 올라옴
        int mid =  (start + end)/2;
        // 왼쪽 자식
        build(node << 1, start, mid);
        // 오른쪽 자식
        build(node << 1 | 1, mid+1, end);
        // 집계
        segmentTree[node] = segmentTree[node << 1] + segmentTree[node << 1 | 1];
    }


    static void update(int node, int start, int end, int index, long value) {
        if (start == end) {
            segmentTree[node] = value;
            arr[index] = value;
            return;
        }

        int mid = (start + end)/2;
        if (start <= index && index <= mid) {
            update(node << 1, start, mid, index, value);
        } else {
           update(node << 1 | 1, mid + 1 , end , index, value);
        }
        segmentTree[node] = segmentTree[node << 1] + segmentTree[node << 1 | 1];
    }

    static long query(int node, int start, int end, int left, int right){
        if(right < start || left > end) {
            return 0;
        }
        if(left <= start && end <= right) {
            return segmentTree[node];
        }
        int mid = (start + end) / 2;
        long l = query(node << 1, start, mid, left, right);
        long r = query(node << 1 | 1 , mid + 1, end, left, right);
        return l + r;
    }

}
