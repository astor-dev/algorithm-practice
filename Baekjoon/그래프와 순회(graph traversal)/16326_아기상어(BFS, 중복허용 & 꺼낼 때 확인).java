import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{

//     static variables
    static int n;
    static ArrayList<ArrayList<Integer>> graph;
    static ArrayList<ArrayList<Boolean>> visited;    
    static int sharkX;
    static int sharkY;
    static int sharkWeight;
    static int eat;
    static int totalDistance;
    static final int[][] MOVE = new int[][]{
        {-1, 0},
        {0, -1},                        
        {0, 1},        
        {1, 0}
    };
//     ================
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;
            
            n = Integer.parseInt(br.readLine());
            
            graph = new ArrayList<>(n);
            
            //init
            for(int x = 0; x<n; x++){
                st = new StringTokenizer(br.readLine());
                ArrayList<Integer> graphRow = new ArrayList<Integer>(n);
                for(int y = 0; y<n; y++){                                        
                    int weight = Integer.parseInt(st.nextToken());
                    if(weight == 9){
//                         params 초기화
                        sharkX = x;
                        sharkY = y;
                        sharkWeight = 2;
                        eat = 0;
                        totalDistance = 0;                        
                        graphRow.add(0);
                    } else{
                        graphRow.add(weight);                        
                    }
                }
                graph.add(graphRow);
            }
            boolean flag = true;
            while(flag){
                initVisited();
                flag = BFS();
            }            
            System.out.println(totalDistance);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }    
//     static methods     
    static void initVisited(){
        visited = new ArrayList<>(n);        
        for(int i = 0; i < n; i++){
            ArrayList<Boolean> visitedRow = new ArrayList<Boolean>(n);            
            for(int j = 0; j < n; j++){
                visitedRow.add(false);                                    
            }
            visited.add(visitedRow);            
        }
    }
    
    static boolean BFS(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(sharkX, sharkY, 0));
        while(!pq.isEmpty()){

            // System.out.println(pq.toString());
            Node node = pq.poll();            
            if(visited.get(node.x).get(node.y)){
                continue;
            }            
            int distance = node.distance;
            visited.get(node.x).set(node.y, true);
            
//                 만약 물고기면 먹고 리턴
            if(graph.get(node.x).get(node.y) != 0 && graph.get(node.x).get(node.y) < sharkWeight){
                totalDistance += distance;
                // System.out.println("(" + node.x + ", " + node.y + ") 에서 " + graph.get(node.x).get(node.y) + "를 냠" + distance + "/" + totalDistance);
                eat += 1;
                sharkX = node.x;
                sharkY = node.y;
                graph.get(node.x).set(node.y, 0);
                if(eat == sharkWeight){
                    sharkWeight += 1;
                    eat = 0;
                }

                return true;                    
            }     
            distance +=1;
//             무빙
            for(int[] move : MOVE){
                int moveX = node.x + move[0];
                int moveY = node.y + move[1];
//                 그래프에서 나가면 컷
                if(moveX < 0 ||(moveX >= n) ||moveY < 0 ||(moveY >= n))
                    continue;       
//                 방문한 적 있으면 컷
                if(visited.get(moveX).get(moveY))
                    continue;                
//                 상어보다 크기가 크면 컷
                if(sharkWeight < graph.get(moveX).get(moveY))
                    continue;                
//                 아니면 pq에 추가
                pq.add(new Node(moveX, moveY, distance));
            }            
        }
        return false;                
    }
//     ==============
}

class Node implements Comparable<Node>{
    int x;
    int y;
    int distance;
    
    Node(int x, int y, int distance){
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
    @Override
    public String toString(){
        return String.format("x: %d, y: %d, distance: %d", x, y, distance);
    }
    
    @Override
    public int compareTo(Node target){        
        if(this.distance != target.distance){
            return this.distance - target.distance;
        } else if(this.x != target.x){
            return this.x - target.x;
        } else{
            return this.y - target.y;
        }
    }
}
