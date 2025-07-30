import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Deque<Character> stack = new ArrayDeque<>();
        String string = bufferedReader.readLine();
        int depth = 0;
        int[] processing = new int[30];
        for (int i = 0; i < string.length(); i++) {
            char bracket = string.charAt(i);
            char popped;
            char previousChar;
            switch (bracket) {
                case '[':
                    stack.push(bracket);
                    depth++;
                    break;
                case ']':
                    if(stack.isEmpty()) {
                        System.out.println(0);
                        return;
                    }
                    popped = stack.pop();
                    if (popped != '[') {
                        System.out.println(0);
                        return;
                    }
                    depth--;
                    previousChar = string.charAt(i-1);
                    if (previousChar == '[') processing[depth] += 3;
                    else processing[depth] += processing[depth+1] * 3;
                    processing[depth+1] = 0;
                    break;
                case '(':
                    stack.push(bracket);
                    depth++;
                    break;
                case ')':
                    if(stack.isEmpty()) {
                        System.out.println(0);
                        return;
                    }
                    popped = stack.pop();
                    if (popped != '(' || i == 0) {
                        System.out.println(0);
                        return;
                    }
                    depth--;
                    previousChar = string.charAt(i-1);
                    if (previousChar == '(') processing[depth] += 2;
                    else processing[depth] += processing[depth+1] * 2;
                    processing[depth+1] = 0;
                    break;
            }
//            System.out.println(Arrays.toString(processing));
        }
        if(!stack.isEmpty()) {
            System.out.println(0);
            return;
        }
        System.out.println(processing[0]);
    }
}
