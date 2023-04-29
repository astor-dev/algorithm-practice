import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.*;

public class Main{
    
    public static void solution() throws Exception{
        try{
            /*
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.valueOf(st.nextToken());
            */
            
            Scanner sc = new Scanner(System.in);
            int N = Integer.valueOf(sc.nextLine());
            
            int[] memoization = new int[N+1];
            memoization[0] = memoization[1] = 0;
            for(int i = 2; i <= N; i++){
                int temp = Math.min(memoization[i-1] + 1, (i%3 ==0) ? memoization[i/3] + 1 : memoization[i-1]+1);
                temp = Math.min(temp, (i%2==0) ? memoization[i/2] + 1 : temp);
                memoization[i] = temp;
            }
            System.out.println(memoization[N]);                    

            

        } catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    
    

    public static void main(String[] args) throws Exception {
        solution();
    }
}
