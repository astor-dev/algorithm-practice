import java.util.*;

class Solution {

    static List<List<Integer>> combinations = new ArrayList<>();
    static List<TreeMap<Integer, Integer>> sumAndCounts = new ArrayList<>();
    public int[] solution(int[][] dice) {

        TreeMap scoreTempMap = new TreeMap<>();
        scoreTempMap.put(0, 1);
        combine(dice, new ArrayList<Integer>(), 0, scoreTempMap);

        int maxSumIndex = 0;
        int maxSum = 0;

        for(int i = 0; i < combinations.size(); i++) {
            // 전체 length - 현재 index = 반대의 조합
            int opponent = Math.abs(combinations.size()-1 - i);
            /*
            키 맵 순회
            */
            TreeMap<Integer, Integer> myMap = sumAndCounts.get(i);
            TreeMap<Integer, Integer> opponentMap = sumAndCounts.get(opponent);

            int winCount = 0;
            for(Map.Entry<Integer, Integer> myEntry: myMap.entrySet()) {
                int myKey = myEntry.getKey();
                int myValue = myEntry.getValue();
                Map<Integer, Integer> opponentHeadMap = opponentMap.headMap(myKey);

                for(int opponentValue : opponentHeadMap.values()) {
                    winCount += opponentValue * myValue;
                }
            }
            if(maxSum < winCount) {
                maxSum = winCount;
                maxSumIndex = i;
            }
        }
        int[] answer = new int[combinations.get(maxSumIndex).size()];
        for(int i = 0; i < combinations.get(maxSumIndex).size(); i++) {
            answer[i] = combinations.get(maxSumIndex).get(i) + 1;
        }

        return answer;
    }

    private static void combine(int[][] dice, List<Integer> cur, int start, TreeMap<Integer, Integer> scoreCount) {
        if(cur.size() == dice.length / 2) {
            // deep copy
            combinations.add(new ArrayList<>(cur));
            sumAndCounts.add(new TreeMap<>(scoreCount));
            return;
        }
        for(int i = start; i < dice.length; i++) {
            cur.add(i);
            TreeMap<Integer, Integer> newScoreCount = new TreeMap<>();
            for(int key: scoreCount.keySet()) {
                for(int diceNumber: dice[i]) {
                    int sum = key + diceNumber;
                    int newCount =
                            newScoreCount.getOrDefault(sum,0) + scoreCount.get(key);
                    newScoreCount.put(sum, newCount);
                }
            }
            combine(dice, cur, i + 1, newScoreCount);
            cur.remove(cur.size() -1);

        }
    }

}