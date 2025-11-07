import java.io.*;
import java.util.*;

public class Main {

    public static int[] array;
    public static int[] tree;
    public static int N;

    /**
     * 아이디어
     * 좌표 압축 + 펜윅트리
     * 펜윅 트리의 키값은 선수의 실력, 벨류는 1(에 대한 누적합)
     * 키 분포가 1~1,000,000,000 이지만 전체 입력 값이 500,000 이하임. 좌표 압축이 효율적
     * => 이에 따라 선수의 실력이 주어지면, 선수보다 느린 인원수의 누적합을 알 수 있음
     * 현재 시점 전체 선수 수 - 느린 인원수 = 랭킹
     */

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        N = Integer.parseInt(bufferedReader.readLine());
        array = new int[N];
        tree = new int[N+1];
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(bufferedReader.readLine());
        }
        int[] newArray = array.clone();
        Arrays.sort(newArray);
        Map<Integer, Integer> compress = new HashMap<>();
        int index = 1;
        for (int value : newArray) {
            compress.put(value, index++);
        }

        tree = new int[N + 1];
        int total = 1;
        for (int input : array) {
            int compressed = compress.get(input);
            int lowerCount = sum(compressed);
            int rank = total++ - lowerCount;
//            System.out.println("input: " + input +"(" + compressed + ") " + lowerCount  + "/" + total);
//            System.out.println("rank: " + rank);
            update(compressed, 1);
            stringBuilder.append(rank).append("\n");
        }
        System.out.print(stringBuilder);

    }

    public static void update(int index, int value) {
        while (index <= N) {
            tree[index] += value;
            index += index & -index;
        }
    }

    public static int sum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index & -index;
        }
        return sum;
    }
}

