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
            Dijkstra dijkstra = new Dijkstra();
            dijkstra.getEdges();
            dijkstra.start();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        
//     ==============
}    

class Dijkstra{
    private ArrayList<ArrayList<Edge>> graph;
    private CourseManager cm;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;
    private int nOfNode;    
    private int nOfEdge;
    private PriorityQueue<Edge> pq;
    
    Dijkstra(){
        try{
            nOfNode = Integer.parseInt(br.readLine());            
            nOfEdge = Integer.parseInt(br.readLine());
            cm = new CourseManager(nOfNode);    
            graph = new ArrayList<>(nOfNode+1);   
            pq = new PriorityQueue<>();
            for(int i = 0; i<=nOfNode; i++){
                graph.add(new ArrayList<Edge>());
            }
        }catch(IOException e){}
    }
    
    public void getEdges(){
        for(int i = 0; i<nOfEdge; i++){
            try{
                st = new StringTokenizer(br.readLine());              
                int startNode = Integer.parseInt(st.nextToken());
                int targetNode = Integer.parseInt(st.nextToken());
                int distance = Integer.parseInt(st.nextToken());
                graph.get(startNode).add(new Edge(distance, targetNode));
            } catch(IOException e){}
        }
    }
    
    public void start(){
        try{
            st = new StringTokenizer(br.readLine());
            int startPoint = Integer.parseInt(st.nextToken());
            int goal = Integer.parseInt(st.nextToken());
            
            ArrayList<Integer> initialCourse = new ArrayList<>();
            initialCourse.add(startPoint);
            cm.update(0, startPoint, initialCourse);
            pq.offer(new Edge(0, startPoint));
            while(!pq.isEmpty()){
                Edge e = pq.poll();  
                if(e.getDistance() > cm.getDistance(e.getTargetNode()))
                    continue;                
                for(Edge searchingE : graph.get(e.getTargetNode())){
                    int tempDistance = searchingE.getDistance() + e.getDistance();
                    if(cm.ifShorter(tempDistance, searchingE.getTargetNode())){
                        ArrayList<Integer> course = new ArrayList<Integer>();
                        course.addAll(cm.getCourse(e.getTargetNode()));                        
                        course.add(searchingE.getTargetNode());
                        // System.out.println("now searching: " + searchingE.getTargetNode() + ", course: " + course.toString());                        
                        cm.update(tempDistance, searchingE.getTargetNode(), course);
                        pq.offer(new Edge(tempDistance, searchingE.getTargetNode()));
                    }
                }                
            }
            System.out.println(cm.getDistance(goal));
            System.out.println(cm.getCourse(goal).size());
            Iterator<Integer> it = cm.getCourse(goal).iterator();
            while(it.hasNext()){
                System.out.print(it.next() + " ");
            }
            System.out.println();
            
        } catch(IOException e){}
    }
}
class CourseManager{
    private ArrayList<Integer> distance;
    private ArrayList<ArrayList<Integer>> course;
    private int INF = Integer.MAX_VALUE;
    
    CourseManager(int size){
        distance = new ArrayList<Integer>(size+1);
        course = new ArrayList<>(size+1);
        for(int i =0;i<size+1;i++){
            course.add(new ArrayList<Integer>());
            distance.add(INF);
        }
    }
    
    public boolean ifShorter(int inputDistance, int targetNode){
        return (inputDistance < distance.get(targetNode));
    }
    
    public void update(int inputDistance, int targetNode, ArrayList<Integer> inputCourse){
        distance.set(targetNode, inputDistance);
        course.set(targetNode, inputCourse);
    }    
    
    public int getDistance(int targetNode){
        return distance.get(targetNode);        
    }
    
    public ArrayList<Integer> getCourse(int targetNode){
        return course.get(targetNode);
    }
}
class Edge implements Comparable<Edge>{
    private int distance;
    private int targetNode;
    
    Edge(int distance, int targetNode){
        this.distance = distance;
        this.targetNode = targetNode;
    }
    
    @Override
    public int compareTo(Edge t){
        return this.distance - t.distance;
    }
    
    public int getDistance(){
        return distance;
    }
    
    public int getTargetNode(){
        return targetNode;
    }
}
