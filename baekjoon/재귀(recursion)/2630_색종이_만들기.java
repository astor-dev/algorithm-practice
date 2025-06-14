import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 
        
    public static void main(String[] args) throws Exception {
        try{   
// ========================================================Write Here========================================================            
            StringBuilder sb = new StringBuilder();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            int T = Integer.valueOf(br.readLine());
            ArrayList<Integer>[] arr = new ArrayList[T];
            for(int i = 0; i<T; i++){
                arr[i] = new ArrayList<Integer>();
            }
            for(int i = 0; i<T; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j<T; j++){
                    arr[i].add(Integer.valueOf(st.nextToken()));
                }
            }
            forloop(0,0,T,arr);
            System.out.println(whiteNum);
            System.out.println(blueNum);
            
// ========================================================Write Here========================================================            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
    static int whiteNum = 0;
    static int blueNum = 0;
    static void divide(ArrayList<Integer>[] arr, int T){
        if (T == 1){
            if (arr[0].get(0) == 0) {
                whiteNum+=1;
            } else if (arr[0].get(0) == 1){
                blueNum+=1;
                return;
            }
        }
        int Tdiv2 = (int)T/2;
        forloop(0, 0, Tdiv2, arr);
        forloop(0,Tdiv2,Tdiv2, arr);
        forloop(Tdiv2,0,Tdiv2, arr);
        forloop(Tdiv2,Tdiv2,Tdiv2,arr);                        

    }
    
    static void forloop(int iNum, int jNum, int Tdiv2,  ArrayList<Integer>[] arr){
        
        ArrayList<Integer>[] tempArr = new ArrayList[Tdiv2];
        for(int i = 0; i<Tdiv2; i++){
            tempArr[i] = new ArrayList<Integer>();
        }        
        boolean flag = false;
        int before = -1;
        for(int i = iNum; i < iNum+Tdiv2; i++){
            for(int j = jNum; j < jNum+Tdiv2; j++){
                
                int addVal = arr[i].get(j);
                tempArr[i-iNum].add(addVal);                
                if(before==-1){
                    before = addVal;
                    continue;
                }                
                if(before != addVal){
                    flag = true;
                }
                before = addVal;                
            }
        }
        /*
        System.out.println("================");
        for(ArrayList<Integer> smallarr : tempArr){
            System.out.println(smallarr);
        }
        System.out.println("================");
        */
        
        if (flag){
            divide(tempArr, Tdiv2);
        } else{
            if (tempArr[0].get(0) == 0) {
                whiteNum+=1;
            } else if (tempArr[0].get(0) == 1){
                blueNum+=1;
                return;
            }            
    }
    } //end of method
}

