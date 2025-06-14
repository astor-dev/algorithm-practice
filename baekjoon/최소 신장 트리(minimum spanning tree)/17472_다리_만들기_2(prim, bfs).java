import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.math.BigInteger;

public class Main{
    static int n;
    static int m;
    static ArrayList<ArrayList<Edge>> edges;
    static boolean[][] BFS_visited;
    static int idCounter = 0;
    static ArrayList<ArrayList<Island>> islandGraph = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            BFS_visited = new boolean[n][m];
            int[][] graph = new int[n][m];            
            for(int i = 0; i < n; i++){
                ArrayList<Island> islandGraphRow = new ArrayList<Island>();
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < m; j++){
                    islandGraphRow.add(new Island());
                    int value = Integer.parseInt(st.nextToken());
                    graph[i][j] = value;
                    if(value == 0){
                        BFS_visited[i][j] = true;
                    }
                }
                islandGraph.add(islandGraphRow);
            }
//             BFS로 섬 단위 판별하기
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    BFS(new int[]{i,j});
                }
            }
            edges = new ArrayList<>(idCounter);
            for(int i = 0; i < idCounter; i++){
                edges.add(new ArrayList<Edge>());
            }
//             그래프 가로 edge구하기
            for(int i = 0; i < n; i++){
                boolean flag = true;
                int from = 0;
                int to = 0;
                int counter = 0;
                for(int j = 0; j < m; j++){
                    counter += 1;                    
                    if(!islandGraph.get(i).get(j).isBlank){
                        if(flag){
                            from = islandGraph.get(i).get(j).id;                              
                            counter = 0;
                            flag = false;
                        } else{
                            to = islandGraph.get(i).get(j).id;
                            if(from == to){
                                counter = 0;
                                continue;
                            }
                            if(counter == 2){
                                from = to;
                                counter = 0;
                                continue;
                            }          
                            edges.get(from).add(new Edge(to, counter-1));
                            edges.get(to).add(new Edge(from, counter-1));
                            from = to;
                            counter = 0;
                        }
                    }

                }
            }
            
//             그래프 세로 edge구하기
            for(int i = 0; i < m; i++){
                boolean flag = true;
                int from = 0;
                int to = 0;
                int counter = 0;
                for(int j = 0; j < n; j++){
                    counter += 1;                    
                    if(!islandGraph.get(j).get(i).isBlank){
                        if(flag){
                            from = islandGraph.get(j).get(i).id;                              
                            counter = 0;
                            flag = false;
                        } else{
                            to = islandGraph.get(j).get(i).id;
                            if(from == to){
                                counter = 0;
                                continue;
                            }
                            if(counter == 2){
                                from = to;
                                counter = 0;                                
                                continue;
                            }                                    
                            edges.get(from).add(new Edge(to, counter-1));
                            edges.get(to).add(new Edge(from, counter-1));
                            from = to;
                            counter = 0;
                        }
                    }

                }
            }           
            for(ArrayList<Edge> ar : edges){
                for(Edge e : ar){
                    // System.out.println(e.goal + "를 향해 " + e.distance);
                }
            }
//             index == 0 인 섬을 시작으로 prim알고리즘
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.add(new Edge(0, 0));
            boolean[] visited = new boolean[idCounter];
            int totalDistance = 0;
            while(!pq.isEmpty()){
                Edge e = pq.poll();
                if(visited[e.goal])
                    continue;                
                visited[e.goal] = true;
                totalDistance += e.distance;
                // System.out.println(e.goal + "을 향해 " + e.distance + "의 다리 건설");
                for(Edge eg : edges.get(e.goal)){
                    if(!visited[eg.goal])
                        pq.add(eg);
                }
            }
            for(boolean vis : visited){
                if(!vis){
                    System.out.println("-1");
                    return;
                }
            }
            System.out.println(totalDistance);                   
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }
    }    
    

    static void BFS(int[] xy){
        if(BFS_visited[xy[0]][xy[1]])
            return;
        
        int[][] dxys = new int[][]{
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
        };
        Queue<int[]> queue = new LinkedList<>();
        
        
        queue.add(xy);
        Island island = new Island(idCounter++);
        islandGraph.get(xy[0]).set(xy[1], island);        
        while(!queue.isEmpty()){
            int[] s_xy = queue.poll();
            for(int[] dxy : dxys){
                int dx = s_xy[0] + dxy[0];
                int dy = s_xy[1] + dxy[1];
                
                if(dx < 0 || dx >= n || dy < 0 || dy >= m){
                    continue;
                }
                if(BFS_visited[dx][dy])
                    continue;
                queue.add(new int[]{dx, dy});
                BFS_visited[dx][dy] = true;
                islandGraph.get(dx).set(dy, island);
            }
        }
    }    
}

class Island{
    int id;
    boolean isBlank = true;
    
    Island(){
        this.id = -1;        
    }
    Island(int id){
        this.id = id;
        this.isBlank = false;
    }
}
class Edge implements Comparable<Edge>{
    int goal;
    int distance;
    Edge(int goal, int distance){
        this.goal = goal;
        this.distance = distance;
    }
    
    @Override
    public int compareTo(Edge target){
        return this.distance - target.distance;
    }
}
