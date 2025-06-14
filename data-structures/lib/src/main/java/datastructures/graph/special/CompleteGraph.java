package datastructures.graph.special;

import datastructures.graph.UndirectedGraph;

import java.util.List;

/**
 * 완전 그래프(Complete Graph) 인터페이스
 * 모든 정점이 서로 연결된 그래프
 * @param <V> 정점 타입
 * @param <E> 간선 가중치 타입
 */
public interface CompleteGraph<V, E> extends UndirectedGraph<V, E> {
    /**
     * 완전 그래프인지 확인
     * @return 완전 그래프이면 true
     */
    boolean isComplete();
    
    /**
     * n개의 정점으로 완전 그래프를 생성
     * @param vertices 정점들
     * @param defaultWeight 기본 가중치
     */
    void createCompleteGraph(List<V> vertices, E defaultWeight);
    
    /**
     * 완전 그래프의 최대 간선 수를 반환
     * @return 최대 간선 수 n*(n-1)/2
     */
    int getMaxEdgeCount();
    
    /**
     * 그래프의 완전성 비율을 반환
     * @return 현재 간선 수 / 최대 간선 수
     */
    double getCompletenessRatio();
    
    /**
     * 특정 정점을 제거하여 부분 완전 그래프 생성
     * @param vertex 제거할 정점
     * @return 새로운 완전 그래프
     */
    CompleteGraph<V, E> removeVertexKeepComplete(V vertex);
} 