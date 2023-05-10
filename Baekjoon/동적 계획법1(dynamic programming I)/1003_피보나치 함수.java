import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 
        
    public static void main(String[] args) throws Exception {

        try{   

// ========================================================Write Here========================================================            
            //입력
         
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));            
            //StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.valueOf(br.readLine());
            for(int i = 0; i<T; i++){
                int temp = Integer.valueOf(br.readLine());
                fibo tempfibo = new fibo(temp);
                tempfibo.fibonacci(temp);
                tempfibo.printStatus(temp);
                
            }

            


            
            
// ========================================================Write Here========================================================            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
}
class fibo{

    Integer[][] dp;
    fibo(int n){
        dp = new Integer[n+2][2];
        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;        
    }
    Integer[] fibonacci(int n) {        
        if(dp[n][0] == null || dp[n][1] == null){
            dp[n][0] = fibonacci(n-1)[0] + fibonacci(n-2)[0];
            dp[n][1] = fibonacci(n-1)[1] + fibonacci(n-2)[1];
        }
        return dp[n];
    }
    
    void printStatus(int n){
        System.out.println(this.dp[n][0].intValue() + " " + this.dp[n][1].intValue());
    }
}
