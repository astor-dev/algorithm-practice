import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 

//     static variables
    static int N;
    static int K;
    static int counter = 0;
    static final int INF = Integer.MAX_VALUE;    
    static ArrayList<Integer> visited = new ArrayList<>();
    static PriorityQueue<Node> queue = new PriorityQueue<>();    
    static int answer;
//     ================
    public static void main(String[] args) throws Exception {
        try{                           
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //수빈이 위치
            K = Integer.parseInt(st.nextToken()); //동생 위치            
            /* 수빈이의 행동
            1. 걷기: 1초후 +- 1
            2. 순간이동: 1초후 2X
            가장 빠른 시간 and 경우의 수 측정
            그래프를 그리는데 BFS, queue에 넣으면서 ㄱㄱ
            수빈이 위치를 큐에 넣음
            큐에서 빼면서 x2 or +-1을 함
            해당 위치에 대한 시간 소요량을 거리그래프에 넣음(기존 값보다 작을경우!)
            작은경우 그 거리 값을 큐에 넣음
            */            
            for(int i = 0; i< Math.max(2*N+1, 2*K+1); i++){
                visited.add(INF);
            }
            System.out.println(BFS());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        
    static int BFS(){
        queue.offer(new Node(0, N));
        visited.set(N, 0);
        
//         N==K일 때 예외처리
        if(N==K){

            return 0;
        }
//         BFS
        while(!queue.isEmpty()){
            Node cur = queue.poll();            
            Node[] actions = cur.getAction();
            int time = cur.getTime();
            
            for(Node target : actions){
                
                int coordinate = target.getCoordinate();
                int targeted_time = target.getTime();
                
                if(coordinate>Math.max(N,K)*2 || coordinate < 0)
                    continue;
                if(visited.get(coordinate) <= targeted_time)
                    continue;
                if(visited.get(K) < targeted_time){
                    return visited.get(K);
                }
                visited.set(coordinate, targeted_time);
                // System.out.println("좌표: " + coordinate + " 시간: " + targeted_time);                
                queue.offer(new Node(targeted_time, coordinate));
            }
        }
        return visited.get(K);
        
    }
    
//     ==============
}    

class Node implements Comparable<Node>{
    private int time;
    private int coordinate;
    
    Node(int time, int coordinate){
        this.time = time;
        this.coordinate = coordinate;
    }
    
    public int getTime(){
        return time;
    }
    public int getCoordinate(){
        return coordinate;
    }
    public Node[] getAction(){
        return new Node[]{new Node(time, 2*coordinate),new Node(time +1, coordinate -1),new Node(time +1, coordinate +1)};
//         순서가 중요함!!! 0초만에 이동하는 순간이동 노드를 제일 먼저 데큐에 넣음!!
    }
    @Override
    public int compareTo(Node target){
        return (this.time > target.time)
            ? 1
            : -1;
    }
}
