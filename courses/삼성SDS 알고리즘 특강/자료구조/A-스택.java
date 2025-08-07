import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());
        Deque<Integer> stack = new ArrayDeque<>();
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder =new StringBuilder();
        for(int i = 0; i < n; i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            String operation = stringTokenizer.nextToken();
            int value;
            switch (operation) {
                case "push" :
                    value = Integer.parseInt(stringTokenizer.nextToken());
                    stack.push(value);
                    break;
                case "top" :
                    stringBuilder.append(stack.isEmpty() ? -1 : stack.peek()).append("\n");
                    break;
                case "size" :
                    stringBuilder.append(stack.size()).append("\n");
                    break;
                case "empty" :
                    stringBuilder.append(stack.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "pop" :
                    stringBuilder.append(stack.isEmpty() ? -1 : stack.pop()).append("\n");
                    break;
            }
        }
        System.out.println(stringBuilder);
    }
}