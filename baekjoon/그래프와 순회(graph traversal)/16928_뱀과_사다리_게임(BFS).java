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
            
            int ledder = Integer.parseInt(st.nextToken());
            int snake = Integer.parseInt(st.nextToken());
            
            ArrayList<TreeSet<Integer>> graph =  new ArrayList<>();
            ArrayList<Boolean> visited = new ArrayList<>();
            
            for(int i = 0; i <= 100; i++){
                TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());
                set.add(i+1);
                set.add(i+2);
                set.add(i+3);
                set.add(i+4);
                set.add(i+5);
                set.add(i+6);
                graph.add(set);
                visited.add(false);
            }
            for(int i = 0; i < ledder; i++){
                st = new StringTokenizer(br.readLine());
                int startNode = Integer.parseInt(st.nextToken());
                int endNode = Integer.parseInt(st.nextToken());
                TreeSet<Integer> set = new TreeSet<>();
                set.add(endNode);
                graph.set(startNode, set);
            }
            for(int i = 0; i < snake; i++){
                st = new StringTokenizer(br.readLine());
                int startNode = Integer.parseInt(st.nextToken());
                int endNode = Integer.parseInt(st.nextToken());
                TreeSet<Integer> set = new TreeSet<>();
                set.add(endNode);
                graph.set(startNode, set);
            }            
            /*
            BFS를 만드는데 queue로 돌리자
            queue는 선입선출 linkedlist로 하는데 각 원소들이 지닌 값은
            (도착지점, 카운터)이다.
            queue에 방문할때마다 카운터 1씩 늘려주자
            100에 방문하는경우 return counter
            
            기본적으로 graph는 자신의 index +1~+6의 값이 edge로 연결되어있음. !일방향!
            허나 뱀 or 사다리로 연결되어있는 경우 다른간선 없애고 도착지만 냅둠
            
            */
            BFS game = new BFSBuilder()
                .setVisited(visited)
                .setGraph(graph)
                .build();
            
            int counter = game.bfs();
            System.out.println(counter);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        

//     ==============
}    
class Node implements Comparable<Node>{
    private int location;
    private int counter;
    
    Node(int location, int counter){
        this.location = location;
        this.counter = counter;
    }
    
    public int getLocation(){return location;}
    public int getCounter(){return counter;}
    
    @Override
    public int compareTo(Node other){
        return this.counter - other.counter;
    }
}
class BFS{
    private ArrayList<Boolean> visited;
    private ArrayList<TreeSet<Integer>> graph;

    BFS(ArrayList<Boolean> visited, ArrayList<TreeSet<Integer>> graph){
        this.visited = visited;
        this.graph = graph;
    }
    public int bfs(){
        PriorityQueue<Node> Queue = new PriorityQueue<>();
        Queue.add(new Node(1, 0)); 
        while(!Queue.isEmpty()){
            Node curNode = Queue.poll();
            int location = curNode.getLocation();
            int counter = curNode.getCounter();
            // System.out.println("location: " + location + " / counter: " + counter);            
            if(location == 100){
              return counter;  
            }
            visited.set(location, true);
            
            TreeSet<Integer> edges = graph.get(location);
            for(Integer destination : edges){
                if(destination > 100){
                    continue;
                }
                if(!visited.get(destination)){
                    if(edges.size() == 1){
                        Queue.add(new Node(destination, counter));                        
                    } else{
                        Queue.add(new Node(destination, counter+1));                                                
                    }
                }
            }
        }
        return 0;
    }
    public void printAnswer(){

    }
}

class BFSBuilder{
    private ArrayList<Boolean> visited;
    private ArrayList<TreeSet<Integer>> graph;
    private ArrayList<Integer> order;

    public BFSBuilder setVisited(ArrayList<Boolean> visited){
        this.visited = visited;
        return this;
    }

    public BFSBuilder setGraph(ArrayList<TreeSet<Integer>> graph){
        this.graph = graph;
        return this;
    }

    public BFS build(){
        return new BFS(visited, graph);
    }
}
