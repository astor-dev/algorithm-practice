import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();
        int n = Integer.parseInt(bufferedReader.readLine());
        ArrayList<Integer> pseudoLis = new ArrayList<>();
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 0번 인덱스 예외 그냥 선제 처리해서 연산 줄임
        pseudoLis.add(Integer.parseInt(stringTokenizer.nextToken()));

        // 0번 제외하고 n-1번
        for (int i = 1; i < n; i++) {
            int value = Integer.parseInt(stringTokenizer.nextToken());
            int last = pseudoLis.get(pseudoLis.size()-1);
            // 일단 마지막 값 비교 후 더 크면 넣기
            if(last < value) {
                pseudoLis.add(value);
                continue;
            }
            // 마지막보다 작은 케이스면 배열 안에서 들어온 값의 딱 lowerBound 찾아서 그 자리에 넣기
            int left = 0;
            int right = pseudoLis.size() -1;
            while (left <= right) {
                int mid = left + (right-left)/2;
                if(value > pseudoLis.get(mid)) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            pseudoLis.set(left, value);
        }
        System.out.println(pseudoLis.size());

    }
}
