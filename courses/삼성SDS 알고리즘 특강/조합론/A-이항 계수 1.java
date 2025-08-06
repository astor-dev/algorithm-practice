import java.io.*;
import java.util.*;


public class Main {
    static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken()), m = Integer.parseInt(stringTokenizer.nextToken());

        int fact[] = new int[n+1];
        fact[0] = 1;
        for(int i = 1; i <= n; i++) {
            fact[i] = fact[i-1] * i;
        }
        System.out.println(fact[n] / (fact[m] * fact[n-m]));
    }
}