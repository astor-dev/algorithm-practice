import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {

        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        long totalSum = 0;
        long q1Sum = 0;
        int length = 0;
        for (int x : queue1) {
            q1.add(x);
            q1Sum += x;
            totalSum += x;
            length++;
        }
        for (int x : queue2) {
            q2.add(x);
            totalSum += x;
            length++;
        }

        int loopCount = 0;
        long targetSum = totalSum/2;
        while(loopCount <= 2*length) {
            if(q1Sum == targetSum) {
                return loopCount;
            }
            if(q1Sum > targetSum) {
                int polled = q1.poll();
                q1Sum -= polled;
                q2.add(polled);
            } else {
                int polled = q2.poll();
                q1Sum += polled;
                q1.add(polled);
            }
            loopCount++;
        }

        int answer = -1;
        return answer;
    }
}