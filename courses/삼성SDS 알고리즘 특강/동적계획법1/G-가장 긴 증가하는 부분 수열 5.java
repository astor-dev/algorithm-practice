import java.io.*;
import java.util.*;

public class Main{

    /*
    이분탐색 LIS를 왜 하는가? 무엇을 얻을 수 있는가?
    LIS를 했을 때, 마지막 입력 값을 최소화하는 수열 쌍을 러프하게 갱신 가능
    결국 수열은, LIS에서 사이즈가 증가하는 시점에 들어간 노드에 종속적임 이를 트래킹 하자
    커서 - 현 시점 마지막 값이 몇번째 인덱스에서 온 입력인가
    부모 - 해당 인덱스의 값에서 바로 위 값이 몇번 인덱스인가
     */

    static class Node{
        int value;
        Node parent;
        Node (int value, Node parent) {
            this.value = value;
            this.parent = parent;
        }
        @Override
        public String toString() {
            return "[ value: " + this.value + ", parent: " + (parent != null ? parent.value : "null") + " ]";
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();

        int n = Integer.parseInt(bufferedReader.readLine());
        ArrayList<Node> pseudoLis = new ArrayList<>();

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 1번 인덱스 예외 그냥 선제 처리해서 isEmpty 분기 생략
        Node firstNode = new Node(Integer.parseInt(stringTokenizer.nextToken()), null);
        pseudoLis.add(firstNode);
        // 1번 제외하고 n-1번

        for (int i = 2; i <= n; i++) {
            Node node;
            int value = Integer.parseInt(stringTokenizer.nextToken());
            Node lastNode = pseudoLis.get(pseudoLis.size()-1);
            // 일단 마지막 값 비교 후 더 크면 넣기
            if(lastNode.value < value) {
                node = new Node(value, lastNode);
                pseudoLis.add(node);
                continue;
            }
            // 마지막보다 작은 케이스면 배열 안에서 들어온 값의 딱 lowerBound 찾아서 그 자리에 넣기
            int left = 0;
            int right = pseudoLis.size() -1;
            while (left <= right) {
                int mid = left + (right-left)/2;
                if(value > pseudoLis.get(mid).value) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            node = new Node(value, (left > 0) ? pseudoLis.get(left-1) : null);
//            System.out.println("지금 배열 = " + pseudoLis);
//            System.out.println("[" + left + "] = " + node.value);
            pseudoLis.set(left, node);
        }
        stringBuilder.append(pseudoLis.size()).append("\n");
        Node cursorNode = pseudoLis.get(pseudoLis.size()-1);
        int[] answer = new int[pseudoLis.size()];
        for (int i = pseudoLis.size()-1; i >=0 ; i--) {
            answer[i] = cursorNode.value;
            cursorNode = cursorNode.parent;
        }
        for (int i = 0; i <= pseudoLis.size()-1; i++) {
            stringBuilder.append(answer[i]).append(" ");
        }

        System.out.println(stringBuilder);

    }
}
