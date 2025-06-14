package datastructures.graph;

import java.util.List;
import java.util.Map;

/**
 * 가중치 그래프(Weighted Graph) 인터페이스
 * @param <V> 정점 타입
 * @param <E> 간선 가중치 타입
 */
public interface WeightedGraph<V, E extends Comparable<E>> extends Graph<V, E> {
    /**
     * 모든 정점 쌍 간의 최단 경로를 계산 (Floyd-Warshall)
     * @return 정점 쌍별 최단 거리 맵
     */
    Map<V, Map<V, E>> getAllPairsShortestPaths();
    
    /**
     * 특정 정점에서 다른 모든 정점까지의 최단 경로 (Dijkstra)
     * @param source 시작 정점
     * @return 정점별 최단 거리와 경로
     */
    Map<V, PathInfo<V, E>> getShortestPathsFromSource(V source);
    
    /**
     * 음수 가중치를 허용하는 최단 경로 알고리즘 (Bellman-Ford)
     * @param source 시작 정점
     * @return 정점별 최단 거리와 경로
     * @throws IllegalStateException 음수 사이클이 있는 경우
     */
    Map<V, PathInfo<V, E>> getBellmanFordShortestPaths(V source);
    
    /**
     * 두 정점 사이의 최단 경로와 거리를 반환
     * @param from 시작 정점
     * @param to 목표 정점
     * @return 경로 정보
     */
    PathInfo<V, E> getShortestPath(V from, V to);
    
    /**
     * 그래프에 음수 가중치 간선이 있는지 확인
     * @return 음수 가중치가 있으면 true
     */
    boolean hasNegativeEdges();
    
    /**
     * 그래프에 음수 사이클이 있는지 확인
     * @return 음수 사이클이 있으면 true
     */
    boolean hasNegativeCycle();
    
    /**
     * 가중치의 총합을 반환
     * @return 모든 간선 가중치의 합
     */
    E getTotalWeight();
    
    /**
     * 최소 가중치 간선을 반환
     * @return 최소 가중치 간선
     */
    Graph.Edge<V, E> getMinWeightEdge();
    
    /**
     * 최대 가중치 간선을 반환
     * @return 최대 가중치 간선
     */
    Graph.Edge<V, E> getMaxWeightEdge();
    
    /**
     * 특정 가중치 범위 내의 간선들을 반환
     * @param minWeight 최소 가중치
     * @param maxWeight 최대 가중치
     * @return 범위 내 간선들
     */
    List<Graph.Edge<V, E>> getEdgesInWeightRange(E minWeight, E maxWeight);
    
    /**
     * k번째 최단 경로를 반환
     * @param from 시작 정점
     * @param to 목표 정점
     * @param k k번째
     * @return k번째 최단 경로들
     */
    List<PathInfo<V, E>> getKShortestPaths(V from, V to, int k);
    
    /**
     * 경로 정보를 담는 클래스
     * @param <V> 정점 타입
     * @param <E> 가중치 타입
     */
    class PathInfo<V, E> {
        private final List<V> path;
        private final E distance;
        
        public PathInfo(List<V> path, E distance) {
            this.path = path;
            this.distance = distance;
        }
        
        public List<V> getPath() { return path; }
        public E getDistance() { return distance; }
        
        @Override
        public String toString() {
            return "Path: " + path + ", Distance: " + distance;
        }
    }
}