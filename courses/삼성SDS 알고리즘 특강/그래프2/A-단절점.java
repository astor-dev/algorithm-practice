import java.util.*;
import java.io.*;


public class Main {
    static ArrayList<ArrayList<Integer>> adj;
    static TreeSet<Integer> articulationPoints = new TreeSet<>();
    // 연결된 길 (나, 샛길, 자식) 중 최솟값
    static int[] lowLinkValue;
    // 최초 진입 시 인덱스
    static int[] inIndex;
    static int index = 0;
    static int NO_PARENT = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        int vertexCount = Integer.parseInt(stringTokenizer.nextToken());
        int edgeCount = Integer.parseInt(stringTokenizer.nextToken());
        adj = new ArrayList<>(vertexCount + 1);
        for (int i = 0; i <= vertexCount; i++) {
            adj.add(new ArrayList<>());
        }
        lowLinkValue = new int[vertexCount+1];
        inIndex = new int[vertexCount+1];

        for (int i = 0; i < edgeCount; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int node1 = Integer.parseInt(stringTokenizer.nextToken());
            int node2 = Integer.parseInt(stringTokenizer.nextToken());
            adj.get(node2).add(node1);
            adj.get(node1).add(node2);
        }

        // 연결 그래프가 아닐 수 있으니 전체 순회
        for (int i = 1; i <= vertexCount; i++) {
            dfs(i, NO_PARENT);
        }

        stringBuilder.append(articulationPoints.size()).append("\n");
        articulationPoints.forEach(point -> stringBuilder.append(point).append(" "));
        System.out.println(stringBuilder.toString());

    }

    static void dfs(int currentNode, int parentNode){
        index++;
        inIndex[currentNode] = lowLinkValue[currentNode] = index;
        int childCount = 0;

        for (int adjacentNode : adj.get(currentNode)) {
            if(adjacentNode == parentNode) continue;
            boolean visited = inIndex[adjacentNode] != 0;
            if(visited) {
                // 이미 인접 노드가 방문된 경우엔, dfs 특성 상 자식이 아닌 다른 루트로 이어진 인접 노드인 것
                lowLinkValue[currentNode] = Math.min(lowLinkValue[currentNode], inIndex[adjacentNode]);
            } else {
                // 아니면 자식임
                childCount++;
                dfs(adjacentNode, currentNode);
                lowLinkValue[currentNode] = Math.min(lowLinkValue[currentNode], lowLinkValue[adjacentNode]);
                // 단절점 분기[루트가 아닌 경우]: 자식의 inIndex가 나 or 나보다 크면 나를 끊으면 자식이 단절됨
                if(parentNode != NO_PARENT &&  lowLinkValue[adjacentNode] >= inIndex[currentNode]) {
                    articulationPoints.add(currentNode);
                }
            }
        }
        // 단절점 분기[루트]: 자식이 둘 이상이면 나를 끊으면 트리가 분할됨
        if(parentNode == NO_PARENT && childCount >= 2) {
            articulationPoints.add(currentNode);
        }
    }
}