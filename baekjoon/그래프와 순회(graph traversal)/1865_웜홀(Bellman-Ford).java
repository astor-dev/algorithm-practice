import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{

//     static variables
    static int tc;
    static int n;
    static int m;
    static int w;
    static ArrayList<int[]> edges;
    static ArrayList<Integer> shortestD;
//     ================
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;
            
            tc = Integer.parseInt(br.readLine());
            for(int i = 0; i< tc; i++){
                st = new StringTokenizer(br.readLine());
                n = Integer.parseInt(st.nextToken());
                m = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());
                
                edges = new ArrayList<>();
                shortestD = new ArrayList<>(n+1);                
                for(int j = 0; j <= n; j++){
                    shortestD.add(Integer.MAX_VALUE);
                    edges.add(new int[]{0, j, 0});                    
                }
//                 도로 입력 받기(무향)
                for(int j = 0; j < m; j++){
                    st = new StringTokenizer(br.readLine());
                    int start = Integer.parseInt(st.nextToken());
                    int end = Integer.parseInt(st.nextToken());
                    int time = Integer.parseInt(st.nextToken());
                    
                    edges.add(new int[]{end, start, time});
                    edges.add(new int[]{start, end, time});
                }
//                 웜홀 입력 받기(유향)
                for(int j = 0; j < w; j++){
                    st = new StringTokenizer(br.readLine());
                    int start = Integer.parseInt(st.nextToken());
                    int end = Integer.parseInt(st.nextToken());
                    int time = Integer.parseInt(st.nextToken());                
                    
                    edges.add(new int[]{start, end, -time});
                }                
                System.out.println(bellman_ford(0)?"YES":"NO");
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }    
    //     static methods     
    static boolean bellman_ford(int start){
        shortestD.set(start, 0);
//         최단거리 찾기
        for(int i = 1; i < n; i++){            
            Iterator<int[]> it = edges.iterator();
            while(it.hasNext()){
                int[] searching = it.next();
                int s_start = searching[0];
                int s_end = searching[1];
                int s_time = searching[2];
//                 INT 오버플로우 예외처리
                if(shortestD.get(s_start) == Integer.MAX_VALUE)
                    continue;
                if(shortestD.get(s_end) > shortestD.get(s_start) + s_time)
                    shortestD.set(s_end, shortestD.get(s_start) + s_time);                                
            }
        }
        // System.out.println(shortestD.toString());
//         음수 사이클 유무 찾기
        Iterator<int[]> it = edges.iterator();
        while(it.hasNext()){
            int[] searching = it.next();
            int s_start = searching[0];
            int s_end = searching[1];
            int s_time = searching[2];
            if(shortestD.get(s_end) > shortestD.get(s_start) + s_time){            
                return true;
            }
        }
        return false;
    }
    //     ==============
}
