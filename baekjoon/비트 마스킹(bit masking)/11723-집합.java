import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 
        
    public static void main(String[] args) throws Exception {

        try{   
/*

*/
// ========================================================Write Here========================================================            
            StringBuilder sb = new StringBuilder();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int M = Integer.valueOf(br.readLine());
            int bit = 0; //32비트 자료형인 int(00000000 *4)의 각 자리에 1부터 20까지 숫자 존재 유무를 대응시켜 저장한다.
            
            for(int i = 0; i<M; i++){
                //System.out.println(Integer.toBinaryString(bit));
                StringTokenizer st = new StringTokenizer(br.readLine());
                String operation = st.nextToken();
                int num;
                switch(operation){
                    case "add" :
                        num = Integer.valueOf(st.nextToken());                        
                        bit |= (1<<(num-1));  // n번째 비트만 1로 변경해서 bit집합과 or연산을 통해 집합의 해당 인덱스의 비트를 1로 바꾼다.
                        break;
                    case "remove":
                        num = Integer.valueOf(st.nextToken());                        
                        bit &= ~(1<<(num-1)); // n번째 비트만 0으로 바꾸고 나머지는 1로 만든다. 이후 and연산을 통해 n번째 비트만 0으로 바꾼다.
                        break;
                    case "check":
                        num = Integer.valueOf(st.nextToken());                        
                        sb.append((bit&(1<<(num-1))) != 0 ? "1\n" : "0\n"); // and연산을 통해 만약 집합에 해당 인덱스 비트가 있으면 그부분이 켜질것이고 아니면 공집합이 나올것.
                        break;
                    case "toggle":
                        num = Integer.valueOf(st.nextToken());                        
                        bit ^= (1<<(num-1));
                        break;
                    case "all":
                        bit |= (~0);
                        break;
                    case "empty":
                        bit &= 0;
                        break;
                }
            }
            bw.write(sb.toString());
            bw.close();
            

// ========================================================Write Here========================================================            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
}

