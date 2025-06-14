import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{

//     static variables
    
//     ================
    public static void main(String[] args) throws Exception {
        try{                    
            // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            // StringTokenizer st;
            MST mst = new MST();
            
            mst.init();
            mst.getEdges();
            mst.kruskal();
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        
//     ==============
}    

class MST{
    private int nOfNode;
    private int nOfEdge;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;
    private ArrayList<Integer> UFList;
    private PriorityQueue<Edge> edges;
    
    MST(){}
    
    public void init(){
        try{
            st = new StringTokenizer(br.readLine());
            nOfNode = Integer.parseInt(st.nextToken());
            nOfEdge = Integer.parseInt(st.nextToken());
            edges = new PriorityQueue<>(nOfEdge);
            
            UFList = new ArrayList<Integer>(nOfNode + 1);
            for(int i = 0; i<= nOfNode; i++){
                UFList.add(i);
            }
        } catch(IOException e){}
    }
    
    
    public void getEdges(){
        for(int i = 0; i <nOfEdge; i++){
            try{
                st = new StringTokenizer(br.readLine());
                Edge e = new Edge()
                    .setNode1(Integer.parseInt(st.nextToken()))
                    .setNode2(Integer.parseInt(st.nextToken()))
                    .setWeight(Integer.parseInt(st.nextToken()));
                
                edges.add(e);                
            } catch(IOException e){}
        }
    }
    
    public void kruskal(){
        int counter = 0;        
        int totalWeight = 0;
        while(counter != (nOfNode -1)){
            Edge e = edges.poll();            
            if(union(e.getNode1(), e.getNode2())){
                counter+=1;
                totalWeight+=e.getWeight();
            }
        }
        System.out.println(totalWeight);
    }
    
    private boolean union(int x, int y){
        x = find(x);
        y = find(y);
        
        if(x==y){
            return false;
        }
        
        if(x < y){
            UFList.set(y,x);
            return true;
        }
        UFList.set(x,y);
        return true;
    }
    
    private int find(int x){
        if(x == UFList.get(x))
            return x;
        
        return find(UFList.get(x));
    }
}

class Edge implements Comparable<Edge>{
    private int node1;
    private int node2;
    private int weight;
    
    public int getNode1(){
        return node1;
    }
    
    public int getNode2(){
        return node2;
    }
    public int getWeight(){
        return weight;
    }
    public Edge setNode1(int node1){
        this.node1 = node1;
        return this;
    }
    
    public Edge setNode2(int node2){
        this.node2 = node2;
        return this;
    }
    
    public Edge setWeight(int weight){
        this.weight = weight;
        return this;
    }
    
    @Override
    public int compareTo(Edge target){
        return this.weight - target.weight;
    }
}
