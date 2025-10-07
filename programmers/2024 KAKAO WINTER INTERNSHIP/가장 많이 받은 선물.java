import java.util.*;
import java.io.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        /**
         자료구조는 선물지수 map
         각 친구들간의 선물 교환 토탈 joy bread String key로 map
         */

        Map<String,Integer> presentScore = new HashMap<>();
        // KEY: "$준사람 $받은사람"
        Map<String,Integer> giftCount = new HashMap<>();

        StringTokenizer st;
        for(String gift : gifts) {
            st = new StringTokenizer(gift);
            String from = st.nextToken();
            String to = st.nextToken();
            presentScore.put(from, presentScore.getOrDefault(from, 0) +1);
            presentScore.put(to, presentScore.getOrDefault(to, 0) -1);
            giftCount.put(gift, giftCount.getOrDefault(gift, 0) +1);
        }
        // friend의 index와 대응
        int[] nextMonthGiftCount = new int[friends.length];
        for(int f1Idx = 0; f1Idx < friends.length; f1Idx++) {
            for(int f2Idx = f1Idx+1; f2Idx < friends.length; f2Idx++) {
                int f1ToF2 = giftCount.getOrDefault(friends[f1Idx] + " " + friends[f2Idx], 0);
                int f2ToF1 = giftCount.getOrDefault(friends[f2Idx] + " " + friends[f1Idx], 0);
                // f1이 더 많이준 경우
                if(f1ToF2 > f2ToF1) {
                    nextMonthGiftCount[f1Idx]++;
                } else if (f2ToF1 > f1ToF2) {
                    nextMonthGiftCount[f2Idx]++;
                } else if(f1ToF2 == f2ToF1) {
                    int f1PresentScore = presentScore.getOrDefault(friends[f1Idx], 0);
                    int f2PresentScore = presentScore.getOrDefault(friends[f2Idx], 0);
                    if(f1PresentScore > f2PresentScore) {
                        nextMonthGiftCount[f1Idx]++;
                    } else if (f2PresentScore > f1PresentScore) {
                        nextMonthGiftCount[f2Idx]++;
                    }
                }
            }
        }

        int answer = 0;
        for(int count : nextMonthGiftCount) answer = Math.max(answer, count);
        return answer;
    }
}
