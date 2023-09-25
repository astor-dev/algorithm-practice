import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 

//     static variables
    static ArrayList<Integer> parent;
    
//     ================
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;
            
            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());
            parent = new ArrayList<Integer>(n+1);            
            for(int i = 0; i<=n; i++){
                parent.add(i);
            }
            for(int i = 1; i<=n; i++){
                st = new StringTokenizer(br.readLine());                
                for(int j = 1; j<=n; j++){
                    boolean value = (st.nextToken().equals("1")) ?true :false;
                    if(value){                        
                        union(i, j);
                    }
                }                
            }
            
            st = new StringTokenizer(br.readLine());
            int postParent = find(Integer.parseInt(st.nextToken()));
            for(int i = 2; i <= m; i ++){
                int searchingValue = find(Integer.parseInt(st.nextToken()));
                if(postParent != searchingValue){
                    System.out.println("NO");
                    return;
                }
                postParent = parent.get(searchingValue);
            }
            System.out.println("YES");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        
    public static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        
        if(x==y) return false;
        
        if(x<=y) parent.set(y,x);
        else parent.set(x,y);
        
        return true;
    }
    
    public static int find(int x){
        if(parent.get(x) == x) return x;
        
        return (find(parent.get(x)));
    }
//     ==============
}    

