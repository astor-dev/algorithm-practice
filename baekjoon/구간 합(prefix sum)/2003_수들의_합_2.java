import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken()), m = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] arr = new int[n+1];
        int[] sumArr = new int[n+1];
        int sum = 0;
        for(int i = 1; i <= n; i++){
            int value = Integer.parseInt(stringTokenizer.nextToken());
            arr[i] = value;
            sum += value;
            sumArr[i] = sum;
        }

        int answerCount = 0;
        for(int cur1 = 1; cur1 <= n; cur1++) {
            for(int cur2=cur1; cur2<= n; cur2++){
                int subTotal = sumArr[cur2] - sumArr[cur1-1];
                if(subTotal == m) answerCount++;
            }
        }
        System.out.print(answerCount);


    }
}
