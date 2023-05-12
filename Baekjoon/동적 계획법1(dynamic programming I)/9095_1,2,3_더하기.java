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

            int T = Integer.parseInt(br.readLine());
            for(int i = 0; i<T; i++){
                int n = Integer.parseInt(br.readLine());
                int[] dp = new int[n+3];
                //1,2,3의 합 경우의수 구하기

                for(int j = 0; j<n; j++){ //2~n까지 반복       
                    dp[1] = 1; // 1
                    dp[2] = 2; // 1+1, 2+2
                    dp[3] = 4; // 1+1+1, 2+1, 1+2, 3                    
                    dp[j+1] += (dp[j]);
                    dp[j+2] += (dp[j]);
                    dp[j+3] += (dp[j]);                      
                }
                dp[1] = 1; // 1
                dp[2] = 2; // 1+1, 2+2
                dp[3] = 4; // 1+1+1, 2+1, 1+2, 3                   
                sb.append(dp[n]+"\n");
            }
            bw.write(sb.toString());
            bw.close();
// ========================================================Write Here========================================================            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
}
