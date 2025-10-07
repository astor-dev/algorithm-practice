import java.util.*;

class Solution {
    /**
     2n+1의 삼각형 좌측부터 1개씩 탐색
     dp[] <= 해당 칸까지의 삼각형 모양으로 만들 수 있는 경우의 수
     한칸 전진 시
     1. dp[n-1]       // 그냥 삼각형 추가
     2. dp[n-2]       // 이전 칸을 사다리꼴로 먹는 케이스
     3. dp[n-1]       // if) 위에 삼각형이 존재할 경우
     */
    public int solution(int n, int[] tops) {
        // 1-based
        int[] dp = new int[2*n+2];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i < dp.length; i++) {
            int additionalCase = 0;
            if(i%2 == 0 && tops[i/2 - 1] == 1) {
                additionalCase = dp[i-1];
            }
            dp[i] = (dp[i-1] + dp[i-2] + additionalCase) % 10007;
        }

        int answer = dp[2*n+1];
        return answer;
    }
}