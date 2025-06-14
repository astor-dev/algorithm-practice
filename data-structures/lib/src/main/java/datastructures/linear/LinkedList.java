package datastructures.linear;

/**
 * 연결 리스트 기반 리스트 인터페이스
 * 노드 연결을 이용한 리스트의 추상적 데이터 타입
 * @param <T> 데이터 타입
 */
public interface LinkedList<T> extends List<T> {
    /**
     * 리스트의 첫 번째 위치에 요소를 추가
     * @param element 추가할 요소
     */
    void addFirst(T element);
    
    /**
     * 리스트의 마지막 위치에 요소를 추가
     * @param element 추가할 요소
     */
    void addLast(T element);
    
    /**
     * 리스트의 첫 번째 요소를 제거하고 반환
     * @return 제거된 요소
     */
    T removeFirst();
    
    /**
     * 리스트의 마지막 요소를 제거하고 반환
     * @return 제거된 요소
     */
    T removeLast();
    
    /**
     * 리스트의 첫 번째 요소를 반환 (제거하지 않음)
     * @return 첫 번째 요소
     */
    T getFirst();
    
    /**
     * 리스트의 마지막 요소를 반환 (제거하지 않음)
     * @return 마지막 요소
     */
    T getLast();
} 