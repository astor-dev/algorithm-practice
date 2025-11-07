import java.io.*;
import java.util.*;


public class Main {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();
    static int n;
    static int m;
    static String UNKNOWN = "UNKNOWN";
    static int[] parent;
    static int[] diff;
    public static void main(String[] args) throws Exception {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        do {
            parent = new int[n+1];
            diff = new int[n+1];
            for(int i = 0; i <= n; i++) parent[i] = i;
            /*
            max 100_000 개의 샘플
            조상으로부터의 차이를 저장하고 있음
            유니온 파인드
            유니온 불가 = UNKNOWN
             */
            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                char operation = stringTokenizer.nextToken().charAt(0);
                int a;
                int b;
                int w;
                int parent1;
                int parent2;
                switch (operation) {
                    case '!' :
                        a = Integer.parseInt(stringTokenizer.nextToken());
                        b = Integer.parseInt(stringTokenizer.nextToken());
                        w = Integer.parseInt(stringTokenizer.nextToken());
                        parent1 = find(a);
                        parent2 = find(b);
                        parent[parent2] = parent1;
                        if (parent1 != parent2) {
                            // diff 업데이트: diff[parentB] = (parentB에서 parentA 까지의 차이)
                            // parentA + diff[a] = a
                            // a + w = b
                            // parentB + diff[b] = b
                            // parentB = parentA + diff[a] + w - diff[b]
                            // diff[parentB] = diff[a] + w - diff[b]
                            diff[parent2] = diff[a] + w - diff[b];
                        }   break;
                    case '?' :
                        a = Integer.parseInt(stringTokenizer.nextToken());
                        b = Integer.parseInt(stringTokenizer.nextToken());

                        parent1 = find(a);
                        parent2 = find(b);

                        if (parent1 != parent2) {
                            stringBuilder.append(UNKNOWN).append("\n");
                        } else {
                            stringBuilder.append(diff[b] - diff[a]).append("\n");
                        }
                        break;
                }
            }
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
        } while (n != 0 && m != 0);
        System.out.println(stringBuilder);
    }

    static int find(int node) {
        if(parent[node] != node) {
            int parentNode = find(parent[node]);
            // diff 압축
            diff[node] +=  diff[parent[node]];
            parent[node] = parentNode;
        }
        return parent[node];
    }
}