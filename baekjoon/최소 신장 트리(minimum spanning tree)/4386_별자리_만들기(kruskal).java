import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.math.BigInteger;

public class Main{

    //     static variables
        static int n;
        static PriorityQueue<Edge> pq = new PriorityQueue<>();
        static int id = 0;
        static ArrayList<Integer> graph;
    //     ================
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;
            n = Integer.parseInt(br.readLine());            
            graph = new ArrayList<Integer>(n+1);
            graph.add(0);
            
            Stack<Node> stack = new Stack<>();
            for(int i = 0; i < n; i++){
                graph.add(i+1);
                st = new StringTokenizer(br.readLine());
                double x = Double.valueOf(st.nextToken());
                double y = Double.valueOf(st.nextToken());
                Node n = new Node(x, y, i+1);
                stack.add(n);
            }                        
            
            Iterator<Node> it = stack.iterator();
            while(it.hasNext()){
                Iterator<Node> it2 = stack.iterator();
                Node n = it.next();
                while(it2.hasNext()){
                    Edge e = new Edge();
                    e.init(n, it2.next());
                    pq.add(e);
                }
            }
            double answer = 0;
            // System.out.println(pq.toString());
            while(!pq.isEmpty()){
                Edge e = pq.poll();
                if(union(e)){
                    continue;
                }
                answer += e.getDistance();
            }
            System.out.printf("%.4f", answer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }    
    //     static methods     
    static int find(int x){
        if(x == graph.get(x))
            return x;
        
        return find(graph.get(x));
    }
    static boolean union(Edge e){
        int x = e.getX();
        int y = e.getY();
        x = find(x);
        y = find(y);
        
        if(x == y){
            return true;
        }
        if(x < y){
            graph.set(y, x);
            return false;
        }
        graph.set(x, y);
        return false;
        
    }


    //     ==============
}
    //     class
class Node{
    private double x;
    private double y;
    private int id;
    Node(double x, double y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
    }        
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public int getID(){
        return id;
    }
}
class Edge implements Comparable<Edge>{
    private Node startNode;
    private Node endNode;
    private double distance;
    
    Edge(){}
    public void init(Node n1, Node n2){
        this.startNode = n1;
        this.endNode = n2;
        this.distance = 
            Math.sqrt(
            Math.pow((n1.getX() - n2.getX()),2) +
            Math.pow((n1.getY() - n2.getY()),2)
        );            
    }
    public double getDistance(){
        return distance;
    }
    public int getX(){
        return startNode.getID();
    }
    public int getY(){
        return endNode.getID();
    }
    @Override
    public int compareTo(Edge target){
        if(this.distance == target.getDistance())
            return 0;
        return (this.distance > target.getDistance()) ? 1: -1;
    }
    
    @Override
    public String toString(){
        return String.format("[%d, %d] distance: %f", getX(), getY(), distance);
    }
}
    //     ==============
