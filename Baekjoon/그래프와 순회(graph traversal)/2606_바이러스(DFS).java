import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 
    static int count = 0;
    static boolean[] visited = new boolean[101]; //방문 == 감염
    static ArrayList<Integer>[] graph = new ArrayList[101]; //index 0은 사용안함        
    public static void dfs(int n){
        if(visited[n] == true){
            return; //중복 탐색 방지
        }
        if(n != 1){
            count +=1;            
        }
        visited[n] = true;
        for(int node : graph[n]){
            if(visited[node] == false){
                dfs(node);
            }
        }
    }
        
    public static void main(String[] args) throws Exception {
        try{   
// ========================================================Write Here========================================================            
            StringBuilder sb = new StringBuilder();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            int CPUCounts = Integer.valueOf(br.readLine());
            int edgeCounts = Integer.valueOf(br.readLine());
            for (int i = 0; i<CPUCounts+1; i++){
                graph[i] = new ArrayList<Integer>();
            }
            for(int i = 0; i<edgeCounts; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int N = Integer.valueOf(st.nextToken());
                int M = Integer.valueOf(st.nextToken());
                graph[N].add(M);                                
                graph[M].add(N); //양뱡향임!!
            }
            dfs(1);
            System.out.println(count);

            


// ========================================================Write Here========================================================            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
}

