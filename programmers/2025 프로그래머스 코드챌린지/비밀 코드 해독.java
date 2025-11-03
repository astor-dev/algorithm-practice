class Solution {

    int[][] Q;
    int N;
    int[] ANS;
    int answer;

    public int solution(int n, int[][] q, int[] ans) {
        N = n;
        Q = q;
        ANS = ans;
        answer = 0;
        track(new int[5], 0, 1);

        return answer;
    }

    private void track(int[] current, int index, int start) {
        if(index == 5) {
            if(isValid(current)) {
                answer++;
            }
            return;
        }
        for(int i = start; i <= N; i++) {
            current[index] = i;
            track(current, index + 1, i + 1);
        }
        return;
    }

    boolean isValid(int[] target) {
        for(int i = 0; i < Q.length; i++) {
            int count = 0;
            for(int tVal : target) {
                for(int qVal: Q[i]) {
                    if(tVal == qVal) {
                        count++;
                        break;
                    }
                }
            }
            if(count != ANS[i]) return false;
        }
        return true;
    }
}
