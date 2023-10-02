import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{

//     static variables
    static int n;
    static int maxNode;
    static int maxWeight;
    static ArrayList<ArrayList<Edge>> graph;
    static ArrayList<Boolean> visited;
//     ================
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;
            
            n = Integer.parseInt(br.readLine());
            graph = new ArrayList<>(n+1);
            visited = new ArrayList<>(n+1);
            for(int i = 0; i<=n; i++){
                graph.add(new ArrayList<Edge>());
            }
            
            for(int i=0; i<n; i++){                
                st = new StringTokenizer(br.readLine());
                int nodeNumber = Integer.parseInt(st.nextToken());                
                while(true){
                    int connectedNode = Integer.parseInt(st.nextToken());
                    if(connectedNode == -1)
                        break;
                    int weight = Integer.parseInt(st.nextToken());                    
                    graph.get(nodeNumber).add(new Edge(connectedNode, weight));
                }
            }
            Edge start = new Edge(1,0);
            initDFS();            
            DFS(start);
            
            start = new Edge(maxNode, 0);
            initDFS();        
            DFS(start);
            
            System.out.println(maxWeight);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }    
//     static methods     
static void initDFS(){    
    visited = new ArrayList<Boolean>();
    for(int i = 0; i<= n; i++){
        visited.add(false);
    }
    maxWeight = 0;
    maxNode = 0;
}    
static void DFS(Edge e){
    int node = e.getNode();
    int weight = e.getWeight();
    
    if(weight > maxWeight){
        maxWeight = weight;
        maxNode = node;
    }
    visited.set(node, true);
    ArrayList<Edge> adjNodes = graph.get(node);
    
    for(Edge searchingE : adjNodes){
        int searchingNode = searchingE.getNode();
        if(!visited.get(searchingNode)){
            int totalWeight = searchingE.getWeight() + weight;
            DFS(new Edge(searchingNode, totalWeight));
        }
    }
}
//     ==============
}    
class Edge{
    private int weight;
    private int node;
    
    Edge(int node, int weight){
        this.node = node;
        this.weight = weight;
    }
    
    public int getNode(){
        return node;
    }
    public int getWeight(){
        return weight;
    }
    
    @Override    
    public String toString(){
        return String.format("(node: %d, weight: %d)", node, weight);
    }
}
