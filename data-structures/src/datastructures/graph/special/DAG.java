package datastructures.graph.special;

import datastructures.graph.DirectedGraph;
import java.util.List;
import java.util.Map;

/**
 * 방향성 비순환 그래프(Directed Acyclic Graph - DAG) 인터페이스
 * @param <V> 정점 타입
 * @param <E> 간선 가중치 타입
 */
public interface DAG<V, E> extends DirectedGraph<V, E> {
    /**
     * DAG인지 확인 (사이클이 없는지)
     * @return DAG이면 true
     */
    boolean isDAG();
    
    /**
     * 위상 정렬을 수행 (Kahn's Algorithm)
     * @return 위상 정렬된 정점들
     */
    List<V> topologicalSortKahn();
    
    /**
     * 위상 정렬을 수행 (DFS 기반)
     * @return 위상 정렬된 정점들
     */
    List<V> topologicalSortDFS();
    
    /**
     * 모든 가능한 위상 정렬을 반환
     * @return 가능한 모든 위상 정렬들
     */
    List<List<V>> getAllTopologicalSorts();
    
    /**
     * 최장 경로를 반환
     * @param from 시작 정점
     * @param to 목표 정점
     * @return 최장 경로
     */
    List<V> getLongestPath(V from, V to);
    
    /**
     * 최장 경로의 길이를 반환
     * @param from 시작 정점
     * @param to 목표 정점
     * @return 최장 경로 길이
     */
    E getLongestPathLength(V from, V to);
    
    /**
     * 임계 경로(Critical Path)를 반환
     * @return 임계 경로
     */
    List<V> getCriticalPath();
    
    /**
     * 모든 정점의 레벨을 계산
     * @return 정점별 레벨 맵
     */
    Map<V, Integer> getVertexLevels();
    
    /**
     * 특정 정점의 레벨을 반환
     * @param vertex 대상 정점
     * @return 정점의 레벨
     */
    int getVertexLevel(V vertex);
    
    /**
     * 소스 정점들(진입차수가 0인 정점)을 반환
     * @return 소스 정점들
     */
    List<V> getSourceVertices();
    
    /**
     * 싱크 정점들(진출차수가 0인 정점)을 반환
     * @return 싱크 정점들
     */
    List<V> getSinkVertices();
    
    /**
     * DAG의 높이(최대 레벨)를 반환
     * @return DAG의 높이
     */
    int getHeight();
    
    /**
     * DAG의 너비(각 레벨의 최대 정점 수)를 반환
     * @return DAG의 너비
     */
    int getWidth();
    
    /**
     * 특정 정점의 모든 가능한 경로를 반환
     * @param from 시작 정점
     * @param to 목표 정점
     * @return 모든 가능한 경로들
     */
    List<List<V>> getAllPaths(V from, V to);
    
    /**
     * 정점들을 레벨별로 그룹화하여 반환
     * @return 레벨별 정점 그룹
     */
    Map<Integer, List<V>> getVerticesByLevel();
    
    /**
     * 두 정점 사이의 도달 가능성을 확인
     * @param from 시작 정점
     * @param to 목표 정점
     * @return 도달 가능하면 true
     */
    boolean isReachable(V from, V to);
} 