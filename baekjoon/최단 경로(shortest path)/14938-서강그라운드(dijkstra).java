import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{

//     static variables
    static int n;
    static int m;
    static int r;
    static ArrayList<Integer> itemCounts;
    static ArrayList<ArrayList<int[]>> graph;
//     ================
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;
            
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            
            graph = new ArrayList<>(n+1);
            itemCounts = new ArrayList<>(n+1);
            graph.add(null); // index 0 값
            itemCounts.add(null); //index 0 값
            
            st = new StringTokenizer(br.readLine());            
            
            for(int i = 0; i < n; i++){
                int items = Integer.parseInt(st.nextToken());
                itemCounts.add(items);
                graph.add(new ArrayList<int[]>());
            }
            for(int i = 0; i < r; i++){
                st = new StringTokenizer(br.readLine());                            
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());   
                //무향그래프
                graph.get(start).add(new int[]{end, weight});
                graph.get(end).add(new int[]{start, weight});                
            }
            int maxItem = 0;
            for(int i = 1; i <= n; i++){
                maxItem = Math.max(maxItem, dijkstra(i));
            }
            System.out.println(maxItem);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }    
    //     static methods     
    static int dijkstra(int start){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
//         최단거리 그래프 초기화
        ArrayList<Integer> shortestGraph = new ArrayList<>(n+1);
        shortestGraph.add(null);
        for(int i = 0; i < n; i++){
            shortestGraph.add(Integer.MAX_VALUE);
        }
        
//         시작점 설정
        shortestGraph.set(start, 0);
        pq.offer(new Edge(start, 0));
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            Iterator<int[]> it = graph.get(e.node).iterator();
            while(it.hasNext()){
                int[] s = it.next();
                int searchingNode = s[0];
                int searchingD = s[1];
                
                int newD = searchingD + e.d;                
                if(shortestGraph.get(searchingNode) > newD){
                    shortestGraph.set(searchingNode, newD);
                    pq.offer(new Edge(searchingNode, newD));
                }
            }
        }
        
        Iterator<Integer> it = shortestGraph.iterator();
        it.next();
        int index = 1;
        int sum = 0;
        while(it.hasNext()){
            int weight = it.next();
            if(m >= weight){
                sum+=itemCounts.get(index);                
            }
            index++;
        }        
        return sum;
    }
    //     ==============
}

class Edge implements Comparable<Edge>{
    int node;
    int d;
    Edge(int node, int d){
        this.node = node;
        this.d = d;
    }
    
    @Override
    public int compareTo(Edge target){
        return(this.d - target.d);
    }
    
}
