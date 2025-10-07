import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    static int GOAL;
    static int[] dp;
    static
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        GOAL = cards.length + 1;
        dp = new int[n];

        // process game
        // initial draw
        ArrayList<Integer> hand = new ArrayList<Integer>();
        draw(hand, cards, 0, n/3);




        int answer = 0;
        return answer;
    }


    /**
    @return ㅁㄴㅇㄴㅁ
     */
    private static List<Integer> draw(List<Integer> hand, int[] cards, int start, int number) {
        for (int i = start; i < start+number ; i++) {
            hand.add(cards[i]);
        }
        return hand;
    }

    private static void processRound(List<Integer> hand, int coin, int[] cards, int round) {
    }
}