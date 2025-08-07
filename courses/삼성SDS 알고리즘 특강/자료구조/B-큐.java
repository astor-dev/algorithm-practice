import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());
        Deque<Integer> queue = new ArrayDeque<>();
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder =new StringBuilder();
        for(int i = 0; i < n; i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            String operation = stringTokenizer.nextToken();
            int value;
            switch (operation) {
                case "push" :
                    value = Integer.parseInt(stringTokenizer.nextToken());
                    queue.add(value);
                    break;
                case "front" :
                    stringBuilder.append(queue.isEmpty() ? -1 : queue.getFirst()).append("\n");
                    break;
                case "back" :
                    stringBuilder.append(queue.isEmpty() ? -1 : queue.getLast()).append("\n");
                    break;
                case "size" :
                    stringBuilder.append(queue.size()).append("\n");
                    break;
                case "empty" :
                    stringBuilder.append(queue.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "pop" :
                    stringBuilder.append(queue.isEmpty() ? -1 : queue.poll()).append("\n");
                    break;
            }
        }
        System.out.println(stringBuilder);
    }
}