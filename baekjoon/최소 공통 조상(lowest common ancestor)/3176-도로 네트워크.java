import java.io.*;
import java.util.*;

public class Main {

    static class Road {
        int target;
        int weight;
        Road(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    static int N;
    static int M;

    static ArrayList<ArrayList<Road>> adj;

    static int[] depth;
    static int[][] sparseParent;
    static int[][] sparseMaxWeight;
    static int[][] sparseMinWeight;
    static int LOG_MAX_SIZE;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();
        N = Integer.parseInt(bufferedReader.readLine());
        adj = new ArrayList<>();
        for(int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        for (int i = 1; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            int w = Integer.parseInt(stringTokenizer.nextToken());
            adj.get(u).add(new Road(v, w));
            adj.get(v).add(new Road(u, w));
        }

        LOG_MAX_SIZE = (int)(Math.log(N) / Math.log(2)) + 1;
        depth = new int[N+1];
        sparseParent = new int[N+1][LOG_MAX_SIZE];
        sparseMinWeight = new int[N+1][LOG_MAX_SIZE];
        sparseMaxWeight = new int[N+1][LOG_MAX_SIZE];

        dfs(1, 0, 0, 0);
        buildSparseMatrix();


        M = Integer.parseInt(bufferedReader.readLine());
        while(M-- > 0) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            int[] answer = query(u, v);
            stringBuilder.append(answer[0]).append(" ").append(answer[1]).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    static void dfs(int currentNode, int parentNode, int currentDepth, int weightToParent) {
        depth[currentNode] = currentDepth;
        sparseMaxWeight[currentNode][0] = weightToParent;
        sparseMinWeight[currentNode][0] = weightToParent;
        sparseParent[currentNode][0] = parentNode;
        for (Road road : adj.get(currentNode)) {
            if(road.target == parentNode) continue;
            dfs(road.target, currentNode, currentDepth + 1, road.weight);
        }
    }

    static void buildSparseMatrix() {
        for (int j = 1; j < LOG_MAX_SIZE; j++) {
            for (int i = 1; i <= N; i++) {
                if(sparseParent[i][j-1] == 0) continue;
                sparseParent[i][j] = sparseParent[sparseParent[i][j-1]][j-1];
                sparseMaxWeight[i][j] = Math.max(sparseMaxWeight[i][j-1], sparseMaxWeight[sparseParent[i][j-1]][j-1]);
                sparseMinWeight[i][j] = Math.min(sparseMinWeight[i][j-1], sparseMinWeight[sparseParent[i][j-1]][j-1]);
            }
        }
    }
    static int[] query(int u, int v) {
        int maxWeight = 0;
        int minWeight = Integer.MAX_VALUE;

        // 1. u가 v보다 더 깊도록 설정 (depth[u] >= depth[v])
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // 2. 두 노드의 깊이를 맞춘다. (u를 v의 깊이까지 올림)
        for (int j = LOG_MAX_SIZE - 1; j >= 0; j--) {
            if (depth[u] - depth[v] >= (1 << j)) {
                minWeight = Math.min(minWeight, sparseMinWeight[u][j]);
                maxWeight = Math.max(maxWeight, sparseMaxWeight[u][j]);
                u = sparseParent[u][j];
            }
        }

        // 3. 깊이를 맞췄을 때 두 노드가 같다면 v가 u의 조상(LCA)이라는 의미
        if (u == v) {
            return new int[]{minWeight, maxWeight};
        }

        // 4. 두 노드를 동시에 올리면서 LCA 바로 아래까지 이동
        for (int j = LOG_MAX_SIZE - 1; j >= 0; j--) {
            // u와 v의 2^j번째 부모가 다를 경우에만 점프
            if (sparseParent[u][j] != sparseParent[v][j]) {
                minWeight = Math.min(minWeight, Math.min(sparseMinWeight[u][j], sparseMinWeight[v][j]));
                maxWeight = Math.max(maxWeight, Math.max(sparseMaxWeight[u][j], sparseMaxWeight[v][j]));
                u = sparseParent[u][j];
                v = sparseParent[v][j];
            }
        }

        // 5. 마지막으로 한 칸 위의 부모가 LCA이며, 이 경로의 가중치를 추가
        minWeight = Math.min(minWeight, Math.min(sparseMinWeight[u][0], sparseMinWeight[v][0]));
        maxWeight = Math.max(maxWeight, Math.max(sparseMaxWeight[u][0], sparseMaxWeight[v][0]));

        return new int[]{minWeight, maxWeight};
    }
}
