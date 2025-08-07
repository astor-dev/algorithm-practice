import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder stringBuilder = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] oneConsecutiveTable = new int[n+2];
        int[] twoConsecutiveTable = new int[n+2];
        Arrays.fill(oneConsecutiveTable, 0);
        Arrays.fill(twoConsecutiveTable, 0);

//             i = 2: 1번째칸 오른 상태
        for(int i = 1; i<n+1; i++){
            int value = Integer.parseInt(br.readLine());
//                 1연속에 있는 값에 value 더해서 2연속 테이블에 넣기
            twoConsecutiveTable[i+1] = Math.max(twoConsecutiveTable[i+1], oneConsecutiveTable[i] + value);
//                 1연속-1에 있는 값에 value 더해서 1연속 테이블에 넣기
            oneConsecutiveTable[i+1] = Math.max(oneConsecutiveTable[i+1], oneConsecutiveTable[i-1] + value);
//                 2연속에 있는 값-1에 value 더해서 1연속 테이블에 넣기
            oneConsecutiveTable[i+1] = Math.max(oneConsecutiveTable[i+1], twoConsecutiveTable[i-1] + value);
        }
        System.out.println(Math.max(oneConsecutiveTable[n+1], twoConsecutiveTable[n+1]));
    }
}

