import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{

//     static variables
    static ArrayList<ArrayList<Integer>> graph;
    static ArrayList<ArrayList<Boolean>> visited;
    static int n;
    static int m;
//     ================
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>(n);
            
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());       
                ArrayList<Integer> graphRow = new ArrayList<Integer>();
                for(int j = 0; j < m; j++){
                    graphRow.add(Integer.parseInt(st.nextToken()));    
                }
                graph.add(graphRow);
            }
            DFS(new int[]{0,0});
          
            System.out.println(meltCheese());  

            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }    
//     static methods        
    static int meltCheese(){
        int counter = 0;
        while(true){
            boolean flag = true;
            ArrayList<int[]> gonnaMelt = new ArrayList<>();
            
            for(int i = 0; i<n; i++){
                for(int j = 0; j<m; j++){
                    if(checkAir(new int[]{i, j})){
                        flag = false;                        
                        gonnaMelt.add(new int[]{i,j});
                    }
                }
            }
            Iterator<int[]> it = gonnaMelt.iterator();
            
            while(it.hasNext()){
                int[] cheese = it.next();
                graph.get(cheese[0]).set(cheese[1], 0);
            }            
            if(flag){
                return counter;
            }
            DFS(new int[]{0,0});            
            counter+=1;            
        }
    }
    static boolean checkAir(int[] arr){
        
        if(ifTrue(arr))
            return false;    
        int[][] arrays = {
            {arr[0], arr[1] +1},
            {arr[0], arr[1] -1},
            {arr[0]-1, arr[1]},
            {arr[0]+1, arr[1]}
        };
        int outside = 0;        
        for(int[] searching : arrays){
            if(searching[0] < 0 || searching[0] >= n || searching[1] < 0 || searching[1] >= m){
                continue;
            }
            if(ifTrue(searching)){
                outside += 1;
            }            
        }
        if(outside < 2)
            return false;    
        return true;
    }
    static void DFS(int[] start){
        visited = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            ArrayList<Boolean> visitedRow = new ArrayList<Boolean>();            
            for(int j = 0; j < m; j++){
                visitedRow.add(false);                            
            }
            visited.add(visitedRow);            
        }
        
        Stack<int[]> stack = new Stack<>();
        stack.push(start);
        setTrue(start);
                
        while(!stack.isEmpty()){
            int[] now = stack.pop();
            setTrue(now);
            
            int[][] arrays = {
                {now[0], now[1] +1},
                {now[0], now[1] -1},
                {now[0]-1, now[1]},
                {now[0]+1, now[1]}
            };
            
            for(int[] arr : arrays){
                if(arr[0] < 0 || arr[0] >= n || arr[1] < 0 || arr[1] >= m){
                    continue;
                }
                if(ifTrue(arr) || ifCheese(arr))
                    continue;
                
                stack.push(arr);
                // System.out.println(Arrays.toString(arr));
            }
        }
    }
    
    static void setTrue(int[] value){
        visited.get(value[0]).set(value[1], true);
    }
    
    static boolean ifTrue(int[] value){
        return visited.get(value[0]).get(value[1]);
    }
    
    static boolean ifCheese(int[] value){
        return (1 == graph.get(value[0]).get(value[1]));
    }
//     ==============
}    
