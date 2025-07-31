import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();

        int n = Integer.parseInt(bufferedReader.readLine());
        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.ONE;
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        while (n-- > 0) a = a.multiply(new BigInteger(stringTokenizer.nextToken()));
        int m = Integer.parseInt(bufferedReader.readLine());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        while (m-- > 0) b = b.multiply(new BigInteger(stringTokenizer.nextToken()));
        String answer = a.gcd(b).toString();
        if (answer.length() > 9) {
            answer = "000000000" + answer;
            answer = answer.substring(answer.length() - 9);
        }
        System.out.println(answer);
    }
}
