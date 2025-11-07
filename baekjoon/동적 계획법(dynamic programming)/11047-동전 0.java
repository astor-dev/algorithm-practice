import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main{
    
    public static void solution() throws Exception{
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] moneyArr = new int[N];
            for (int i = 0; i<N; i++){
                moneyArr[i] = (Integer.parseInt(br.readLine()));
                
            }
            int counter = 0;
            while(K != 0){
            int tmp;
            int lowest = 99999999;                
            for (int i = 0; i<moneyArr.length; i++){
                tmp = K - moneyArr[i];
                if (tmp >= 0 && lowest>tmp){
                    lowest = tmp;
                }
            }
            //System.out.println(lowest + ", " + K);
            counter += 1;
            K = lowest;
            };
            br.close();
            System.out.println(counter);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) throws Exception {
        solution();
    }
}
