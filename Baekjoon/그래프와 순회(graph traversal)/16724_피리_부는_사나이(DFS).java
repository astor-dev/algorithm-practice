import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{
    static int n;
    static int m;
    static int[][][] moveD;
    static int[][] visited;
    static int counter = 0;
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;                  
            st = new StringTokenizer(br.readLine());
                
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            moveD = new int[n][m][2];
            visited = new int[n][m];
            for(int i = 0; i < n; i++){
                String str = br.readLine();
                for(int j = 0; j < m; j++){
                    String now = str.charAt(j) + "";
                    switch(now){
                        case "D":
                            moveD[i][j] = new int[]{1, 0};
                            break;
                        case "U":
                            moveD[i][j] = new int[]{-1, 0};
                            break;
                        case "L":
                            moveD[i][j] = new int[]{0,-1};
                            break;
                        case "R":
                            moveD[i][j] = new int[]{0, 1};
                            break;
                    }                    
                }
            }
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(visited[i][j]==0){
                        dfs(new int[]{i,j});                        
                    }
                }
            }
            System.out.println(counter);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }
    }     
    static void dfs(int[] xy){        
        int x = xy[0];
        int y = xy[1];
        
        visited[x][y] = 1;
        int[] dxy = moveD[x][y];
        int dx = x + dxy[0];
        int dy = y + dxy[1];
        if(visited[dx][dy] == 1){
            counter += 1;
            visited[x][y] = 2;            
            return;
        }else if(visited[dx][dy] == 2){
            visited[x][y] = 2;            
            return;
        }else{
            dfs(new int[]{dx,dy});
        }
        visited[x][y] = 2;
    }
}
