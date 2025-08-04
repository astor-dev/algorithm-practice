import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 
        
    public static void main(String[] args) throws Exception {

        try{   
/*

*/
// ========================================================Write Here========================================================            
            StringBuilder sb = new StringBuilder();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int n = Integer.parseInt(br.readLine());
            
            int[] dp = new int[n+2];
            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 2;
            for(int i = 3; i<n+1; i++){
                dp[i] = (dp[i-2] + dp[i-1])%10007;  //오버플로우 방지용  
            }
            sb.append(dp[n]);
            bw.write(sb.toString());
            bw.close();            
// ========================================================Write Here========================================================            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
}

