import java.io.*;
import java.lang.management.MemoryManagerMXBean;
import java.util.*;

public class Main {

    // 인접 그래프
    static ArrayList<ArrayList<Integer>> adj;
    // 노드 수, 쿼리 수
    static int N, M;
    // 오일러 경로 인덱스 카운터
    static int pathIndex = 0;
    // 오일러 경로 담는 배열
    static int[] path;
    // 최초 등장 위치 담는 배열
    static int[] firstOccurrence;
    // depth 기록 용
    static int[] depth;
    // 희소 행렬
    static int[][] sparseMatrix;

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(bufferedReader.readLine());
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        for (int i = 1; i < N; i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            adj.get(v).add(u);
            adj.get(u).add(v);
        }
        M = Integer.parseInt(bufferedReader.readLine());

        // 자료구조 초기화
        int pathLength = 2*N-1;
        path = new int[pathLength];
        depth = new int[pathLength];
        firstOccurrence = new int[N+1];
        dfs(1, 0, 1);
        buildSparseMatrix(pathLength);


        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            stringBuilder.append(lca(u, v)).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    static void dfs(int currentNode, int parentNode, int currentDepth) {
        firstOccurrence[currentNode] = pathIndex;
        path[pathIndex] = currentNode;
        depth[pathIndex] = currentDepth;
        pathIndex++;

        for (int adjacentNode : adj.get(currentNode)) {
            if(adjacentNode == parentNode) continue;
            dfs(adjacentNode, currentNode, currentDepth + 1);
            path[pathIndex] = currentNode;
            depth[pathIndex] = currentDepth;
            pathIndex++;
        }
    }

    static void buildSparseMatrix(int size) {
        int MAX_LOG_SIZE = (int) (Math.log(size) / Math.log(2)) + 1;
        sparseMatrix = new int[size][MAX_LOG_SIZE];

        for(int i = 0; i < size; i++) sparseMatrix[i][0] = i;

        for(int j = 1; 1 << j <= size; j++) {
            for (int i = 0; i + (1 << j) <= size; i++) {
                // 이전 dp 이용 절반으로 구간 나눠서 더함
                int upperHalf = sparseMatrix[i][j-1];
                int lowerHalf = sparseMatrix[i + (1 << (j-1))][j-1];
                sparseMatrix[i][j] = depth[upperHalf] > depth[lowerHalf] ? lowerHalf : upperHalf;
            }
        }
    }

    static int lca(int u, int v) {
        int uFirstIndex = firstOccurrence[u];
        int vFirstIndex = firstOccurrence[v];

        int startIndex = Math.min(uFirstIndex, vFirstIndex);
        int endIndex = Math.max(uFirstIndex, vFirstIndex);

        int j = (int)(Math.log(endIndex - startIndex + 1)/ Math.log(2));
        int upperMin = sparseMatrix[startIndex][j];
        int lowerMin = sparseMatrix[endIndex - (1 << j) + 1][j];

        return depth[upperMin] > depth[lowerMin] ? path[lowerMin] : path[upperMin];
    }
}