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
                inputGraph.add(new TreeSet<Integer>());
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
            // dfsHolder.setOrder(order);
            
            BFSHolder bfsHolder = new BFSHolder();
            bfsHolder.setGraph(inputGraph);
            bfsHolder.setVisited(visited);
            // bfsHolder.setOrder(order);
            
            dfsHolder.DFS(startNode);            
            dfsHolder.printAnswer();
            bfsHolder.BFS(startNode);
            bfsHolder.printAnswer();
                
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        

    
//     ==============
}    
class Holder{
    protected ArrayList<TreeSet<Integer>> graph;
    protected ArrayList<Boolean> visited;
    protected ArrayList<Integer> order;  
    protected ArrayList<Integer> order2 = new ArrayList<>();
    protected int counter = 1;
    
    public void setGraph(ArrayList<TreeSet<Integer>> graph){
        this.graph = graph;
    }
    
    public void setVisited(ArrayList<Boolean> visited){
        this.visited = new ArrayList<Boolean>(visited.size());
        this.visited.addAll(visited);
    }
    
    public void setOrder(ArrayList<Integer> order){
        this.order = new ArrayList<Integer>(order.size());
        this.order.addAll(order);
    }
    public void printAnswer(){
        Iterator<Integer> it = order2.iterator();
        // if(it.hasNext()){
        //     it.next();
        // }
        while(it.hasNext()){
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }    
}
class BFSHolder extends Holder{
    private LinkedList<Integer> queue = new LinkedList<>();
    
    
    public void BFS(int start){    
        queue.add(start);
        while(!queue.isEmpty()){                        
            int curNode = queue.poll();
            if(visited.get(curNode))
                continue;
            // order.set(curNode, new Integer(counter++));
            order2.add(curNode);
            visited.set(curNode, true);
            TreeSet<Integer> adjNodes = graph.get(curNode);
            for(Integer i : adjNodes){
                queue.add(i);
            }            
        }
    }        
}
class DFSHolder extends Holder{
    
    public void DFS(int start){
        // if(visited.get(start))
        //     return;        
        TreeSet<Integer> adjNodes = graph.get(start);
        visited.set(start, true);
        order2.add(start);        
        if(adjNodes.size() == 0){
            return;
        }        

        
        for(Integer i : adjNodes){
            if(!visited.get(i))
                DFS(i);
        }
    }

}
