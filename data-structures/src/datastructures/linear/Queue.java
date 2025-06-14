package datastructures.linear;

/**
 * 큐 ADT 인터페이스
 * @param <T> 데이터 타입
 */
public interface Queue<T> extends DataStructure<T> {
    /**
     * 큐의 뒤쪽에 요소를 추가
     * @param element 추가할 요소
     * @return 추가 성공 시 true
     */
    boolean enqueue(T element);
    
    /**
     * 큐의 앞쪽 요소를 제거하고 반환
     * @return 제거된 요소
     * @throws RuntimeException 큐가 비어있는 경우
     */
    T dequeue();
    
    /**
     * 큐의 앞쪽 요소를 반환 (제거하지 않음)
     * @return 앞쪽 요소
     * @throws RuntimeException 큐가 비어있는 경우
     */
    T front();
    
    /**
     * 큐의 뒤쪽 요소를 반환 (제거하지 않음)
     * @return 뒤쪽 요소
     * @throws RuntimeException 큐가 비어있는 경우
     */
    T rear();
} 