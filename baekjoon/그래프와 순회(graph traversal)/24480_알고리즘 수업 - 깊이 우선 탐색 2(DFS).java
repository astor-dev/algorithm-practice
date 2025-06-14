import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 

//     static variables

//     ================
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int numbersOfNodes = Integer.parseInt(st.nextToken());
            int numbersOfEdges = Integer.parseInt(st.nextToken());
            int startNode = Integer.parseInt(st.nextToken());
            
            ArrayList<TreeSet<Integer>> inputGraph = new ArrayList<>();            
            ArrayList<Boolean> visited = new ArrayList<>();
            ArrayList<Integer> order = new ArrayList<>();
            
            for(int i = 0; i <= numbersOfNodes; i++){
                inputGraph.add(new TreeSet<Integer>(Collections.reverseOrder()));
                visited.add(false);
                order.add(0);
            }        
            for(int i = 0; i<numbersOfEdges; i++){
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                inputGraph.get(node1).add(node2);
                inputGraph.get(node2).add(node1);
            }
            DFSHolder dfsHolder = new DFSHolder();
            dfsHolder.setGraph(inputGraph);
            dfsHolder.setVisited(visited);
            dfsHolder.setOrder(order);
            
            dfsHolder.DFS(startNode);
            dfsHolder.printAnswer();
                
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        

    
//     ==============
}    
class DFSHolder{
    private ArrayList<TreeSet<Integer>> graph;
    private ArrayList<Boolean> visited;
    private ArrayList<Integer> order;
    private int counter = 1;
    
    public void setGraph(ArrayList<TreeSet<Integer>> graph){
        this.graph = graph;
    }
    
    public void setVisited(ArrayList<Boolean> visited){
        this.visited = visited;
    }
    
    public void setOrder(ArrayList<Integer> order){
        this.order = order;
    }
    
    public void DFS(int start){
        if(visited.get(start))
            return;        
        visited.set(start, true);
        order.set(start, new Integer(counter++));
        
        TreeSet<Integer> adjNodes = graph.get(start);
        for(Integer i : adjNodes){
            DFS(i);
        }
    }
    public void printAnswer(){
        Iterator<Integer> it = order.iterator();
        if(it.hasNext()){
            it.next();
        }
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
