
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        long[] trees = new long[N];
        st = new StringTokenizer(br.readLine());
        long maxTree = 0L;
        for (int i = 0; i < N; i++) {
            trees[i] = Long.parseLong(st.nextToken());
            maxTree = Math.max(maxTree, trees[i]);
        }

        long low = 0L;
        long high = maxTree;
        long answer = 0L;

        while (low <= high) {
            long mid = (low + high) / 2;
            long sum = 0L;
            for (long tree : trees) {
                if( tree > mid ) sum += tree - mid;
                if( sum > M ) break;
            }
            // 만족할 경우
            if (sum >= M) {
                // 답을 일단 저장
                answer = mid;
                // 이후 +1 해서 탐색
                low = mid + 1;
            } else {
                // 부족할 경우 더 내려야함
                high = mid - 1;
            }
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}
