import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{

//     static variables
    static int row;
    static int col;
    static int time;
    static int[][] graph;
    static ArrayList<Integer> airCleaner;
//     ================
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;
            st = new StringTokenizer(br.readLine());
            
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());
            time = Integer.parseInt(st.nextToken());
            
            graph = new int[row][col];
            airCleaner = new ArrayList<Integer>(2);
            
            for(int i = 0; i < row; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < col; j++){
                    int value = Integer.parseInt(st.nextToken());
                    if(value == -1)
                        airCleaner.add(i);                                        
                    graph[i][j] = value;

                }
            }
            for(int i = 0; i < time; i++){
                graph = spreadDust();
                airCycle();
            }
            // print2DArray(graph);
            int count = 2;
            for(int i = 0; i < row; i++){
                for(int j = 0; j < col; j++){
                    count += graph[i][j];
                }                
            }
            System.out.println(count);
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }    
    //     static methods     
    static void print2DArray(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                System.out.print(arr[i][j]+ " ");
            }
            System.out.println();
        }
    }
 
    static int[][] spreadDust(){
        int[][] direction = new int[][]{
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };
        int[][] newGraph = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int value = graph[i][j];
                if(value == -1 || value == 0){
                    newGraph[i][j] += value;
                    continue;
                }
                int minusVal = (value / 5);
                for(int[] d : direction){
                    int dx = i + d[0];
                    int dy = j + d[1];
                    if(dx < 0 || dx >= row || dy < 0 || dy >= col)
                        continue;
                    if(graph[dx][dy] == -1)
                        continue;
                    value -= minusVal;
                    newGraph[dx][dy] += minusVal;
                    
                }
                newGraph[i][j] += value;
            }
        }
        return newGraph;
    }
    
    static void airCycle(){
        int ac1 = airCleaner.get(0);
        int ac2 = airCleaner.get(1);
        
        //ac1 윗방향 당기기
        for(int i = ac1-1; i >= 0; i--){
            int value = graph[i][0];
            if(graph[i+1][0] != -1)
                graph[i+1][0] = value;
        }
        for(int i = 1; i < col; i++){
            graph[0][i-1] = graph[0][i];
        }
        for(int i = 1; i <= ac1; i++){
            graph[i-1][col-1] = graph[i][col-1];
        }
        for(int i = col-2; i > 0; i--){
            graph[ac1][i+1] = graph[ac1][i];            
        }
        graph[ac1][1] = 0;
        
        for(int i = ac2+1 ; i < row; i++){
            int value = graph[i][0];
            if(graph[i-1][0] != -1)
                graph[i-1][0] = value;            
        }
        for(int i = 1; i < col; i++){
            graph[row-1][i-1] = graph[row-1][i]; 
        }
        for(int i = row-2; i >= ac2; i--){
            graph[i+1][col-1] = graph[i][col-1];
        }        
        for(int i = col-2; i > 0; i--){
            graph[ac2][i+1] = graph[ac2][i];
        }
        graph[ac2][1] = 0;
    }
    //     ==============
}
