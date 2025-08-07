import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {


    /**
     * gcd는 BigInteger 쓰면 유틸함수 있음
     */

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int boonJa1 = Integer.parseInt(stringTokenizer.nextToken());
        int boonMo1 = Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int boonJa2 = Integer.parseInt(stringTokenizer.nextToken());
        int boonMo2 = Integer.parseInt(stringTokenizer.nextToken());
        int boonJa = ((boonJa1 * boonMo2) + (boonJa2 * boonMo1));
        int boonMo = boonMo1 * boonMo2;
        BigInteger gcd = BigInteger.valueOf(boonJa).gcd(BigInteger.valueOf(boonMo));
        int intGcd = gcd.intValue();

        stringBuilder.append(boonJa / intGcd).append(" ").append(boonMo / intGcd);
        System.out.println(stringBuilder);
    }
}