import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        int primeNumber = Integer.parseInt(bufferedReader.readLine());
        boolean[] isPrime = new boolean[primeNumber+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        Map<Long, Boolean> hasSpecificPrefixSum = new HashMap<>();
        hasSpecificPrefixSum.put(0L, true);
        int answer = 0;
        long sum = 0;
        for (int i = 2; i <= primeNumber; i++) {
            if (!isPrime[i]) continue;
            sum += i;
            for (long j = (long)i*i; j <= primeNumber; j += i) {
                isPrime[(int)j] = false;
            }
            hasSpecificPrefixSum.put(sum, true);
            if (hasSpecificPrefixSum.getOrDefault(sum-primeNumber,false)){
//                System.out.println("match!" + i);
                answer += 1;
            }

        }

        System.out.println(answer);
    }
}