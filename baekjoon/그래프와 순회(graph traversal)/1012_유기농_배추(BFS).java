import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 
        
    public static void main(String[] args) throws Exception {

        try{   
/*
교훈!!!!

remove(0) 처럼 인덱스 지정 삭제 등을 수행할 때는 무조건 LinkedList를 쓰자!! ArrayList는 인덱스를 다 당겨줘야 해서 느림
BFS를 수행할 때는 queue에서 뺄 때가 아니라!!! queue에 넣을 때 방문처리를 해줘야 중복 방문이 안생긴다!
*/
// ========================================================Write Here========================================================            
            //입력         
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.parseInt(br.readLine());            
            for(int i = 0; i < T; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int M = Integer.valueOf(st.nextToken());
                int N = Integer.valueOf(st.nextToken());
                int k = Integer.valueOf(st.nextToken());
                
                boolean[][] visited = new boolean[M][N]; //그래프가 탐색했는지 저장할 배열
                LinkedList<Integer[]> queue = new LinkedList<Integer[]>();
                int counter = 0;
                for(int j = 0; j <k; j++){
                    st = new StringTokenizer(br.readLine());
                    int x = Integer.valueOf(st.nextToken());
                    int y = Integer.valueOf(st.nextToken());
                    visited[x][y] = true;                    
                    //입력 끝                    
                }
                for(int q = 0; q<M; q++){
                    for(int w = 0; w<N; w++){
                        if(visited[q][w]){
                            queue.add(new Integer[]{q,w});
                            visited[q][w] = false;
                            while(!queue.isEmpty()){
                                Integer[] tempintarr = queue.remove(0);
                                int tempX = tempintarr[0];
                                int tempY = tempintarr[1];
                                try{
                                if(visited[tempX+1][tempY]){
                                    queue.add(new Integer[]{tempX+1, tempY});
                                    visited[tempX+1][tempY] = false;
                                }
                                } catch(Exception e){}
                                try{
                                if(visited[tempX-1][tempY]){
                                    queue.add(new Integer[]{tempX-1, tempY});
                                    visited[tempX-1][tempY] = false;
                                }
                                } catch(Exception e){}
                                try{
                                if(visited[tempX][tempY+1]){
                                    queue.add(new Integer[]{tempX, tempY+1});
                                    visited[tempX][tempY+1] = false;
                                }
                                } catch(Exception e){}
                                try{
                                if(visited[tempX][tempY-1]){
                                    queue.add(new Integer[]{tempX, tempY-1});         
                                    visited[tempX][tempY-1] = false;
                                }
                                } catch(Exception e){}                                
                            } // end of while
                        counter += 1;
                        } // end of if
                    } // end of second for
                } // end of first for
            System.out.println(counter);    
            } // end of TestCaseLoop

 
// ========================================================Write Here========================================================            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
}

