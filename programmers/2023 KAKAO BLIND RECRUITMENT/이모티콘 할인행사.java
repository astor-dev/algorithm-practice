import java.util.*;

class Solution {
    static int[] rates = {10, 20, 30, 40};
    static int maxSubscriber = 0;
    static int maxPriceSum = 0;
    // 할인율은 10%, 20%, 30%, 40% 중 하나
    // 그냥 4개 다 탐색 때리면 안되나?
    // 유저 100 명 이모티콘 배열 최대 7개 -> 탐색 때릴만 하다
    // 2^14 * 100 연산
    public int[] solution(int[][] users, int[] emoticons) {
        track(0, new int[emoticons.length], users, emoticons);
        int[] answer = {maxSubscriber, maxPriceSum};
        return answer;
    }

    public void track(int node, int[] currentDiscount, int[][] users, int[] emoticons) {
        if(node == emoticons.length) {
            check(currentDiscount, users, emoticons);
            return;
        }
        for(int rate: rates) {
            currentDiscount[node] = rate;
            track(node + 1, currentDiscount, users, emoticons);
        }
    }

    public void check(int[] discountRate, int[][] users, int[] emoticons) {
        int subscriber = 0;
        int priceSum = 0;
        for(int[] user: users) {
            int userPriceSum = 0;
            int willingToBuyRate = user[0];
            int willingToSubscribePrice = user[1];
            boolean hasSubscribed = false;
            for(int i = 0; i < emoticons.length; i++) {
                if(willingToBuyRate <= discountRate[i]) {
                    userPriceSum += emoticons[i] * (100-discountRate[i]) / 100;
                    if(userPriceSum >= willingToSubscribePrice) {
                        subscriber++;
                        hasSubscribed = true;
                        break;
                    }
                }
            }
            if(!hasSubscribed) priceSum+=userPriceSum;
        }
        if(subscriber >= maxSubscriber) {
            if(subscriber==maxSubscriber) {
                maxPriceSum = Math.max(priceSum, maxPriceSum);
            } else {
                maxPriceSum = priceSum;
            }
            maxSubscriber = subscriber;
        }
    }
}