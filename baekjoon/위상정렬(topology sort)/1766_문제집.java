import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main{
    static int n;
    static int m;
    static int[] degree;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            degree = new int[n+1];
            graph = new ArrayList[n+1];
            

            
            for(int i = 0; i <= n; i++){
                graph[i] = new ArrayList<Integer>();
            }
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                degree[b]++;
                graph[a].add(b);                                                         
            }
            topology_sort();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }
    }
    static void topology_sort(){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i <= n; i++){
            if(degree[i] == 0)
                pq.add(i);
        }
        while(!pq.isEmpty()){
            // System.out.println(Arrays.toString(degree));
            int n = pq.poll();
            for(int x : graph[n]){
                if(--degree[x] == 0)
                    pq.add(x);
            }
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
