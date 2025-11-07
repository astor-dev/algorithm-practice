import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {
    static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken()), m = Integer.parseInt(stringTokenizer.nextToken());

        BigInteger[] fact = new BigInteger[n+1];
        fact[0] = BigInteger.ONE;
        for(int i = 1; i <= n; i++) {
            fact[i] = fact[i-1].multiply(BigInteger.valueOf(i));
        }
//        System.out.println(Arrays.toString(fact));
        System.out.println((fact[n].divide(fact[m].multiply(fact[n-m]))).mod(BigInteger.valueOf(10007)));
    }
}
