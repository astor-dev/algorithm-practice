import java.util.*;

class Solution {

    /*
    A가 최소화되는 최적해가 필요함. DP or Greedy
    상태가 누적됨. 상태를 관리할 무언가
    */
    public int solution(int[][] info, int n, int m) {
        int answer = -1;
        // int dp[n] = A 가 n의 비용을 지불할 시 B가 지불해야할 비용
        int INF = 1_000_000_000;
        int[] dpFactory = new int[n]; // 어차피 n만큼만 내면 되니까. 넘기진 않음
        Arrays.fill(dpFactory, INF);
        int[] dp = dpFactory.clone();
        dp[0] = 0;
        // 토탈에서 각 값을 빼면서 감
        for (int[] price: info) {
            int aPrice = price[0];
            int bPrice = price[1];
            // aPrice의 값은 dp[aPrice]
            // dp 배열 full scan
            // 결국 물건을 훔쳐 나가긴 해야함
            int[] newDp = dpFactory.clone();
            for(int i = 0; i < n; i++) {
                // a가 훔침
                if(i + aPrice  < n) {
                    newDp[i + aPrice] = Math.min(newDp[i + aPrice], dp[i]);
                }
                // b가 훔침
                if(dp[i] + bPrice < m) {
                    newDp[i] = Math.min(newDp[i], dp[i] + bPrice);
                }
            }
            dp = newDp;
        }
        for(int i = dp.length-1; i >= 0; i--) {
            if(dp[i] != INF) answer = i;
        }

        return answer;
    }
}