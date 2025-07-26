import java.io.*;
import java.util.*;


public class Main {

    static class Node implements Comparable<Node> {
        int index;
        int degree;
        int constructionTime;
        int constructionFinishTime;
        List<Node> children;

        Node(int index, int constructionTime) {
            this.index = index;
            this.constructionTime = constructionTime;
            this.constructionFinishTime = Integer.MAX_VALUE;
            this.degree = 0;
            this.children = new ArrayList<>();
        }

        @Override
        public int compareTo(Node n) {
            if(this.constructionFinishTime == n.constructionFinishTime) return this.index - n.index;
            return this.constructionFinishTime - n.constructionFinishTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            Node[] graph = new Node[N+1];
            PriorityQueue<Node> jobQueue = new PriorityQueue<>();
            for (int i = 1; i <= N; i++) {
                int constructionTime = Integer.parseInt(st.nextToken());
                Node node = new Node(i, constructionTime);
                graph[i] = node;
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
                // 피진입점의 degree를 선행 건물의 idx 만큼 올림
                graph[y].degree += x;
                // 후행 건물의 빠른 접근을 위해 node의 child에 추가
                graph[x].children.add(graph[y]);
            }
            int goal = Integer.parseInt(br.readLine());
            int time = 0;

            for (Node node : graph) {
                if (node == null) continue;
                if(node.degree == 0) {
                    node.constructionFinishTime = time + node.constructionTime;
                    jobQueue.add(node);
                }
            }

            // 작업에 대한 큐를 가지고 있음 작업완료 시간을 우선순위로 정렬된 pq
            // 큐가 빌 때 까지 while
            while (!jobQueue.isEmpty()) {
                // 큐 poll
                Node cur = jobQueue.poll();
                // 작업 완료에 대한 로직:
                // 현재작업시간을 cur 노드 완료시간으로 업데이트
                time = cur.constructionFinishTime;
                // 목표 달성 시 break
                if (cur.index == goal) break;
                // cur 노드의 child노드의 진입차수를 index 만큼 --
                for (Node child : cur.children) {
                    // 만약 0이된 경우에만, 건설시간만큼의 값을 현재시간에 더해서 큐에 다 넣음
                    child.degree -= cur.index;
                    if(child.degree == 0) {
                        child.constructionFinishTime = time + child.constructionTime;
                        jobQueue.add(child);
                    }
                }
            }
            sb.append(time).append("\n");
        }
        System.out.print(sb);
    }
}