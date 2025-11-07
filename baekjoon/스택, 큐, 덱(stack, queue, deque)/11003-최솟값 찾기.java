import java.io.*;
import java.util.*;


public class Main {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder = new StringBuilder();
    public static void main(String[] args) throws Exception {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int l = Integer.parseInt(stringTokenizer.nextToken());

        Deque<Integer> deque = new ArrayDeque<>();
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] arr = new int[n];
        for (int i = 0;  i < n; i++) {
            int number = Integer.parseInt(stringTokenizer.nextToken());
            arr[i] = number;
        }
        for (int index = 0; index < n; index++) {
            if (!deque.isEmpty() && deque.getFirst() < index - l + 1) {
                deque.removeFirst();
            }
            // 값 추가에 대한 연산
            while (!deque.isEmpty() && arr[deque.getLast()] > arr[index]) {
                deque.removeLast();
            }
            deque.addLast(index);
            // 맨 앞 값 체크

            stringBuilder.append(arr[deque.getFirst()]).append(" ");
        }
        System.out.println(stringBuilder.toString());

    }
}
