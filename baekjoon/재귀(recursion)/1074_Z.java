import java.io.*;
import java.util.*;
import java.util.stream.*;
/*

재귀 호출

매개변수: 행렬

0~반 x4
*/
public class Main{ 
    public static void main(String[] args) throws Exception {

        try{   

// ========================================================Write Here========================================================            
            //입력
         
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));            
            StringTokenizer st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            double N = Math.pow(2,i);
            double r = Double.valueOf(st.nextToken());
            double c = Double.valueOf(st.nextToken());
            
            ZSearcher.find(N,r,c);
            


            
            
// ========================================================Write Here========================================================            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
}

class ZSearcher{
    static int count = 0;
        
    static void find(double N, double row, double col){  
        //System.out.println("count: " + count + ", N: " + N + ", row: " + row + ", col: " + col);        
        if(N<=2){
            count += (2*row) + col;
            System.out.println(count);
            return;
        }

        double standard = N/2; //0~3, 4~8
        //System.out.println(standard);
        if(row<standard){            //1,2사분면
            if(col<standard){        //2사분면
                find(standard, row, col);
            } else{                  //1사분면
                count+= (standard*standard*1);
                find(standard, row, col-standard);
            }        
        } else{                      //3,4사분면
            if(col<standard){        //3사분면
                count += (standard*standard*2);
                find(standard, row-standard, col);
            } else{                  //4사분면
                count += (standard*standard*3);
                find(standard, row-standard, col-standard);
            }
        }

    } //end of find

}
