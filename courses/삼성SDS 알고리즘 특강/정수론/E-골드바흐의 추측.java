import java.io.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int n = Integer.parseInt(bufferedReader.readLine());
        int maxInput = 0;
        List<Integer> testCases = new ArrayList<>();
        while (n != 0) {
            testCases.add(n);
            maxInput = Math.max(maxInput, n);
            n = Integer.parseInt(bufferedReader.readLine());
        }
        boolean[] isPrime = getPrimes(maxInput+1);
        for (int target : testCases) {
            for(int a = 2; a <= target; a++) {
                //                System.out.println(target + ": " + target + ", " + a);
                if(isPrime[a] && isPrime[target-a]) {
                    stringBuilder.append(target).append(" = ").append(a).append(" + ").append(target-a).append("\n");
                    break;
                }
            }
        }
        System.out.println(stringBuilder);
    }

    public static boolean[] getPrimes(int n) {
        boolean[] isPrime =  new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2; i*i <= n; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
        return isPrime;
    }
}