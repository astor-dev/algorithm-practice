package playground;

import java.io.BufferedReader;
import java.io.InputStreamReader;
    import java.util.*;
    
    
    public class Main {
    
        public static int[][] moves = new int[][]{{1,0},{-1,0}, {0,1}, {0, -1}};
        public static void main(String[] args) throws Exception {
    
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
    
            int n = Integer.parseInt(stringTokenizer.nextToken()), m = Integer.parseInt(stringTokenizer.nextToken());
    
            int[][] graph = new int[n][m];
            boolean[][] visited = new boolean[n][m];
            for (int i = 0; i < n; i++){
                String row = bufferedReader.readLine();
                for (int j = 0; j < m ; j++) {
                    char node = row.charAt(j);
                    if(node == 'H'){
                        graph[i][j] = -1;
                        continue;
                    }
                    graph[i][j] = node - '0';
                }
            }
    
            ArrayDeque<int[]> stack = new ArrayDeque<>();
            stack.add(new int[]{0,0});
            while(!stack.isEmpty()) {
                int[] xy = stack.pop();
                for (int[] move : moves){
                    int dx = xy[0] + move[0];
                    int dy = xy[1] + move[1];
                }
            }
    
        }
    }
