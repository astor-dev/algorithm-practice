import java.io.*;
import java.util.*;


public class Main {
    static boolean[] isPrime;


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken()), K = Integer.parseInt(stringTokenizer.nextToken());
        isPrime =  new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for(int cursor = 2; cursor <= N; cursor++) {
            if(!isPrime[cursor]) continue;
            if( 0 == --K) {
                System.out.print(cursor);
                return;
            }
            for(int i = cursor*2; i <= N; i += cursor) {
                if(!isPrime[i]) continue;
                isPrime[i] = false;
                if( 0 == --K) {
                    System.out.print(i);
                    return;
                }
            }
        }

    }
}

