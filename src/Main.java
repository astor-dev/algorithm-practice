import java.io.*;
import java.util.*;

public class Main{

    static int n; // 사이즈
    static int[] arr; // 원본 배열
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 1일 1 세그먼트트리 만들기
        n=20;
        arr = new int[n];
        tree = new int[n]; // 원본 배열과 같아도 됨


    }
    // index의 값을 value 만큼 update
    static void update(int index, int value) {
        while(index <= n) {
            tree[index] += value;
            index += index & -index;

        }
    }

    static int sum(int index) {
        int sum = 0;
        while(index <= n) {
            sum += tree[index];
            index -= index&-index;
        }
        return sum;
    }
}