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
            answer = INF;
            BFS();
            System.out.println(answer);
            System.out.println(counter);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        
    static void BFS(){
        queue.offer(new Node(0, N));
        visited.set(N, 0);
        
//         N==K일 때 예외처리
        if(N==K){
            counter += 1;
            answer = 0;
            return;
        }
//         BFS
        while(!queue.isEmpty()){
            Node cur = queue.poll();            
            int[] actions = cur.getAction();
            int time = cur.getTime();
            
            for(int coordinate : actions){
                if(coordinate>Math.max(N,K)*2 || coordinate < 0)
                    continue;
                // System.out.println("좌표: " + coordinate + " 시간: " + (time +1));
                if(coordinate == K){
                    if(visited.get(K) == INF){
                        visited.set(K,time+1);
                        answer = time+1;
                        counter +=1;
                        continue;
                    }
                    if(answer < time+1){
                        break;
                    }                    
                    counter += 1;
                    continue;
                }
                if(visited.get(coordinate) < time+1)
                    continue;
                
                visited.set(coordinate, time+1);
                queue.offer(new Node(time +1, coordinate));
            }
        }
        
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
    public int[] getAction(){
        return new int[]{coordinate -1, coordinate +1, 2*coordinate};
    }
    @Override
    public int compareTo(Node target){
        return (this.time > target.time)
            ? 1
            : -1;
    }
}
