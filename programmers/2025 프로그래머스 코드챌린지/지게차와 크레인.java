import java.util.*;

class Solution {
    // 50x50 = 2500
    // 1. reachable 한 컨테이너 리스트
    // 2. 종류를 키로 하는 컨테이너 리스트

    class Container {
        char type;
        int x;
        int y;
        Container(char type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("%4s", type);
        }
    }

    Map<Character, List<Container>> containersMap;
    Set<Container> reachableContainers;
    Container[][] graph;
    int[][] moves = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public int solution(String[] storage, String[] requests) {
        containersMap = new HashMap<>();
        reachableContainers = new HashSet<>();
        int n = storage.length;
        int m = storage[0].length();
        graph = new Container[n+2][m+2];
        for(int i = 0; i < storage.length; i++) {
            for(int j = 0; j < storage[i].length(); j++) {
                char c = storage[i].charAt(j);
                Container container = new Container(c, i+1, j+1);
                // 1-based
                graph[i+1][j+1] = container;
                containersMap.computeIfAbsent(c ,k -> new ArrayList<>()).add(container);
            }
        }
        dfs(0, 0, new boolean[graph.length][graph[0].length]);

        for(String request: requests) {

            char c = request.charAt(0);
            int operation = request.length();
            if(operation == 1) {
                for(Container container : reachableContainers) {
                    if(container.type == c) {
                        containersMap.get(c).remove(container);
                        graph[container.x][container.y] = null;
                    }
                }
            }
            if (operation == 2) {
                for(Container container: containersMap.getOrDefault(c, List.of())) {
                    graph[container.x][container.y] = null;
                }
                containersMap.put(c, List.of());
            }
            reachableContainers = new HashSet<>();
            dfs(0, 0, new boolean[graph.length][graph[0].length]);


        }
        int answer = 0;
        for(Container[] list :graph) {
            for(Container container: list) {
                if(container != null) answer++;
            }
        }
        return answer;
    }

    void dfs(int x, int y, boolean[][] visited) {
        if(visited[x][y]) return;
        visited[x][y] = true;
        for(int[] move: moves) {
            int dx = x+move[0];
            int dy = y+move[1];
            if(0 <= dx && dx < graph.length && 0 <= dy && dy < graph[dx].length) {
                if(graph[dx][dy] == null) {
                    dfs(dx,dy,visited);
                } else {
                    reachableContainers.add(graph[dx][dy]);
                }
            }
        }
    }
}