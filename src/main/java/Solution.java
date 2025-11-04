import java.util.*;
import java.util.stream.*;

class Solution {

    enum TreeType {
        NORMAL, REVERSED, NONE
    }

    class Forest {
        int id;
        Set<Integer> nodes;
        Map<Integer, TreeType> cache;
        Map<Integer, List<Integer>> edges;
        Forest(int id, Map<Integer, List<Integer>> edges) {
            this.id = id;
            this.nodes = new HashSet<>();
            this.cache = new HashMap<>();
            this.edges = edges;
        }

        // parent == -1 이면 root
        TreeType judge(int current, int parent) {
            if(parent != -1 && cache.get(current) != null) {
                return cache.get(current);
            }
            List<Integer> children = new ArrayList<>();
            for (Integer i : edges.getOrDefault(current, List.of())) {
                if (i != parent) {
                    children.add(i);
                }
            }
            boolean isCurrentEven = current %2 == 0;
            boolean isChildrenEven = children.size() %2 == 0;
            TreeType type;
            // 루트가 아니면 cache 활용
            if(isCurrentEven) {
                if (isChildrenEven) type = TreeType.NORMAL;
                else type = TreeType.REVERSED;
            }
            else {
                if (isChildrenEven) type = TreeType.REVERSED;
                else type = TreeType.NORMAL;
            }

            for(int child : children) {
                TreeType childType = judge(child, current);
                if(childType != type) return TreeType.NONE;
            }
            if(parent != -1) cache.put(current, type);
            return type;
        }

        void build(int startNode) {
            Deque<Integer> deque = new ArrayDeque<Integer>();
            deque.add(startNode);
            this.nodes.add(startNode);
            // visited 대신 set으로 o(1) 조회
            while(!deque.isEmpty()) {
                int next = deque.poll();
                for(int adj: this.edges.getOrDefault(next, List.of())) {
//                    if(this.nodes.contains(adj)) continue;
                    this.nodes.add(adj);
                    nodeToForest.put(adj, this);
                    deque.push(adj);
                }
            }
        }
    }
    Map<Integer, Forest> nodeToForest;
    public int[] solution(int[] nodes, int[][] edges) {
        nodeToForest = new HashMap<>();
        // 트리 그루핑
        // 모든 노드에 대해서 완전탐색 수행, tree로 묶음
        // 만약 tree로 이미 묶인 노드면 pass
        Map<Integer, List<Integer>> adj = new HashMap<>();
        List<Forest> forests = new ArrayList<>();
        int forestId = 1;
        for(int[] edge: edges) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        for (int node : nodes) {
            if (nodeToForest.get(node) != null) continue;
            Forest forest = new Forest(forestId++, adj);
            forests.add(forest);
            forest.build(node);
        }


        int normalTreeCount = 0;
        int reversedTreeCount = 0;
        for (Forest forest: forests) {
            for(int rootNode : forest.nodes) {
                TreeType type = forest.judge(rootNode, -1);
                if(type == TreeType.NORMAL) normalTreeCount++;
                if(type == TreeType.REVERSED) reversedTreeCount++;
            }
        }
        return new int[]{normalTreeCount, reversedTreeCount};
    }
}