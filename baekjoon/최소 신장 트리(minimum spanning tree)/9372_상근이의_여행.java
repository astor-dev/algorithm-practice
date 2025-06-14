import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{

//     static variables
    
//     ================
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;
            int Tcase = Integer.parseInt(br.readLine());
            
            for(int i = 0; i<Tcase;i++){
                st = new StringTokenizer(br.readLine());                
                int nodeN = Integer.parseInt(st.nextToken());
                int edgeN = Integer.parseInt(st.nextToken());
                for(int j = 0; j < edgeN; j++){
                    br.readLine();
                }
                //MST의 특징! 항상 edge 수는 node-1
                System.out.println(nodeN - 1);                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        
//     ==============
}    
