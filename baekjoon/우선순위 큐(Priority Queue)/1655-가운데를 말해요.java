import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        N = Integer.parseInt(bufferedReader.readLine());

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++){
            int num = Integer.parseInt(bufferedReader.readLine());
            if(i==0) {
                maxHeap.add(num);
                stringBuilder.append(maxHeap.peek()).append("\n");
                continue;
            }
            if(maxHeap.peek() < num) {
                minHeap.add(num);
                if(maxHeap.size() < minHeap.size()) {
                    maxHeap.add(minHeap.poll());
                }
            } else {
                maxHeap.add(num);
                if(minHeap.size() + 1 < maxHeap.size()) {
                    minHeap.add(maxHeap.poll());
                }
            }
            stringBuilder.append(maxHeap.peek()).append("\n");
        }
        System.out.print(stringBuilder);
    }
}
