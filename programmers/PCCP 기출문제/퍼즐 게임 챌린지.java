class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = Integer.MIN_VALUE;
        long target = limit;
        for(int diff: diffs) right = Math.max(right, diff);
        while(left <= right) {
            int mid = left + (right-left)/2;
            // 내림차순이니 < 방향 + lowerbound 이니 <=
            if(calculate(mid, diffs, times) <= limit) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        int answer = left;
        return answer;
    }

    long calculate(int level, int[] difficulties, int[] times) {
        int i = 0;
        long answer = 0;
        boolean[] isSolved = new boolean[difficulties.length];
        while(i < difficulties.length) {
            int difference = level - difficulties[i];
            if(difference >= 0 || isSolved[i]) {
                answer += times[i];
                isSolved[i] = true;
                i++;
            } else {
                // 차이만큼 틀리고 다시 푼다 = difference + 1 만큼 추가
                answer += times[i] * (-1 * difference+1);
                // 직전 것 차이만큼 더 품
                answer += times[i-1] * -1 * difference;
                i++;
            }
        }
        return answer;
    }
}