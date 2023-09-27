import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{

//     static variables
    
//     ================
    public static void main(String[] args) throws Exception {
        try{                    
            // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            // StringTokenizer st;
        
            // st = new StringTokenizer(br.readLine());
            
            UDCycleFinder finder = new UDCycleFinder();
            
            finder.init();
            finder.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        
//     ==============
}    

class UDCycleFinder{
    private ArrayList<Integer> undirectedGraph;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;
    private int n;
    private int m;
    
    UDCycleFinder(){}
    
    public void init(){
        try{
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            undirectedGraph = new ArrayList<>(n);
            for(int i = 0; i < n; i++){
                undirectedGraph.add(i);
            }
        } catch(IOException e){}    
    }
    
    public void start(){
        for(int i = 0; i < m; i++){
            try{
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());    
                
                if(union(x,y)){
                    System.out.println(i+1);
                    return;
                }
            } catch(IOException e){}
        }
        System.out.println(0);
    }
    
    public boolean union(Integer x, Integer y){
        x = find(x);
        y = find(y);
        
        if(x == y){
            return true;
        }        
        if(x < y){
            undirectedGraph.set(y,x);
            return false;
        }
        undirectedGraph.set(x,y);
        return false;
    }
    
    public Integer find(Integer x){
        if(x == undirectedGraph.get(x)){
            return x;
        }
        
        return find(undirectedGraph.get(x));
    }
}
