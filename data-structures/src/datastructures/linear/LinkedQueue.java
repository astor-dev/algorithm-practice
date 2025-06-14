package datastructures.linear;

/**
 * 연결 리스트 기반 큐 인터페이스
 * 연결 노드를 이용한 큐의 추상적 데이터 타입
 * @param <T> 데이터 타입
 */
public interface LinkedQueue<T> extends Queue<T> {
    /**
     * 큐의 앞쪽 노드 정보를 반환
     * @return 앞쪽 노드의 정보
     */
    String getFrontNodeInfo();
    
    /**
     * 큐의 뒤쪽 노드 정보를 반환
     * @return 뒤쪽 노드의 정보
     */
    String getRearNodeInfo();
}