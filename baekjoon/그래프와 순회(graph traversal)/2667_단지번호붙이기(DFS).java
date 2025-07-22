
import java.io.*;
import java.util.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        List<Integer> answer = new ArrayList<>();


        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int ii = 0; ii < N; ii++) {
                int num = input.charAt(ii) - '0';
                graph[i][ii] = num;
            }
        }


        for (int i = 0; i < N; i++) {
            for (int ii = 0; ii < N; ii++) {
                if(!visited[i][ii] && graph[i][ii] == 1) {
                    // dfs here
                    Deque<int[]> stack = new ArrayDeque<>();
                    int counter = 1;
                    visited[i][ii] = true;
                    stack.push(new int[]{i,ii});
                    while(!stack.isEmpty()) {
                        int[] cur = stack.pop();
                        int x = cur[0], y = cur[1];
                        for (int idx = 0; idx < 4; idx++){
                            int nx  = dx[idx] + x, ny = dy[idx] + y;
                            if(0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny] && graph[nx][ny] == 1) {
                                stack.push(new int[]{nx,ny});
                                visited[nx][ny] = true;
                                counter++;
                            }
                        }
                    }
                    answer.add(counter);
                }
            }
        }

        Collections.sort(answer);
        sb.append(answer.size()).append("\n");
        for(int a : answer) {
            sb.append(a).append("\n");
        }
        System.out.print(sb);
    }
}