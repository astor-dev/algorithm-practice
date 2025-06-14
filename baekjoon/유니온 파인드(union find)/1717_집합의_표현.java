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
            // int n = Integer.parseInt(st.nextToken());
            // int m = Integer.parseInt(st.nextToken());            
            
            UnionFinder unionFinder = new UnionFinder();    
            unionFinder.loop();
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        
//     ==============
}    

class UnionFinder{
    private ArrayList<Integer> parent;    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;
    private int m;
    
    UnionFinder(){
        int n = 1000000;
        try{
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            this.m = Integer.parseInt(st.nextToken());
        } catch(Exception e){}
        parent = new ArrayList<Integer>(n+1);
        for(int i = 0; i<=n; i++){
            parent.add(i);
        }
    }
    
    public void loop(){
        for(int i = 0; i<m; i++){
            getOperation();
        }
    }
    
    public void getOperation(){
        try{
            st = new StringTokenizer(br.readLine());   
            int op = Integer.parseInt(st.nextToken());            
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
                        
            switch(op){
                case 0:
                    union(x, y);
                    break;
                case 1:
                    checkOp(x, y);
                    break;
                default:
                    System.out.println("invalid operation");
                    break;
            }
        } catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public boolean union(int x, int y){
        if(x == y){
            return true;
        }
        x = find(x);
        y = find(y);
        
        if(x == y) return true;
        
        if(x <= y){
            parent.set(y,x);
            return false;
        } else{
            parent.set(x,y);
            return false;
        }
    }
    
    public int find(int x){
        if(x == parent.get(x)) return x;
        
        return (find(parent.get(x)));
    }
    
    public void checkOp(int x, int y){
        if(x==y){
            System.out.println("YES");
            return;
        }
        if(find(x) == find(y)){
            System.out.println("YES");
        } else{
            System.out.println("NO");
        }
        
    }
}
