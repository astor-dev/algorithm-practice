import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main{
    static int n;
    static long[] arr;
    static int left = 0;
    static int right;
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            n = Integer.parseInt(br.readLine());            
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr = new long[n];
            right = n-1;
            for(int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            twoPointer();            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }
    }    
    static void twoPointer(){
        long min = Long.MAX_VALUE;
        int ml = 0, mr = 0;
        while(left<right){
            long sum = arr[left] + arr[right];
            if(min > Math.abs(sum)){
                min = Math.abs(sum);
                ml = left; mr = right;
            }
            if(sum>=0){
                right--;
            } else {
                left++;
            }
        }
        System.out.println(arr[ml] + " " + arr[mr]);
    }
}
