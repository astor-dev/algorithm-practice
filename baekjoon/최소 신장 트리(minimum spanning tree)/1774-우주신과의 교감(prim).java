import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.math.BigInteger;

public class Main{

    /*
    minHeap PQ / ArrayList graph / ArrayList visited
    
    prim Algorithm
    이미 방문한 노드 visited에 넣어놓기
    visited에 true로 설정된 놈들과
    이어져있는 edges만 pq에 넣고
    추가로 이을때마다 pq에 edges 추가해주기
    */
    //     static variables
        static int n;
        static int m;
        static PriorityQueue<Edge> pq = new PriorityQueue<>();
        static boolean[] visited;
        static ArrayList<int[]> nodes; // index 0: id, 1: x, 2: y
        static double totalDistance = 0;
        static ArrayList<ArrayList<Edge>> graph;
    //     ================
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            
            //init
            nodes = new ArrayList<>(n+1);
            graph = new ArrayList<>(n+1);
            graph.add(null);
            visited = new boolean[n+1];
            visited[0] = true;
            nodes.add(new int[]{0,0,0});
            for(int i = 1; i <= n; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                nodes.add(new int[]{i,x,y});
                graph.add(new ArrayList<Edge>());
            }
            for(int i = 1; i <= n; i++){
                addAdjEdges(i);
            }            
            int startpoint = 0;
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());                
                startpoint = end;
                graph.get(start).add(new Edge(start, end, 0));
                graph.get(end).add(new Edge(end, start, 0));
            }
            visited[startpoint] = true;
            addPQ(graph.get(startpoint));
            while(!pq.isEmpty()){
                Edge e = pq.poll();
                if(visited[e.end])
                    continue;
                // System.out.println(e.end + "로 " + e.distance + "에 연결");
                totalDistance += e.distance;
                visited[e.end] = true;
                addPQ(graph.get(e.end));
            }
            System.out.printf("%.2f\n", totalDistance);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }        
    //     static methods     
    static void addPQ(ArrayList<Edge> ar){
        Iterator<Edge> it = ar.iterator();
        while(it.hasNext()){
            Edge e = it.next();
            if(visited[e.end])
                continue;
            pq.add(e);
        }        
    }
    static void addAdjEdges(int index){
        
        int stX = nodes.get(index)[1];
        int stY = nodes.get(index)[2];
        Iterator<int[]> nodeIt = nodes.iterator();
        while(nodeIt.hasNext()){
            int[] node = nodeIt.next();
            int id = node[0];
            int x = node[1];
            int y = node[2];
            
            if(visited[id])
                continue;
            
            double distance = Math.sqrt(
                Math.pow((stX - x), 2) +
                Math.pow((stY - y), 2)
            );
            
            graph.get(index).add(new Edge(index, id, distance));
        }
    }


    //     ==============
}
    //     class
class Edge implements Comparable<Edge>{
    int start;
    int end;
    double distance;
    
    Edge(int start, int end, double distance){
        this.start = start;
        this.end = end;
        this.distance = distance;
    }
    
    @Override
    public int compareTo(Edge target){
        if(distance == target.distance)
            return 0;
        return (distance > target.distance)?1:-1;
    }
}
    //     ==============
