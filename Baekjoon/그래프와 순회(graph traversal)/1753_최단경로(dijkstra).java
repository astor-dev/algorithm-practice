import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 

    static ArrayList<Node>[] graph;
    static int[] d;
    static int N, M, start;
    static final int INF = Integer.MAX_VALUE;
    static PriorityQueue<Node>pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        try{   
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            start = Integer.parseInt(br.readLine());
            graph = new ArrayList[N+1];
            for(int i = 0; i<N+1; i++){
                graph[i] = new ArrayList<Node>();
            }
            for(int i = 0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int startNode = Integer.parseInt(st.nextToken());
                int endNode = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());            
                graph[startNode].add(new Node(endNode, weight));         
            }
            d = new int[N+1];
            Arrays.fill(d, INF);
            dijkstra();        

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }  
    static void dijkstra(){
        
        graph[start].add(new Node(start, 0));
        pq.offer(new Node(start, 0));
        
        while(!pq.isEmpty()){
            Node n = pq.poll();
            if(n.getDistance() > d[n.getNode()]) continue;
            for(Node tempNode : graph[n.getNode()]){
                int tempDistance = n.getDistance() + tempNode.getDistance();
                if(tempDistance < d[tempNode.getNode()]){
                    d[tempNode.getNode()] = tempDistance;
                    pq.offer(new Node(tempNode.getNode() ,tempDistance));
                }
            }
        }
        for(int i = 1; i<d.length; i++){
            if(d[i] == INF){
                System.out.println("INF");
            } else{
                System.out.println(d[i]);
            }
        }
    }
    
}
class Node implements Comparable<Node>{
    private int node;
    private int distance;
    
    Node(int node, int distance){
        this.node = node;
        this.distance = distance;
    }
    public int getNode(){
        return node;
    }
    
    public int getDistance(){
        return distance;
    }
    @Override
    public int compareTo(Node target){
        return (this.distance > target.distance)
        ? 1
        : -1;
    }
}
