import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main{
    static HashMap<Integer, int[]> weightMapper = new HashMap<>();
    static int INF = 100_000_000;
    static ArrayList<int[][]> dp;
    static int pointer = 0;

    public static void main(String[] args) throws Exception {
        try{                    
            weightMapper.put(0, new int[]{INF, 2, 2, 2, 2});
            weightMapper.put(1, new int[]{INF, 1, 3, 4, 3});
            weightMapper.put(2, new int[]{INF, 3, 1, 3, 4});
            weightMapper.put(3, new int[]{INF, 4, 3, 1, 3});
            weightMapper.put(4, new int[]{INF, 3, 4, 3, 1});
            /*
                DP로 저장하는 데, 이중 배열로 내 발의 마지막 위치 + 현재 횟수를 저장한다
            */
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
            dp = new ArrayList<>();
            int[][] arr1 = {
                {0,INF,INF,INF,INF},
                {INF,INF,INF,INF,INF},
                {INF,INF,INF,INF,INF},
                {INF,INF,INF,INF,INF},
                {INF,INF,INF,INF,INF}                     
            };            
            dp.add(arr1);
            while(true){
                int next = Integer.parseInt(st.nextToken());
                if(next == 0){
                    int min = INF;
                    // showArray(pointer);
                    for(int l = 0; l < 5; l++){
                        for(int r = 0; r < 5; r++){
                            min = Math.min(dp.get(pointer)[l][r], min);
                        }
                    }
                    System.out.println(min);
                    break;
                }
                int[][] arr = {
                    {INF,INF,INF,INF,INF},
                    {INF,INF,INF,INF,INF},
                    {INF,INF,INF,INF,INF},
                    {INF,INF,INF,INF,INF},
                    {INF,INF,INF,INF,INF}                     
                };
                dp.add(arr);
                search(next);
                // showArray(pointer);
                pointer++;                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }
    }
    static void showArray(int idx){
        int[][] arr = dp.get(idx);
        for(int i = 0; i < 5; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
    }
    static void search(int target){
        // l => target
        for(int l = 0; l < 5; l++){
            int add = weightMapper.get(l)[target];
            for(int r = 0; r < 5; r++){
                dp.get(pointer+1)[target][r] = Math.min(dp.get(pointer)[l][r] + add , dp.get(pointer+1)[target][r]);
            }
        }
        // r => target
        for(int r = 0; r < 5; r++){
            int add = weightMapper.get(r)[target];
            for(int l = 0; l < 5; l++){
                dp.get(pointer+1)[l][target] = Math.min(dp.get(pointer)[l][r] + add , dp.get(pointer+1)[l][target]);
            }
        }   
    }
}
