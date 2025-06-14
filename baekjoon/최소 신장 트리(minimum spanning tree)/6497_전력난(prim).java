import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main{
    static int n;
    static int m;
    static ArrayList<Edge>[] graph;
    static PriorityQueue<Edge> pq;
    static int totalWeight;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            Supplier<String> prim = () -> {
                final int startIndex = 0;
                int min_cost = 0;
                pq.add(new Edge(startIndex,0));
                while(!pq.isEmpty()){
                    Edge e = pq.poll();                    
                    if(visited[e.goal])
                        continue;
                    visited[e.goal] = true;
                    min_cost += e.weight;
                    for(Edge searching : graph[e.goal]){
                        pq.add(searching);
                    }                
                }
                return String.valueOf(totalWeight - min_cost);            
                };
            
            while(true){
                StringTokenizer st = new StringTokenizer(br.readLine());
                m = Integer.parseInt(st.nextToken()); 
                n = Integer.parseInt(st.nextToken());
                if(m == 0 && n == 0){
                    break;
                }
                graph = new ArrayList[m];
                visited = new boolean[m];
                pq = new PriorityQueue<>();
                totalWeight = 0;
                for(int i = 0; i < m; i++){
                    graph[i] = new ArrayList<Edge>();
                }
                for(int i = 0; i < n; i++){
                    st = new StringTokenizer(br.readLine());
                    int firstHouse = Integer.parseInt(st.nextToken());
                    int secondHouse = Integer.parseInt(st.nextToken());
                    int weight = Integer.parseInt(st.nextToken());
                    totalWeight += weight;
                    graph[firstHouse].add(new Edge(secondHouse, weight));      
                    graph[secondHouse].add(new Edge(firstHouse, weight));      
                
            }
            bw.append(prim.get() + "\n");
            }
            bw.flush();
            bw.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }
    }
}
class Edge implements Comparable<Edge>{
    
    int goal;
    int weight;
    Edge(int goal, int weight){
        this.goal = goal;
        this.weight = weight;
    }
    @Override
    public int compareTo(Edge t){
        return this.weight - t.weight;
    }
}
