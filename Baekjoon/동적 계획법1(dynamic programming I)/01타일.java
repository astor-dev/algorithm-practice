import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main{
    
    public static void solution() throws Exception{
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.valueOf(st.nextToken());
            int[] memoization = new int[N];
            for (int i = 0; i < N; i++){
                if(i==0){
                    memoization[i] = 1;
                } else if(i==1){
                    memoization[i] = 2;
                } else{
                    memoization[i] = (memoization[i-1] + memoization[i-2])%15746;
                }                                
            }
            System.out.println(memoization[memoization.length -1]);
            

        } catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    
    

    public static void main(String[] args) throws Exception {
        solution();
    }
}
