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
            
            /*
            DP테이블을 만드는데 2개를만든다
            연속 1번인 dpT
            연속 2번인 dpT            
            */
            int n = Integer.parseInt(br.readLine());
            Integer[] zeros = new Integer[n+2];
            Arrays.fill(zeros, new Integer(0));
            
            ArrayList<Integer> oneConsecutiveTable = new ArrayList(Arrays.asList(zeros));
            ArrayList<Integer> twoConsecutiveTable = new ArrayList(Arrays.asList(zeros));
//             i = 2: 1번째칸 오른 상태
            for(int i = 1; i<n+1; i++){
                Integer value = Integer.valueOf(br.readLine());
//                 1연속에 있는 값에 value 더해서 2연속 테이블에 넣기
                twoConsecutiveTable.set(i+1, Math.max(twoConsecutiveTable.get(i+1), oneConsecutiveTable.get(i) + value));                
//                 1연속-1에 있는 값에 value 더해서 1연속 테이블에 넣기
                oneConsecutiveTable.set(i+1, Math.max(oneConsecutiveTable.get(i+1), oneConsecutiveTable.get(i-1) + value));
//                 2연속에 있는 값-1에 value 더해서 1연속 테이블에 넣기
                oneConsecutiveTable.set(i+1, Math.max(oneConsecutiveTable.get(i+1), twoConsecutiveTable.get(i-1) + value));
            }
            
            System.out.println(Math.max(oneConsecutiveTable.get(n+1), twoConsecutiveTable.get(n+1)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        
    
//     ==============
}    
