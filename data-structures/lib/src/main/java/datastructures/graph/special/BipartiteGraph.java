package datastructures.graph.special;

import datastructures.graph.Graph;
import datastructures.graph.UndirectedGraph;

import java.util.List;
import java.util.Set;

/**
 * 이분 그래프(Bipartite Graph) 인터페이스
 * 정점을 두 집합으로 나누어 같은 집합 내의 정점들은 연결되지 않는 그래프
 * @param <V> 정점 타입
 * @param <E> 간선 가중치 타입
 */
public interface BipartiteGraph<V, E> extends UndirectedGraph<V, E> {
    /**
     * 이분 그래프인지 확인
     * @return 이분 그래프이면 true
     */
    boolean isBipartite();
    
    /**
     * 첫 번째 정점 집합을 반환
     * @return 첫 번째 집합의 정점들
     */
    Set<V> getFirstPartition();
    
    /**
     * 두 번째 정점 집합을 반환
     * @return 두 번째 집합의 정점들
     */
    Set<V> getSecondPartition();
    
    /**
     * 정점을 특정 집합에 할당
     * @param vertex 정점
     * @param isFirstPartition 첫 번째 집합에 할당할지 여부
     */
    void assignToPartition(V vertex, boolean isFirstPartition);
    
    /**
     * 완전 이분 그래프인지 확인
     * @return 완전 이분 그래프이면 true
     */
    boolean isCompleteBipartite();
    
    /**
     * 최대 매칭을 찾아 반환
     * @return 최대 매칭의 간선들
     */
    List<Graph.Edge<V, E>> getMaximumMatching();
    
    /**
     * 최대 독립 집합을 반환
     * @return 최대 독립 집합의 정점들
     */
    Set<V> getMaximumIndependentSet();
    
    /**
     * 최소 정점 덮개를 반환
     * @return 최소 정점 덮개의 정점들
     */
    Set<V> getMinimumVertexCover();
    
    /**
     * 홀 결혼 정리(Hall's Marriage Theorem) 조건을 만족하는지 확인
     * @return 조건을 만족하면 true
     */
    boolean satisfiesHallCondition();
    
    /**
     * 두 집합의 크기 차이를 반환
     * @return |partition1| - |partition2|
     */
    int getPartitionSizeDifference();
    
    /**
     * 특정 정점이 속한 집합을 반환
     * @param vertex 정점
     * @return 첫 번째 집합이면 true, 두 번째 집합이면 false
     */
    boolean getVertexPartition(V vertex);
} 