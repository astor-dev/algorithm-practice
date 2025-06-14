package datastructures.graph;

import java.util.List;
import java.util.Map;

/**
 * 인접 리스트 기반 그래프 인터페이스
 * @param <V> 정점 타입
 * @param <E> 간선 가중치 타입
 */
public interface AdjacencyListGraph<V, E> extends Graph<V, E> {
    /**
     * 인접 리스트를 반환
     * @return 정점별 인접 정점들의 맵
     */
    Map<V, List<V>> getAdjacencyList();
    
    /**
     * 특정 정점의 인접 리스트를 반환
     * @param vertex 대상 정점
     * @return 인접 정점들의 리스트
     */
    List<V> getAdjacencyList(V vertex);
    
    /**
     * 인접 리스트에 정점을 추가
     * @param vertex 정점
     * @param neighbor 인접 정점
     * @return 추가 성공 시 true
     */
    boolean addToAdjacencyList(V vertex, V neighbor);
    
    /**
     * 인접 리스트에서 정점을 제거
     * @param vertex 정점
     * @param neighbor 제거할 인접 정점
     * @return 제거 성공 시 true
     */
    boolean removeFromAdjacencyList(V vertex, V neighbor);
    
    /**
     * 인접 리스트의 메모리 사용량을 최적화
     */
    void optimizeMemory();
    
    /**
     * 인접 리스트를 정렬
     * @param vertex 대상 정점
     */
    void sortAdjacencyList(V vertex);
    
    /**
     * 모든 인접 리스트를 정렬
     */
    void sortAllAdjacencyLists();
    
    /**
     * 인접 리스트의 평균 길이를 반환
     * @return 평균 인접 리스트 길이
     */
    double getAverageAdjacencyListSize();
    
    /**
     * 가장 긴 인접 리스트의 길이를 반환
     * @return 최대 인접 리스트 길이
     */
    int getMaxAdjacencyListSize();
    
    /**
     * 가장 짧은 인접 리스트의 길이를 반환
     * @return 최소 인접 리스트 길이
     */
    int getMinAdjacencyListSize();
}