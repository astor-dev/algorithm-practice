package datastructures.graph;

import java.util.List;

/**
 * 무방향 그래프(Undirected Graph) 인터페이스
 * @param <V> 정점 타입
 * @param <E> 간선 가중치 타입
 */
public interface UndirectedGraph<V, E> extends Graph<V, E> {
    /**
     * 특정 정점과 인접한 간선들을 반환
     * @param vertex 대상 정점
     * @return 인접한 간선들의 리스트
     */
    List<Graph.Edge<V, E>> getIncidentEdges(V vertex);
    
    /**
     * 그래프가 연결되어 있는지 확인
     * @return 연결되어 있으면 true
     */
    boolean isConnected();
    
    /**
     * 연결 컴포넌트들을 반환
     * @return 연결 컴포넌트들의 리스트
     */
    List<List<V>> getConnectedComponents();
    
    /**
     * 두 정점 사이의 최단 경로를 반환
     * @param from 시작 정점
     * @param to 목표 정점
     * @return 최단 경로 정점들의 리스트
     */
    List<V> getShortestPath(V from, V to);
    
    /**
     * 그래프가 이분 그래프(bipartite)인지 확인
     * @return 이분 그래프이면 true
     */
    boolean isBipartite();
    
    /**
     * 이분 그래프의 두 집합을 반환
     * @return 두 집합의 리스트 [집합1, 집합2]
     * @throws IllegalStateException 이분 그래프가 아닌 경우
     */
    List<List<V>> getBipartition();
    
    /**
     * 그래프가 트리인지 확인
     * @return 트리이면 true
     */
    boolean isTree();
    
    /**
     * 그래프가 포레스트(forest)인지 확인
     * @return 포레스트이면 true
     */
    boolean isForest();
    
    /**
     * 최소 신장 트리를 반환 (Kruskal 알고리즘)
     * @return 최소 신장 트리의 간선들
     */
    List<Graph.Edge<V, E>> getMinimumSpanningTreeKruskal();
    
    /**
     * 최소 신장 트리를 반환 (Prim 알고리즘)
     * @param startVertex 시작 정점
     * @return 최소 신장 트리의 간선들
     */
    List<Graph.Edge<V, E>> getMinimumSpanningTreePrim(V startVertex);
    
    /**
     * 특정 정점에서 모든 정점까지의 최단 거리를 반환
     * @param startVertex 시작 정점
     * @return 정점별 최단 거리 맵
     */
    java.util.Map<V, E> getShortestDistances(V startVertex);
    
    /**
     * 그래프의 지름(diameter)을 반환
     * @return 그래프의 지름
     */
    E getDiameter();
    
    /**
     * 그래프의 반지름(radius)을 반환
     * @return 그래프의 반지름
     */
    E getRadius();
} 