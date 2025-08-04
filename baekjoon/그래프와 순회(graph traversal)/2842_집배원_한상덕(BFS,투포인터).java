import java.io.*;
import java.util.*;


public class Main {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();
    /*
    투포인터로 low/high 값 조절해나가면서 성공할 때 까지 루프
     */
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(bufferedReader.readLine());
        char[][] graph = new char[n][n];
        int[][] height = new int[n][n];
        int[] start = new int[1];
        int kCount = 0;
        int[][] moves = {{1,0}, {-1,0}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            String string = bufferedReader.readLine();
            for (int j = 0; j < n; j++) {
                char c= string.charAt(j);
                if(c == 'P') start = new int[]{i,j};
                if(c == 'K') kCount++;
                graph[i][j] = c;
            }
        }
        for(int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                int h = Integer.parseInt(stringTokenizer.nextToken());
                height[i][j] = h;
                set.add(h);
            }
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        int lowIdx = 0;
        int highIdx = 0;
        int answer = Integer.MAX_VALUE;
        /*
        실패 시 high + 1
        성공 시
        flag = true
        low + 1
        flag = true 인데 실패 시 low--; break
         */
//        System.out.println(list);
        while(lowIdx <= highIdx && highIdx < list.size()) {
            Deque<int[]> deque = new ArrayDeque<>();
            boolean[][] visited = new boolean[n][n];
            deque.add(start);
            int kMet = 0;
            boolean pMet = false;
            while (!deque.isEmpty()) {
                int[] xy = deque.poll();
                for (int[] move : moves) {
                    int dx = xy[0] + move[0];
                    int dy = xy[1] + move[1];
                    if(dx < 0 || dx >= n || dy < 0 || dy >= n) continue;
                    int dh = height[dx][dy];
                    char dc = graph[dx][dy];
                    if(dh < list.get(lowIdx) || dh > list.get(highIdx)) continue;
                    if(visited[dx][dy]) continue;
                    if(dc == 'K') {
                        kMet++;
                    }
                    if (dc == 'P') {
                        pMet = true;
                    }
                    deque.add(new int[]{dx, dy});
                    visited[dx][dy] = true;
                }
            }
            // 성공 조건
            if(kMet == kCount && pMet) {
                answer = Math.min(answer, list.get(highIdx) - list.get(lowIdx));
                lowIdx++;
//                System.out.println("current answer = " + answer);
            } else {
                highIdx++;
            }
//            System.out.println("low = " + list.get(lowIdx) + ", high = " + list.get(highIdx));
        }
        System.out.println(answer);
    }
}
