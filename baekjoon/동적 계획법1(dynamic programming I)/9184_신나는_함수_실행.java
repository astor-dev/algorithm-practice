import java.io.*;
import java.util.*;
import java.util.stream.*;


public class Main {
    static int OFFSET = 50;
    static int[][][] dp =  new int[101][101][101];
    static boolean[][][] visited = new boolean[101][101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        do {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1 && c == -1) {
                bw.flush();
                bw.close();
                break;
            }
            int result = w(a,b,c);
            bw.write("w("+a+", " + b + ", " + c + ") = " +result);
            bw.newLine();
        } while (true);
    }

    // 3차원 DP
    static int w(int a, int b, int c){
        int offsetA = a + OFFSET;
        int offsetB = b + OFFSET;
        int offsetC = c + OFFSET;
        if (visited[offsetA][offsetB][offsetC]) return dp[offsetA][offsetB][offsetC];
        int answer;
        if (a <= 0 || b <= 0 || c <= 0) answer = 1;
        else if ( a > 20 || b > 20 || c > 20 ) answer = w(20,20,20);
        else if ( a < b && b < c ) answer = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
        else answer = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
        visited[offsetA][offsetB][offsetC] = true;
        dp[offsetA][offsetB][offsetC] = answer;
        return answer;
    }
}