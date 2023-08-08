import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 

//     static variables
    static int N, M, X;
    static ArrayList<Node>[] graph;   
    static final int INF = Integer.MAX_VALUE;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
//     ================
    public static void main(String[] args) throws Exception {
        try{                           
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //노드 수
            M = Integer.parseInt(st.nextToken()); //엣지 수(유향)
            X = Integer.parseInt(st.nextToken()); //목적지 노드
            
            graph = new ArrayList[N+1];
            for(int i = 0; i < N+1; i++){
                graph[i] = new ArrayList<Node>();
            }            
            for(int i = 0; i< M; i++){
                st = new StringTokenizer(br.readLine());
                int startNode = Integer.parseInt(st.nextToken());
                int endNode = Integer.parseInt(st.nextToken());
                int distance = Integer.parseInt(st.nextToken());                
                graph[startNode].add(new Node(endNode, distance));
            }
            
            ArrayList<int[]> result = new ArrayList<>(N+1);
            result.add(new int[N+1]);
            
            for(int i = 1; i<N+1; i++){
                int[] dArr = new int[N+1];
                
                for(int j = 0; j<N+1; j++){
                    dArr[j] = INF;
                }                
                
                result.add(dijkstra(i, dArr).clone());

            }
            int[] answer = new int[N+1];
            for(int i = 1; i<N+1; i++){
                answer[i] = result.get(i)[X] + result.get(X)[i];
            }
            System.out.println(Arrays.stream(answer).max().getAsInt());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods    
    static int[] dijkstra(int start, int[] dArr){
        dArr[start] = 0;
        pq.offer(new Node(start, 0));
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int currentNode = node.getNode();
            int distance = node.getDistance();
            for(Node nextNode : graph[currentNode]){
                int nextDistance = nextNode.getDistance() + distance;
                if(nextDistance < dArr[nextNode.getNode()]){
                    dArr[nextNode.getNode()] = nextDistance;
                    pq.offer(new Node(nextNode.getNode(), nextDistance));
                }
            }
        }       
        return dArr;
    }
//     ==============
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
            ?1
            :-1;
    }
}
