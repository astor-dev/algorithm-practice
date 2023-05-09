import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{
        static void printGraph(int[][] graph){
            for(int i = 0; i<graph.length; i++){
                System.out.println(Arrays.toString(graph[i]));
            }
        };    
    public static void main(String[] args) throws Exception {

        try{        
/*
문제 접근
7576 토마토
BFS
arraydeque에 방문한 노드 저장(큐)
fifo 수행하며 순회!

<pseudo coding>
N, M 저장
LinkedList<Integer>[] 링크드리스트 graph 함 써보자
음.. ArrayList가 더 빠른가? 배열 접근하는 용도로 쓸텐데 이따 속도 비교 ㄱ
for(m){
    for(n){
        graph[m].add(인풋)        
    }
}


초기시작점: 전체 배열 보면서 1인 값(토마토가 있는 노드) 큐에 싹 집어넣음
<while 큐 길이 == 0>
!!이 시점에서 큐 원소 개수 저장
   <for 큐 원소 개수만큼>
       try {
       if == 0 : 1로 바꾸고 queue에 넣기
       } catch{ }
       x4       
날짜 카운트 +=1
</while>
print 날짜 
*/
// ========================================================Write Here========================================================            
            //입력
         
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));            
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            ArrayDeque<Integer[]> queue = new ArrayDeque<>();            
            int[][] graph = new int[M][N];
            
            for (int i = 0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<N; j++){
                    graph[i][j] = Integer.parseInt(st.nextToken());
                    if (graph[i][j] == 1){
                        queue.add(new Integer[]{i,j});
                    }
                }
            }
            int count = -1;
            while(!queue.isEmpty()){
                int tempSize = queue.size();
                count +=1;                
                for(int i = 0; i<tempSize; i++){
                    Integer[] now = queue.poll();
                    int x = now[0];
                    int y = now[1];
                    try{
                        if(graph[x][y-1] == 0){
                            graph[x][y-1] = 1;
                            queue.add(new Integer[]{x,y-1});
                        }                        
                    } catch(Exception e){}
                    try{
                        if(graph[x][y+1] == 0){
                            graph[x][y+1] = 1;
                            queue.add(new Integer[]{x,y+1});
                        }                        
                    } catch(Exception e){}
                    try{
                        if(graph[x+1][y] == 0){
                            graph[x+1][y] = 1;
                            queue.add(new Integer[]{x+1,y});
                        }                        
                    } catch(Exception e){}
                    try{
                        if(graph[x-1][y] == 0){
                            graph[x-1][y] = 1;
                            queue.add(new Integer[]{x-1,y});
                        }                        
                    } catch(Exception e){}                    
                
                //printGraph(graph);
                }                
            }
            boolean flag = false;
            for(int i = 0; i<M; i++){
                if(Arrays.stream(graph[i]).anyMatch(n -> n == 0)){
                    flag = true;
                }
            }
            if(!flag){
                System.out.println(count);
            } else{
                System.out.println(-1);
            }
            
            
// ========================================================Write Here========================================================            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
}
