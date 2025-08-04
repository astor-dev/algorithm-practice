import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {

        StringTokenizer stringTokenizer = new StringTokenizer((new BufferedReader(new InputStreamReader(System.in))).readLine());
        int x = Integer.parseInt(stringTokenizer.nextToken()), y = Integer.parseInt(stringTokenizer.nextToken());

        int z = (int) ((long) y * 100 / x);
        if( z>= 99 ) {
            System.out.print(-1);
            return;
        }


        int left = 1, right = 1_000_000_000;
        int answer = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int newZ = (int) ((long)(y + mid) * 100 / (x + mid));
            if (newZ > z) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}