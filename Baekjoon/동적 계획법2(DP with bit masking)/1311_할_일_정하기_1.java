import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main{
    static int n;
    static int[][] dp;
    static int[][] costs;
    static final int INF = 1_000_000_000;
    
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));                                
            StringTokenizer st;                
            
            n = Integer.parseInt(br.readLine());
            
            dp = new int[n][1<<n];
            costs = new int[n][n];
            
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    costs[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println(bitDP(0,0));
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }
    }
    static int bitDP(int person, int mask){
        if(person == n)
            return 0;
        if(dp[person][mask] != 0)
            return dp[person][mask];
        
        int result = INF;
        
        for(int i = 0; i < n; i ++){
            if((mask & (1<<i)) == 0)
                result = Math.min(result, costs[person][i] + bitDP(person +1, mask | (1 << i)));                        
        }
        dp[person][mask] = result;
        // System.out.println(person + "/" + Integer.toBinaryString(mask) + " = " + result);
        return dp[person][mask];
    }
}
