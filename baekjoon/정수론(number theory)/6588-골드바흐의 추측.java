import java.io.*;
import java.util.*;


public class Main {


    /**
     * 코테용 테크닉 정리
     * sqrt(n) == a 이런 연산 할 바에 반대 항을 제곱해라
     * n = a*a
     * 에라토스테네스의 채 풀 때
     * - 첫번째 루프는 목표의 제곱근까지만 돌면 됨
     * - 두번째 루프는 초기 값을 i * i로 해도됨 이미 지워졌기 때문
     * - 보충 설명: [i * 1] ~ [i * i-1] 까지는 이미 루프 돌았음
     *   가령 i = 5 면 5*2는 i=2 루프 때, 5*3은 i=3 루프 때, 5*4는 i=4 루프 때 확인했음
     */

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
