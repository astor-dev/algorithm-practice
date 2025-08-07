import java.io.*;
import java.util.*;

public class Main {

    public static Node root;

    public static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = bufferedReader.readLine()) != null && !line.trim().isEmpty()) {
            int value = Integer.parseInt(line);
            Node node = new Node(value);
            if(root == null) {
                root = node;
                continue;
            }
            insert(node);
        }
        StringBuilder stringBuilder  = new StringBuilder();
        postOrder(root, stringBuilder);

        System.out.println(stringBuilder);


    }

    public static void insert(Node node) {
        Node cursor = root;
        // 전위는 루트 - 좌 - 우 순임
        while(true) {
            // 루트보다 작으면 left로
            if(node.value < cursor.value) {
                if(cursor.left == null) {
                    cursor.left = node;
                    break;
                }
                cursor = cursor.left;
            } else {
                if(cursor.right == null) {
                    cursor.right = node;
                    break;
                }
                cursor = cursor.right;
            }
        }
    }

    public static void postOrder(Node node, StringBuilder stringBuilder) {
        if (node == null) return;

        postOrder(node.left, stringBuilder);
        postOrder(node.right, stringBuilder);
        stringBuilder.append(node.value).append("\n");

    }
}