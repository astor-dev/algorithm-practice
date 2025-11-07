import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{

//     static variables

    static ArrayList<ArrayList<Integer>> graph;
    static int[][][] visited;
    
    static int n;
    static int m;
//     ================
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>(n);
            visited = new int[2][n][m];
                       
            for(int i = 0; i<n; i++){
                String str = br.readLine();
                ArrayList<Integer> graphRow = new ArrayList<>();
                for(int j = 0; j<m; j++){
                    int val = str.charAt(j) - '0'; //char to int technique
                    graphRow.add(val);                                                            
                }
                graph.add(graphRow);
            }
            
            BFS();
            int a1 = visited[0][n-1][m-1];
            int a2 = visited[1][n-1][m-1];
            
            if(a1 + a2 == 0){
                System.out.println(-1);
            } else{
                if(a1 == 0){                   
                    System.out.println(a2);
                } else if(a2 == 0){
                    System.out.println(a1);
                } else{
                    System.out.println(Math.min(a1, a2));
                }
            }            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }    
//     static methods     
            
    static void BFS(){
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{0,0,0});
        int[][] directions = new int[][]{
            {0,1},
            {0,-1},
            {1,0},
            {-1,0}
        };
        visited[0][0][0] = 1;
        visited[1][0][0] = 1;
        
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            int broke = node[0];
            int x = node[1];
            int y = node[2];
            
            int count = visited[broke][x][y];
            // System.out.println(count + ": [" + broke + "] " + x + ", " + y);
            for(int[] dxy : directions){
                int dx = x + dxy[0];
                int dy = y + dxy[1];
                if(dx < 0 || dx >= n || dy < 0 || dy >= m){
                    continue;
                }
                //벽이 아닐 경우
                if(graph.get(dx).get(dy) == 0){
                    //처음 방문할 경우
                    if(visited[broke][dx][dy] == 0){
                        visited[broke][dx][dy] = count+1;
                        queue.add(new int[]{broke, dx, dy});                        
                    }
                }
                //벽일 경우
                if(graph.get(dx).get(dy) == 1 && broke == 0){
                    if(visited[broke][dx][dy] == 0){
                        visited[1][dx][dy] = count+1;
                        queue.add(new int[]{1, dx, dy});
                    }
                }
            }            
        }
    }
//     ==============
}

