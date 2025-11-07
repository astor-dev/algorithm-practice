import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 
        
    public static void main(String[] args) throws Exception {
        try{   
// ========================================================Write Here========================================================            
            StringBuilder sb = new StringBuilder();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.valueOf(st.nextToken());
            int L = Integer.valueOf(st.nextToken());
            int R = Integer.valueOf(st.nextToken());
            
            long[][][] dp = new long[101][101][101];
            dp[1][1][1] = 1;
            dp[2][1][2] = 1;
            dp[2][2][1] = 1;
            final int MOD = 1000000007;
            
            for(int i = 2; i < N+1; i++){
                for(int j = 1; j<L+1; j++){
                    for(int k = 1; k < R+1; k++){
                        dp[i][j][k] = ( (dp[i-1][j-1][k])%MOD + (dp[i-1][j][k-1])%MOD + (dp[i-1][j][k]*(i-2))%MOD )%MOD; //오버플로우 방지를 위해 모든 계산 과정에서 나누기를 한다.
                    }
                }
            }
            bw.write(dp[N][L][R] + "\n"); //기본형 -> 문자열 형변환 꼼수
            bw.close();

// ========================================================Write Here========================================================            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
}
