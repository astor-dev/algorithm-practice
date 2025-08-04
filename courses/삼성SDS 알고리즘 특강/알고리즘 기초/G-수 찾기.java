import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            int val = Integer.parseInt(st.nextToken());
            set.add(val);
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder stringBuilder =new StringBuilder();
        for(int i = 0; i < m; i ++) {
            int val = Integer.parseInt(st.nextToken());
            stringBuilder.append(set.contains(val) ? 1 : 0).append("\n");
        }
        System.out.print(stringBuilder);

    }
}