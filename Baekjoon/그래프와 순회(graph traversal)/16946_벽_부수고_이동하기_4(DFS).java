import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.math.BigInteger;

public class Main{

    //     static variables
    static int n;
    static int m;
    static int[][] graph;
    static boolean[][] visited;    
    static Node[][] answerGraph;
    static int[][] directions = new int[][]{
            {0,1},
            {0,-1},
            {1,0},
            {-1,0}
        };
    
    //     ================
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;
            /*
            그래프를 int[][]에 저장(크기가 정해져있으니)
            visited배열 만들고
            for문으로 한번 훑으면서 BFS를하고 BFS로 탐색된 애들에게 모두 동일한 class인 Node를 주자
            class Node:
            int counter        
            */
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            graph = new int[n][m];
            visited = new boolean[n][m];
            answerGraph = new Node[n][m];
            
            for(int i = 0; i < n; i++){
                String string = br.readLine();
                for(int j = 0; j < m; j++){
                    graph[i][j] = Integer.parseInt("0" + string.charAt(j));                    
                }
            }
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    BFS(new int[]{i,j});
                }
            }            
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
//                     벽 아니면 넘기기
                    if(graph[i][j] == 0){
                        bw.append("0");
                        continue;
                    }
//                     주변 노드를 저장할 set. node의 주소가 같다 == 같은 빈공간을 공유하는 노드다. 중복 허용 X 하는 set으로 필터링
                    HashSet<Node> set = new HashSet<>();                    
//                     4방향 순회하며 node를 저장
                    for(int[] d : directions){
                        int dx = i + d[0];
                        int dy = j + d[1];
                        if(dx < 0 || dy < 0 || dx >= n || dy >= m)
                            continue;          
                        if(graph[dx][dy] == 1)
                            continue;                        
                        set.add(answerGraph[dx][dy]);
                    }
                    int sum = 1;
                    for(Node node : set){
                        // System.out.print("value: " + node.value);
                        sum+=node.value;
                    
                    }
                    // System.out.println(set.toString());
                    bw.append((sum%10)+"");
                }
                bw.append("\n");
            }              
            bw.flush();
            bw.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }    
    //     static methods     
    static void BFS(int[] start){
        if(graph[start[0]][start[1]] == 1){
            answerGraph[start[0]][start[1]] = new Node(0);
            return;
        }
        if(visited[start[0]][start[1]])
            return;
        
        Queue<int[]> queue = new LinkedList<>();
        Node node = new Node(1);
        queue.add(start);  
        visited[start[0]][start[1]] = true;
        answerGraph[start[0]][start[1]] = node;
        while(!queue.isEmpty()){            
            int[] next = queue.poll();      
            for(int[] dxy : directions){
                int dx = next[0] + dxy[0];
                int dy = next[1] + dxy[1];
                if(dx < 0 || dy < 0 || dx >= n || dy >= m)
                    continue;
                if(visited[dx][dy])
                    continue;
                if(graph[dx][dy] == 1)
                    continue;
                
                queue.add(new int[]{dx,dy});
                visited[dx][dy] = true;
                answerGraph[dx][dy] = node;
                node.value +=1;
            }
        }
    }

    //     ==============
}
    //     class
    class Node{
        int value;
        Node(int value){
            this.value = value;
        }
    }
    //     ==============
