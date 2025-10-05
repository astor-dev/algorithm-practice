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

/*
점 하나를 단절 시, 연결되어 있으면 하나의 그래프로 침
간선 리스트로 그래프를 표현
찾으려는 단절점의 특징: 나가는 화살표가 최소 2개 + 들어오는 화살표 없음

- 도넛 모양 그래프 -> 간선이 한개며 사이클이 존재
- 일자 모양 그래프 -> 간선이 한개며 사이클이 없음
- 팔자 모양 그래프 -> 도넛 모양 그래픈데 가운데 정점을 기준으로 대칭
*/

class Solution {

    static int MAX_NODE = 1_000_000;
    public int[] solution(int[][] edges) {
        int[] inDegree = new int[MAX_NODE];
        int[] outDegree = new int[MAX_NODE];
        for(int i = 0; i < edges.length; i++) {
            int from = edge[i][0];
            int to = edge[i][1];
            inDegree[from]++;
            outDegree[to]++;
        }
        int articulationPoint
        for(int i = 0; i < MAX_NODE; i++) {

        }

        int articulationPoint = findArticulationPoint(edges);

        int[] answer = {};
        return answer;
    }

    private int findArticulationPoint(int[][] edges) {
    }
}