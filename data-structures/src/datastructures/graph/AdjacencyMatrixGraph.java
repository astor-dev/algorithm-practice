package datastructures.graph;

/**
 * 인접 행렬 기반 그래프 인터페이스
 * @param <V> 정점 타입
 * @param <E> 간선 가중치 타입
 */
public interface AdjacencyMatrixGraph<V, E> extends Graph<V, E> {
    /**
     * 인접 행렬을 반환
     * @return 2차원 인접 행렬
     */
    E[][] getAdjacencyMatrix();
    
    /**
     * 특정 위치의 인접 행렬 값을 반환
     * @param i 행 인덱스
     * @param j 열 인덱스
     * @return 인접 행렬 값
     */
    E getMatrixValue(int i, int j);
    
    /**
     * 특정 위치의 인접 행렬 값을 설정
     * @param i 행 인덱스
     * @param j 열 인덱스
     * @param value 설정할 값
     */
    void setMatrixValue(int i, int j, E value);
    
    /**
     * 정점을 인덱스로 변환
     * @param vertex 정점
     * @return 인덱스
     */
    int getVertexIndex(V vertex);
    
    /**
     * 인덱스를 정점으로 변환
     * @param index 인덱스
     * @return 정점
     */
    V getVertexByIndex(int index);
    
    /**
     * 행렬의 대각선 합을 반환 (trace)
     * @return 대각선 합
     */
    E getTrace();
    
    /**
     * 행렬의 전치를 반환
     * @return 전치된 인접 행렬
     */
    E[][] getTranspose();
    
    /**
     * 행렬이 대칭인지 확인
     * @return 대칭이면 true
     */
    boolean isSymmetric();
    
    /**
     * 행렬의 거듭제곱을 계산
     * @param power 거듭제곱 수
     * @return 거듭제곱된 행렬
     */
    E[][] matrixPower(int power);
    
    /**
     * 특정 길이의 경로 개수를 반환
     * @param from 시작 정점
     * @param to 목표 정점
     * @param length 경로 길이
     * @return 경로 개수
     */
    int getPathCount(V from, V to, int length);
    
    /**
     * 행렬의 메모리 사용량을 반환 (바이트 단위)
     * @return 메모리 사용량
     */
    long getMemoryUsage();
    
    /**
     * 행렬을 압축하여 메모리 최적화
     */
    void compressMatrix();
    
    /**
     * 행렬의 밀도를 반환 (0과 아닌 값의 비율)
     * @return 행렬 밀도
     */
    double getMatrixDensity();
} 