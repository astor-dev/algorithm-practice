import java.util.*;

class Solution {
    static class Problem {
        int alpReq;
        int copReq;
        int alpRwd;
        int copRwd;
        int cost;
        Problem(int[] problem) {
            this.alpReq = problem[0];
            this.copReq = problem[1];
            this.alpRwd = problem[2];
            this.copRwd = problem[3];
            this.cost = problem[4];
        }

        boolean canSolve(int alp, int cop) {
            return (alpReq <= alp && copReq <= cop);
        }
    }

    static List<Problem> problemList;
    static final int INF = 100_000_000;

    public int solution(int initAlp, int initCop, int[][] problems) {
        int maxAlpReq = 0;
        int maxCopReq = 0;
        int maxAlpRwd = 0;
        int maxCopRwd = 0;
        problemList = new ArrayList<>();
        for(int[] problemArr : problems) {
            Problem problem = new Problem(problemArr);
            problemList.add(problem);
            maxAlpReq = Math.max(maxAlpReq, problem.alpReq);
            maxCopReq = Math.max(maxCopReq, problem.copReq);
            maxAlpRwd = Math.max(maxAlpRwd, problem.alpRwd);
            maxCopRwd = Math.max(maxCopRwd, problem.copRwd);
        }
        if (initAlp >= maxAlpReq && initCop >= maxCopReq) {
            return 0;
        }
        int maxAlp = Math.max(initAlp, maxAlpReq) + maxAlpRwd;
        int maxCop = Math.max(initCop, maxCopReq) + maxCopRwd;
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for(int[] arr : dp) {
            Arrays.fill(arr, INF);
        }
        dp[initAlp][initCop] = 0;
        for(int alp = initAlp; alp <= maxAlp; alp++) {
            for(int cop = initCop; cop <= maxCop; cop++) {
                if(alp+1 <= maxAlp) dp[alp+1][cop] = Math.min(dp[alp+1][cop], dp[alp][cop] + 1);
                if(cop+1 <= maxCop) dp[alp][cop+1] = Math.min(dp[alp][cop+1], dp[alp][cop] + 1);
                for(Problem problem : problemList) {
                    if(problem.canSolve(alp,cop)) {
                        int newAlp = Math.min(alp + problem.alpRwd, maxAlp);
                        int newCop = Math.min(cop + problem.copRwd, maxCop);
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[alp][cop] + problem.cost);
                    }
                }
            }
        }
        int answer = INF;
        for (int alp = maxAlpReq; alp <= maxAlp; alp++) {
            for (int cop = maxCopReq; cop <= maxCop; cop++) {
                answer = Math.min(answer, dp[alp][cop]);
            }
        }
        return answer;
    }
}