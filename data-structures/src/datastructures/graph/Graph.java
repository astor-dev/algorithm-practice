package datastructures.graph;

import java.util.List;

/**
 * 그래프 ADT 인터페이스
 * @param <V> 정점(Vertex) 타입
 * @param <E> 간선(Edge) 타입
 */
public interface Graph<V, E> {
    /**
     * 정점을 추가
     * @param vertex 추가할 정점
     * @return 추가 성공 시 true
     */
    boolean addVertex(V vertex);
    
    /**
     * 정점을 제거
     * @param vertex 제거할 정점
     * @return 제거 성공 시 true
     */
    boolean removeVertex(V vertex);
    
    /**
     * 간선을 추가
     * @param from 시작 정점
     * @param to 끝 정점
     * @param weight 가중치
     * @return 추가 성공 시 true
     */
    boolean addEdge(V from, V to, E weight);
    
    /**
     * 간선을 제거
     * @param from 시작 정점
     * @param to 끝 정점
     * @return 제거 성공 시 true
     */
    boolean removeEdge(V from, V to);
    
    /**
     * 두 정점 사이에 간선이 있는지 확인
     * @param from 시작 정점
     * @param to 끝 정점
     * @return 간선이 있으면 true
     */
    boolean hasEdge(V from, V to);
    
    /**
     * 두 정점 사이의 간선 가중치를 반환
     * @param from 시작 정점
     * @param to 끝 정점
     * @return 간선 가중치
     */
    E getEdgeWeight(V from, V to);
    
    /**
     * 특정 정점의 인접 정점들을 반환
     * @param vertex 대상 정점
     * @return 인접 정점들의 리스트
     */
    List<V> getNeighbors(V vertex);
    
    /**
     * 특정 정점의 차수(degree)를 반환
     * @param vertex 대상 정점
     * @return 차수
     */
    int getDegree(V vertex);
    
    /**
     * 특정 정점의 진입차수(in-degree)를 반환
     * @param vertex 대상 정점
     * @return 진입차수
     */
    int getInDegree(V vertex);
    
    /**
     * 특정 정점의 진출차수(out-degree)를 반환
     * @param vertex 대상 정점
     * @return 진출차수
     */
    int getOutDegree(V vertex);
    
    /**
     * 모든 정점들을 반환
     * @return 정점들의 리스트
     */
    List<V> getVertices();
    
    /**
     * 모든 간선들을 반환
     * @return 간선들의 리스트
     */
    List<Edge<V, E>> getEdges();
    
    /**
     * 정점의 개수를 반환
     * @return 정점 개수
     */
    int getVertexCount();
    
    /**
     * 간선의 개수를 반환
     * @return 간선 개수
     */
    int getEdgeCount();
    
    /**
     * 그래프가 방향 그래프인지 확인
     * @return 방향 그래프이면 true
     */
    boolean isDirected();
    
    /**
     * 그래프가 가중치 그래프인지 확인
     * @return 가중치 그래프이면 true
     */
    boolean isWeighted();
    
    /**
     * 깊이 우선 탐색(DFS) 수행
     * @param startVertex 시작 정점
     * @return DFS 순회 결과
     */
    List<V> depthFirstSearch(V startVertex);
    
    /**
     * 너비 우선 탐색(BFS) 수행
     * @param startVertex 시작 정점
     * @return BFS 순회 결과
     */
    List<V> breadthFirstSearch(V startVertex);
    
    /**
     * 그래프의 크기를 반환 (정점 개수)
     * @return 정점 개수
     */
    int size();
    
    /**
     * 그래프가 비어있는지 확인
     * @return 비어있으면 true
     */
    boolean isEmpty();
    
    /**
     * 그래프를 비움
     */
    void clear();
    
    /**
     * 특정 정점이 포함되어 있는지 확인
     * @param vertex 찾을 정점
     * @return 포함되어 있으면 true
     */
    boolean contains(V vertex);
    
    /**
     * 간선 내부 클래스
     * @param <V> 정점 타입
     * @param <E> 간선 가중치 타입
     */
    class Edge<V, E> {
        private V from;
        private V to;
        private E weight;
        
        public Edge(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
        public V getFrom() { return from; }
        public V getTo() { return to; }
        public E getWeight() { return weight; }
        
        @Override
        public String toString() {
            return "(" + from + " -> " + to + ", weight: " + weight + ")";
        }
    }
} 