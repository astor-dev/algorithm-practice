import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.math.BigInteger;

public class Main{
    static int n;
    static int m;
    static int[] degree;
    static ArrayList<ArrayList<Integer>> edgesInfo;
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            
            degree = new int[n+1];
            edgesInfo = new ArrayList<ArrayList<Integer>>();
            for(int i = 0; i <= n; i++){
                edgesInfo.add(new ArrayList<Integer>());
            }
            
            degree[0] = Integer.MAX_VALUE;
            
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int fore = Integer.parseInt(st.nextToken());
                int post = Integer.parseInt(st.nextToken()); 
                edgesInfo.get(fore).add(post);
                degree[post]++;                    
            }
            // System.out.println(Arrays.toString(degree));
            // System.out.println(edgesInfo.toString());
            topologySort();

                  
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }
    }     
    static void topologySort(){
        try{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));        
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 1; i <= n; i++){
            if(degree[i] == 0)
                queue.add(i);
        }
        for(int i = 0; i < n; i++){
            if(queue.isEmpty()){
                return;
            }
            int searching = queue.poll();
            bw.write(searching+ " ");            
            ArrayList<Integer> connectedNodes = edgesInfo.get(searching);
            for(int node : connectedNodes){
                if(--degree[node] == 0)
                    queue.add(node);
            }
        }
            bw.flush();
            bw.close();        
    } catch(IOException e){}
    }
}
