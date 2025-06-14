import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 

//     static variables
    static int n, m;
    static ArrayList<Integer>[] graph;
    static final int INF = Integer.MAX_VALUE;
//     ================
    public static void main(String[] args) throws Exception {
        try{                           
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine()); //노드의 개수
            m = Integer.parseInt(br.readLine()); //엣지의 개수
            
            StringTokenizer st;
            graph = new ArrayList[n+1];
            //그래프 초기화
            for(int i = 0; i < n+1; i++){
                graph[i] = new ArrayList<Integer>();
                for(int j = 0;  j < n+1; j++){
                    graph[i].add(INF);
                }
            }
            
            //입력 받기
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());                                
                int startNode = Integer.parseInt(st.nextToken());
                int endNode = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph[startNode].set(endNode, Math.min(weight, graph[startNode].get(endNode)));
            }            
            floydWarshall();
            //답 출력
            printGraph();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        
    static void floydWarshall(){
        for(int passingNode = 1; passingNode < n+1; passingNode++){
            for(int startNode = 1; startNode < n+1; startNode++){
                for(int endNode = 1; endNode < n+1; endNode++){
                    if(startNode == endNode || passingNode == startNode || passingNode == endNode){
                        continue;
                    }
                    if(graph[startNode].get(passingNode)==INF || graph[passingNode].get(endNode)==INF){
                        continue;
                    }
                    int oldValue = graph[startNode].get(endNode);
                    int targetValue = graph[startNode].get(passingNode) + graph[passingNode].get(endNode);
                    
                    if(targetValue < oldValue){
                        // System.out.println("현재 passingNode: " + passingNode + ", startNode: " + startNode);
                        graph[startNode].set(endNode, targetValue);
                    }
                }
            }
        }
    }
    
    static void printGraph(){
            for(int i = 1; i< n+1; i++){
                for(int j = 1; j < n; j++){
                    if(graph[i].get(j) == INF){
                        System.out.print("0 ");
                    } else{
                        System.out.print(String.valueOf(graph[i].get(j)) + " ");
                    }
                }
                if(graph[i].get(n) == INF){
                    System.out.println("0");
                } else{
                    System.out.println(String.valueOf(graph[i].get(n)));                    
                }               
            }        
    }
//     ==============
}
