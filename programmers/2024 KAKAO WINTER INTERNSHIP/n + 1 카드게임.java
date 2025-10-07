import java.util.*;

class Solution {
    static int GOAL;
    static int n;
    static int maxRound = 1;
    public int solution(int coin, int[] cards) {
        n = cards.length;
        GOAL = cards.length + 1;

        // process game
        // initial draw
        ArrayList<Integer> freeHand = new ArrayList<Integer>();
        ArrayList<Integer> priceHand = new ArrayList<Integer>();
        for (int i = 0; i < n/3 ; i++) {
            freeHand.add(cards[i]);
        }
        boolean hasGameEnded = false;
        int round = 1;
        Collections.sort(freeHand);
        while(!hasGameEnded) {
            int cursor = (n/3-1) + (round -1) * 2;
            if(cursor + 2 > cards.length - 1) break;
            priceHand.add(cards[cursor + 1]);
            priceHand.add(cards[cursor + 2]);
            Collections.sort(priceHand);
            // System.out.println(round + " / " + coin);
            // System.out.println(freeHand);
            // System.out.println(priceHand);
            // 공짜로 넘어가지는 지
            boolean success = precessRound(freeHand, freeHand);
            if(success) {
                round++;
                continue;
            }
            if(coin >= 1) {
                success = precessRound(freeHand, priceHand);
                if(success) {
                    round++;
                    coin -= 1;
                    continue;
                }
            }
            if(coin >= 2) {
                success = precessRound(priceHand, priceHand);
                if(success) {
                    round++;
                    coin -= 2;
                    continue;
                }
            }

            hasGameEnded = true;
        }
        return round;
    }

    static boolean precessRound(
            List<Integer> hand1, List<Integer> hand2
    ) {
        List<List<Integer>> answer = new ArrayList<>();
        for(int i = 0; i < hand1.size(); i++) {
            int value = hand1.get(i);
            int target = GOAL - value;
            int targetIndex = Collections.binarySearch(hand2, target);
            if(targetIndex >= 0) {
                // NOTE: 만약 같은 배열이면 낮은 값을 먼저 찾고 이분탐색을 하기에 i가 더 작은값임. 따라 높은 인덱스를 먼저 제거해서 연속적인 remove에서 index가 밀리지 않게 함
                // System.out.println(hand1.get(i) + " + " + hand2.get(targetIndex) + " = " + GOAL);
                hand2.remove(targetIndex);
                hand1.remove(i);
                return true;
            }
        }
        return false;
    }
}