import java.util.*;

class Solution {
    /**
     모든 값을 이미 다 앎 -> 보통은 그리디
     최선은 어차피 멀리 가야 하지 않나?
     -> deliveries + pickups 남은 곳 중 가장 먼 곳을 간다
     가장 먼 곳에서 한칸씩 줄인다.
     반복
     */
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliveryCursor = calculateCursor(n-1, deliveries);
        int pickupCursor = calculateCursor(n-1, pickups);
        int move = Math.max(deliveryCursor, pickupCursor);
        while(move != -1) {
            decrease(deliveryCursor, deliveries, cap);
            decrease(pickupCursor, pickups, cap);
            answer += (move+1) * 2;
            deliveryCursor = calculateCursor(deliveryCursor, deliveries);
            pickupCursor = calculateCursor(pickupCursor, pickups);
            move = Math.max(deliveryCursor, pickupCursor);
        }
        return answer;
    }

    private int calculateCursor(int currentCursor, int[] arr) {
        for(int i = currentCursor; i >= 0; i--) {
            if(arr[i] > 0) return i;
        }
        return -1;
    }

    private void decrease(int currentCursor, int[] arr, int cap) {
        for(int i = currentCursor; i >= 0; i--) {
            if(cap > 0 && arr[i] > 0) {
                if(cap >= arr[i]) {
                    cap -= arr[i];
                    arr[i] = 0;
                } else {
                    arr[i] -= cap;
                    cap = 0;
                }
                if(cap == 0) {
                    return;
                }
            }
        }
    }
}