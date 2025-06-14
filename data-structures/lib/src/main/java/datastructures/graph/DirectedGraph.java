package datastructures.graph;

import java.util.List;

/**
 * 방향 그래프(Directed Graph) 인터페이스
 * @param <V> 정점 타입
 * @param <E> 간선 가중치 타입
 */
public interface DirectedGraph<V, E> extends Graph<V, E> {
    /**
     * 특정 정점에서 나가는 간선들을 반환
     * @param vertex 시작 정점
     * @return 나가는 간선들의 리스트
     */
    List<Graph.Edge<V, E>> getOutgoingEdges(V vertex);
    
    /**
     * 특정 정점으로 들어오는 간선들을 반환
     * @param vertex 목표 정점
     * @return 들어오는 간선들의 리스트
     */
    List<Graph.Edge<V, E>> getIncomingEdges(V vertex);
    
    /**
     * 그래프를 전치(transpose)하여 반환
     * 모든 간선의 방향을 반대로 바꾼 그래프
     * @return 전치 그래프
     */
    DirectedGraph<V, E> transpose();
    
    /**
     * 특정 정점에서 다른 정점으로의 경로가 있는지 확인
     * @param from 시작 정점
     * @param to 목표 정점
     * @return 경로가 있으면 true
     */
    boolean hasPath(V from, V to);
    
    /**
     * 그래프가 강연결(strongly connected)인지 확인
     * @return 강연결이면 true
     */
    boolean isStronglyConnected();
    
    /**
     * 강연결 컴포넌트들을 반환
     * @return 강연결 컴포넌트들의 리스트
     */
    List<List<V>> getStronglyConnectedComponents();
    
    /**
     * 위상 정렬(topological sort) 수행
     * @return 위상 정렬된 정점들의 리스트
     * @throws IllegalStateException 사이클이 있는 경우
     */
    List<V> topologicalSort();
    
    /**
     * 그래프에 사이클이 있는지 확인
     * @return 사이클이 있으면 true
     */
    boolean hasCycle();
    
    /**
     * 특정 정점의 조상 정점들을 반환
     * @param vertex 대상 정점
     * @return 조상 정점들의 리스트
     */
    List<V> getAncestors(V vertex);
    
    /**
     * 특정 정점의 후손 정점들을 반환
     * @param vertex 대상 정점
     * @return 후손 정점들의 리스트
     */
    List<V> getDescendants(V vertex);
} 