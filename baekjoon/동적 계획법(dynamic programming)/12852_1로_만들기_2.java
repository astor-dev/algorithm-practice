import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main{

/*
입력을 n으로 잡고 dp로 반복 bottom-up
n이 도달할 때 까지 반복
n이 2의 배수 일 경우 dp[n/2]
n이 3의 배수 일 경우 dp[n/3]
else -1

*/
    static int n;
    static int[] dp;
    static int now;
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            dp = new int[n+1];
            for( int i = 1; i <= n; i++){
                dp[i] = i-1;
                if(i % 2 == 0){
                    dp[i] = Math.min(dp[i/2]+1, dp[i]);
                }
                if(i % 3 == 0){
                    dp[i] = Math.min(dp[i/3]+1, dp[i]);
                }
                dp[i] = Math.min(dp[i-1] + 1, dp[i]);                
            }
            System.out.println(dp[n]);

            
            int tracker = n;
            while(tracker != 0){
                System.out.print(tracker + " ");
                now = dp[tracker];
                if((tracker % 3 == 0) && (dp[tracker/3] == now -1)){ 
                    tracker = tracker/3;
                    continue;
                }
                if((tracker % 2 == 0) && (dp[tracker/2] == now -1)){
                    tracker = tracker/2;
                    continue;
                }
                tracker--;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }
    }
}
