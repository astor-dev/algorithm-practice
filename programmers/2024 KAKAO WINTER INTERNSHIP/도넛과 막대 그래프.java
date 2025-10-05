import java.util.*;

class Solution {

    static int MAX_NODE = 1_000_000;
    public int[] solution(int[][] edges) {
        int[] inDegree = new int[MAX_NODE];
        int[] outDegree = new int[MAX_NODE];
        int articulationPoint = -1;
        int from;
        int to;
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for(int i = 0; i < edges.length; i++) {
            from = edges[i][0];
            to = edges[i][1];
            inDegree[to]++;
            outDegree[from]++;
            adj.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            if(outDegree[from] >= 2 && inDegree[from] == 0) {
                articulationPoint = from;
            }
        }
        int doughnutGraphCount = 0;
        int barGraphCount = 0;
        int eightShapeGraphCount = 0;
        for(int connected : adj.get(articulationPoint)) {
            int currentNode = connected;
            boolean initialLoop = true;
            while(true) {
                if(!adj.containsKey(currentNode)) {
                    barGraphCount++;
                    break;
                }
                List<Integer> nextNodes = adj.get(currentNode);

                if(nextNodes.size() > 1) {
                    eightShapeGraphCount++;
                    break;
                } else if(currentNode == connected && !initialLoop) {
                    doughnutGraphCount++;
                    break;
                }
                initialLoop = false;
                currentNode = nextNodes.get(0);
            }
        }


        int[] answer = {articulationPoint, doughnutGraphCount, barGraphCount, eightShapeGraphCount};
        return answer;
    }
}