import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {

    static int n ;
    static BigInteger fact[];
    public static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        fact = new BigInteger[n+1];
        fact[0] = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i-1].multiply(BigInteger.valueOf(i));
        }


        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        boolean toFindIndex = stringTokenizer.nextToken().equals("2");
        if(toFindIndex) {
            int[] numbers = new int[n+1];
            for (int i = 1; i <= n; i++) numbers[i] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            BigInteger indexSum = BigInteger.ONE;
            for (int i = 1; i <= n; i++) {
//                System.out.println(Arrays.toString(numbers));
                BigInteger rangeSize = fact[n-i];
                indexSum = indexSum.add(rangeSize.multiply(BigInteger.valueOf(numbers[i])));
                for(int j = i; j<=n; j++) if (numbers[j] > numbers[i]) numbers[j]--;
//                System.out.println(indexSum);
            }
            stringBuilder.append(indexSum);
        } else {
            BigInteger index = new BigInteger(stringTokenizer.nextToken()).subtract(BigInteger.ONE);
            List<Integer> remainingNumbers = new ArrayList<>();
            for(int i = 1; i <=n; i++) remainingNumbers.add(i);
            for (int i = n; 0 < i; i--) {
                // 좀 느리지만 쩔수없다
                BigInteger rangeSize = fact[i-1];
                BigInteger rangeIndex = index.divide(rangeSize);
                int removed = remainingNumbers.remove(rangeIndex.intValue());
                stringBuilder.append(removed).append(" ");
//                System.out.println("index = " + index + ", " + i + ": remainingNumber[" + rangeIndex + "] = " + rangeSize.multiply(rangeIndex));
                index = index.subtract(rangeSize.multiply(rangeIndex));
            }
        }
        System.out.println(stringBuilder);
    }
}
