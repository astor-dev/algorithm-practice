import java.io.*;
import java.util.*;


public class Main {

    static int n ;
    static int m;
    static int[] arr;
    static boolean[] visited;
    static int[] currentResult;
    public static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        arr = new int[n];
        visited = new boolean[n];
        currentResult = new int[m];
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(stringTokenizer.nextToken());
            arr[i] = number;
        }
        Arrays.sort(arr);

        dfs(0);
        System.out.println(stringBuilder);

    }

    public static void dfs(int depth) {

        if(depth == m) {
            for(int val : currentResult) stringBuilder.append(val).append(' ');
            stringBuilder.append("\n");
            return;
        }
        int currentDepthPreviousNumber = 0;
        for (int i = 0; i < n; i++) {
            int value = arr[i];
            int previousDepthNumber = depth > 0 ? currentResult[depth-1] : 0;
            if(!visited[i] && currentDepthPreviousNumber != value && previousDepthNumber <= value) {
                visited[i] = true;
                currentResult[depth] = value;
                currentDepthPreviousNumber = value;
                dfs(depth+1);
                // 재귀 끝까지 다 가서 리턴되고 다음 포문 돌 때는 다음 depth에서 사용하는 visited 배열을 현재 depth 까지만 찬 채로 유지해줘야 하니 다시 방문 취소
                visited[i] = false;
            }

        }
    }
}
