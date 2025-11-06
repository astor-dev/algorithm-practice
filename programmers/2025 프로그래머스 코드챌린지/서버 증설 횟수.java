import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        // 1대 그냥 있고
        // 3대면 3 x m 필요
        // 현재 수용가능한 서버 수를 트래킹 가능
        // 걍 풀스캔
        // 증감이 되어야함
        int[] capacity = new int[players.length];
        Arrays.fill(capacity,m-1);
        // 0~3미만 1대, 6명 이상 9명 미만 = 2대
        for(int i = 0; i < players.length; i++) {
            int currentCapacity = capacity[i];
            int requiredCapacity = players[i];
            int diff = requiredCapacity - currentCapacity;
            if(diff > 0) {
                int add = diff/m; // 4개 필요하면 1개 올려야함 5개 필요하면 몫 + 1 개 올려야함
                if(diff%m != 0) add++; // 나누어 떨어지지 않으면 +1
                answer += add;
                for(int j = 0; j < k; j++) {
                    if(i+j >= capacity.length) continue;
                    capacity[i+j] += add * m;
                }

            }
        }
        return answer;
    }

}