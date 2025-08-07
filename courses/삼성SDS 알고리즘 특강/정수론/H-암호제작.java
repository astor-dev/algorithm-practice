import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        BigInteger number = new BigInteger(stringTokenizer.nextToken());
        BigInteger least = new BigInteger(stringTokenizer.nextToken());
        for (BigInteger i = BigInteger.valueOf(2);
             i.compareTo(least) < 0;
             i = i.add(BigInteger.ONE)) {

            if (number.mod(i).equals(BigInteger.ZERO)) {
                stringBuilder.append("BAD").append(" ").append(i);
                System.out.println(stringBuilder);
                return;
            }
        }
        System.out.println("GOOD");
    }
}